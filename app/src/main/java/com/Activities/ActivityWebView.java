package com.Activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.Adapters.CallsAdapter;
import com.Classes.Call;
import com.DatabaseHelper;
import com.Helper;
import com.studioidan.popapp.views.HeaderView;
import com.studioidan.popapp.views.IconView;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityWebView extends FragmentActivity {

    Helper helper;
    Context ctx;
    private HeaderView header;
    private WebView mWebview;


    DatabaseHelper db;
    ListView myList;
    LocationManager manager = null;
    boolean result = false;
    private EditText mSearchEdt;
    CallsAdapter callsAdapter; //to refresh the list
    ArrayList<Call> data2 = new ArrayList<Call>();
    private TextWatcher mSearchTw;
    private ValueCallback<Uri> mUploadMessage;
    private ValueCallback<Uri[]> mUploadMessageandroid5;
    private final static int FILECHOOSER_RESULTCODE = 1;


   /* @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        if (requestCode == FILECHOOSER_RESULTCODE) {
            if (null == mUploadMessage) return;
            Uri result = intent == null || resultCode != RESULT_OK ? null
                    : intent.getData();
            mUploadMessage.onReceiveValue(result);
            mUploadMessage = null;
        }
    }*/


    private String mCM;
    private ValueCallback mUM;
    private ValueCallback<Uri[]> mUMA;
    private final static int FCR = 1;

    //select whether you want to upload multiple files (set 'true' for yes)
    private boolean multiple_files = false;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (Build.VERSION.SDK_INT >= 21) {
            Uri[] results = null;
            //checking if response is positive
            if (resultCode == Activity.RESULT_OK) {
                if (requestCode == FCR) {
                    if (null == mUMA) {
                        return;
                    }
                    if (intent == null || intent.getData() == null) {
                        if (mCM != null) {
                            results = new Uri[]{Uri.parse(mCM)};
                        }
                    } else {
                        String dataString = intent.getDataString();
                        if (dataString != null) {
                            results = new Uri[]{Uri.parse(dataString)};
                        } else {
                            if (multiple_files) {
                                if (intent.getClipData() != null) {
                                    final int numSelectedFiles = intent.getClipData().getItemCount();
                                    results = new Uri[numSelectedFiles];
                                    for (int i = 0; i < numSelectedFiles; i++) {
                                        results[i] = intent.getClipData().getItemAt(i).getUri();
                                    }
                                }
                            }
                        }
                    }
                }
            }
            mUMA.onReceiveValue(results);
            mUMA = null;
        } else {
            if (requestCode == FCR) {
                if (null == mUM) return;
                Uri result = intent == null || resultCode != RESULT_OK ? null : intent.getData();
                mUM.onReceiveValue(result);
                mUM = null;
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_webview);
        ctx = this;

        this.header = findViewById(R.id.header);
        header.setHeaderMode(HeaderView.HEADER_MODE.BACK);
        header.getActionIconThree().setVisibility(View.VISIBLE);
        header.getActionIconThree().setIcon(IconView.ICON_MATERIAL, "\uf326");
        header.setCallback(new HeaderView.HeaderCallback() {
            @Override
            public void onHamburgerClicked() {

            }

            @Override
            public void onActionOneClicked() {

            }

            @Override
            public void onActionTwoClicked() {

            }

            @Override
            public void onActionThreeClicked() {
                String url = mWebview.getUrl();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }

            @Override
            public void onBackClicked() {
                onBackPressed();
            }
        });

        int callid = -1;
        int cid = -1;
        int technicianid = -1;
        String action = "";
        String specialurl = "";
        Bundle b = getIntent().getExtras();
        if (b != null) {
            callid = b.getInt("callid");
            cid = b.getInt("cid");
            technicianid = b.getInt("technicianid");
            action = b.getString("action");
            try {
                specialurl = b.getString("specialurl");
                Log.e("mytag", "special url: " + specialurl);
            } catch (Exception e) {
                Log.e("mytag", "special url ex: " + e.getMessage());
            }

        }

        /*ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        ActionBar.LayoutParams lp1 = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        View customNav = LayoutInflater.from(this).inflate(R.layout.top_bar_back, null); // layout which contains your button.

        actionBar.setCustomView(customNav, lp1);
        Button iv = (Button) customNav.findViewById(R.id.back);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //Toast.makeText(getApplicationContext(),"clicked", Toast.LENGTH_LONG).show();
            }
        });*/


        // final WebView mWebview = new WebView(this);
        mWebview = findViewById(R.id.webview);
        final Activity activity = this;
        mWebview.getSettings().setJavaScriptEnabled(true); // enable javascript
        mWebview.getSettings().setAllowFileAccess(true);
        mWebview.getSettings().setAllowContentAccess(true);
        mWebview.getSettings().setAllowFileAccessFromFileURLs(true);
        // mWebview.getSettings().setUseWideViewPort(true);

       /* if (Build.VERSION.SDK_INT >= 21) {
            mWebview.getSettings().setMixedContentMode(0);
            mWebview.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else if (Build.VERSION.SDK_INT >= 19) {
            mWebview.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            mWebview.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }*/

        mWebview.setWebViewClient(new WebViewClient() {
            @SuppressWarnings("deprecation")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }

            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String url = request.getUrl().toString();

                if (url.startsWith("tel:")) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(request.getUrl().toString()));
                    startActivity(intent);
                    return true;
                }

                if (url.endsWith(".pdf")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    // if want to download pdf manually create AsyncTask here
                    // and download file
                    return true;
                }

                return super.shouldOverrideUrlLoading(view, request);

            }
        });

        try {

            mWebview.setWebChromeClient(new WebChromeClient() {
                //The undocumented magic method override
                //Eclipse will swear at you if you try to put @Override here


                public void openFileChooser(ValueCallback<Uri> uploadMsg) {
                    // ActivityWebView.this.showAttachmentDialog(uploadMsg);
                    // Log.e("mytag", uploadMsg.toString());

                    mUM = uploadMsg;
                    Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                    i.addCategory(Intent.CATEGORY_OPENABLE);
                    i.setType("*/*");
                    if (multiple_files && Build.VERSION.SDK_INT >= 18) {
                        i.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    }
                    startActivityForResult(Intent.createChooser(i, "File Chooser"), FCR);

                }

                // For Android > 3.x
                public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
                    ActivityWebView.this.showAttachmentDialog(uploadMsg);
                    Log.e("mytag", uploadMsg.toString());
                }

                // For Android > 4.1
                public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                    ActivityWebView.this.showAttachmentDialog(uploadMsg);
                    Log.e("mytag", uploadMsg.toString());
                }

                public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                    ///ActivityWebView.this.showAttachmentDialog(filePathCallback.onReceiveValue());
                    //openFileChooserImplForAndroid5(filePathCallback);

                    // ActivityWebView.this.mUploadMessageandroid5 = filePathCallback;

                    // Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                    // i.addCategory(Intent.CATEGORY_OPENABLE);
                    // i.setType("*/*");

                    //Intent fileIntent= fileChooserParams.createIntent();
                    // fileIntent.addCategory(Intent.CATEGORY_OPENABLE);
                    // fileIntent.setType("*/*");
                    // ActivityWebView.this.startActivityForResult(fileIntent,FILECHOOSER_RESULTCODE);
                    //ActivityWebView.this.startActivityForResult(Intent.createChooser(fileintent, "Choose type of attachment"), FILECHOOSER_RESULTCODE);


                    Log.e("mytag", "step4");

                    String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA};

                    //checking for storage permission to write images for upload
                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(ActivityWebView.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(ActivityWebView.this, perms, FCR);

                        //checking for WRITE_EXTERNAL_STORAGE permission
                    } else if (ContextCompat.checkSelfPermission(ActivityWebView.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(ActivityWebView.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, FCR);

                        //checking for CAMERA permissions
                    } else if (ContextCompat.checkSelfPermission(ActivityWebView.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(ActivityWebView.this, new String[]{Manifest.permission.CAMERA}, FCR);
                    }
                    if (mUMA != null) {
                        mUMA.onReceiveValue(null);
                    }
                    mUMA = filePathCallback;
                    Intent takePictureIntent = null;
                    takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePictureIntent.resolveActivity(ActivityWebView.this.getPackageManager()) != null) {
                        File photoFile = null;
                        try {
                            photoFile = createImageFile();
                            takePictureIntent.putExtra("PhotoPath", mCM);
                        } catch (Exception ex) {
                            Log.e("ok", "Image file creation failed", ex);
                        }
                        if (photoFile != null) {
                            mCM = "file:" + photoFile.getAbsolutePath();
                            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                        } else {
                            takePictureIntent = null;
                        }
                    }
                    Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
                    contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
                    contentSelectionIntent.setType("*/*");
                    if (multiple_files) {
                        contentSelectionIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    }
                    Intent[] intentArray;
                    if (takePictureIntent != null) {
                        intentArray = new Intent[]{takePictureIntent};
                    } else {
                        intentArray = new Intent[0];
                    }

                    Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
                    chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
                    chooserIntent.putExtra(Intent.EXTRA_TITLE, "File Chooser");
                    chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);
                    startActivityForResult(chooserIntent, FCR);
                    return true;
                }
            });

        } catch (Exception e) {
            Log.e("mytag", e.getMessage() + " " + e.getStackTrace().toString());
        }

        String url = "";
        switch (action) {
            case "dynamic":
                url = DatabaseHelper.getInstance(getApplicationContext()).getValueByKey("URL")
                        + "/IN.aspx?url=" + specialurl.replace(DatabaseHelper.getInstance(getApplicationContext()).getValueByKey("URL"), "")
                        + "&MACAddress=" + helper.getMacAddr(ctx);
                //Toast.makeText(activity, url, Toast.LENGTH_SHORT).show();
                Log.e("mytag", "url: " + url);
                break;
            case "calltimedetails":
                url = DatabaseHelper.getInstance(getApplicationContext()).getValueByKey("URL")
                        + "/IN.aspx?url="
                        + "/iframe.aspx?control=modulesServices/reportcalltimebytech_details&OdateFrom=" + getCurrentTimeStamp() + "&OdateTo=" + getCurrentTimeStamp() + "&techListDashboard=" + technicianid + "&CID=-1&strstatus=-999"
                        //+ "/iframe.aspx?control=modulesServices/dashboard_report&action=totalCloseCallsToday&techctypeid=&calltypeidnot=&calltype=&ClientCtypeID=&techid=" + technicianid + ""
                        + "&MACAddress=" + helper.getMacAddr(ctx);
                Log.e("mytag", "url: " + url);
                break;
            case "dashboard":
                url = DatabaseHelper.getInstance(getApplicationContext()).getValueByKey("URL")
                        + "/IN.aspx?url="
                        + "/iframe.aspx?control=modulesServices/dashboard_report&action=totalCloseCallsToday&techctypeid=&calltypeidnot=&calltype=&ClientCtypeID=&techid=" + technicianid + ""
                        + "&MACAddress=" + helper.getMacAddr(ctx);
                Log.e("mytag", "url: " + url);
                break;
            case "calltime":
                url = DatabaseHelper.getInstance(getApplicationContext()).getValueByKey("URL")
                        + "/IN.aspx?url="
                        + "/iframe.aspx?control=/modulesServices/CallRepHistory&CallID=" + String.valueOf(callid) + "&class=tdCallRepHistory&mobile=True"
                        + "&MACAddress=" + helper.getMacAddr(ctx);
                Log.e("mytag", "url: " + url);
                break;
            case "callparts":
                url = DatabaseHelper.getInstance(getApplicationContext()).getValueByKey("URL")
                        + "/IN.aspx?url="
                        + "/iframe.aspx?control=modulesServices%2fCallParts&CallID=" + String.valueOf(callid) + "&type=customer&val=" + String.valueOf(cid) + ""
                        + "&MACAddress=" + helper.getMacAddr(ctx);
                Log.e("mytag", "url: " + url);
                break;
            case "mycalls":
                url = DatabaseHelper.getInstance(getApplicationContext()).getValueByKey("URL")
                        + "/IN.aspx?url="
                        + "/mobile/control.aspx?control=modulesService/myCalls"
                        + "&MACAddress=" + helper.getMacAddr(ctx);
                Log.e("mytag", "url: " + url);
                break;
            case "callfiles":
                url = DatabaseHelper.getInstance(getApplicationContext()).getValueByKey("URL")
                        + "/IN.aspx?url="
                        + "/iframe.aspx?control=/modulesServices/CallsFiles&CallID=" + String.valueOf(callid) + "&class=CallsFiles_appCell&mobile=True"
                        + "&MACAddress=" + helper.getMacAddr(ctx);
                Log.e("mytag", "url: " + url);
                break;
            case "history":
                url = DatabaseHelper.getInstance(getApplicationContext()).getValueByKey("URL")
                        + "/IN.aspx?url="
                        + "/iframe.aspx?control=/modulesservices/callhistoryAll&CallID=" + String.valueOf(callid) + "&CID=" + String.valueOf(cid) + "&class=AppCelltable&mobile=True"
                        + "&MACAddress=" + helper.getMacAddr(ctx);
                Log.e("mytag", "url: " + url);
                break;
            case "customercase":
                url = DatabaseHelper.getInstance(getApplicationContext()).getValueByKey("URL")
                        + "/IN.aspx?url="
                        + "/iframe.aspx?control=modules/tableextrafields&table=calls&pk=callid&pkvalue=" + String.valueOf(callid) + ""
                        //+ "/iframe.aspx?control=modules/TableExtraFields&table=clients&pk=cid&pkvalue=" + String.valueOf(cid) + "&mobile=True"
                        + "&MACAddress=" + helper.getMacAddr(ctx);
                Log.e("mytag", "url: " + url);
                break;
            case "goToUserHistory":
                url = DatabaseHelper.getInstance(getApplicationContext()).getValueByKey("URL")
                        + "/IN.aspx?url="
                        + "/iframe.aspx?control=/modulesProjects/UsersTimeReport"
                        //+ "/mobile/control.aspx?control=/modulesProjects/UsersTimeReport"
                        + "&MACAddress=" + helper.getMacAddr(ctx);
                Log.e("mytag", "url: " + url);
                break;
            case "masofon":
                url = DatabaseHelper.getInstance(getApplicationContext()).getValueByKey("URL")
                        + "/IN.aspx?url="
                        + "/AppMasofon/masofon?1=1"
                        + "&MACAddress=" + helper.getMacAddr(ctx);
                Log.e("mytag", "url: " + url);
                break;
            case "callsign":

                // String url = "";//DatabaseHelper.getInstance(getApplicationContext()).getValueByKey("URL") + "/modulesSign/sign.aspx?callID=" + String.valueOf(call.getCallID());
//            url = DatabaseHelper.getInstance(getApplicationContext()).getValueByKey("URL")
//                    +"/IN.aspx?url="
//                    + "/modulesSign/sign.aspx?callID=" + String.valueOf(call.getCallID())
//                    +"&MACAddress=" + helper.getMacAddr(getBaseContext());
//            Log.e("mytag",url);
//            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//            startActivity(browserIntent);

                url = DatabaseHelper.getInstance(getApplicationContext()).getValueByKey("URL")
                        + "/IN.aspx?url="
                        + "/modulesSign/sign.aspx?callID=" + String.valueOf(callid)
                        + "&MACAddress=" + helper.getMacAddr(ctx);
                Log.e("mytag", "url: " + url);
                break;
            default:
                //setContentView(R.layout.default);
        }
        Log.e("mytag", "technicianid:" + technicianid);
        String cookieString = "CID=" + technicianid + "; path=/";
        String cookieString2 = "CtypeID=" + DatabaseHelper.getInstance(getBaseContext()).getValueByKey("CtypeID") + "; path=/";

        CookieManager.getInstance().setCookie(url, cookieString);
        CookieManager.getInstance().setCookie(url, cookieString2);


        mWebview.loadUrl(url);
        // mWebview.loadUrl("tel://0777782800");

        // setContentView(mWebview);

    }

    //creating new image file here
    private File createImageFile() throws IOException {
        @SuppressLint("SimpleDateFormat") String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "img_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(imageFileName, ".jpg", storageDir);
    }

    private void showAttachmentDialog(ValueCallback<Uri> uploadMsg) {
        this.mUploadMessage = uploadMsg;

        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("*/*");

        this.startActivityForResult(Intent.createChooser(i, "Choose type of attachment"), FILECHOOSER_RESULTCODE);
    }

    public String getCurrentTimeStamp() {
        return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }

    public void initList() {
        data2.clear();
        for (Call c : getCallsList()) {
            data2.add(c);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        Toast.makeText(getBaseContext(),"onRestart", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        Toast.makeText(getBaseContext(),"onResume", Toast.LENGTH_SHORT).show();
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.add:
//                //add the function to perform here
//                return (true);
            //case R.id.action_filter:
            //add the function to perform here
            // return (true);
//            case R.id.about:
//                //add the function to perform here
//                return (true);
        }
        return (super.onOptionsItemSelected(item));
    }

    private List<Call> getCallsList() {
        JSONObject j = null;
        int length = 0;

        List<Call> calls = new ArrayList<Call>();
        try {
            calls = DatabaseHelper.getInstance(this).getCalls("");
            length = calls.size();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return calls;
    }

}
