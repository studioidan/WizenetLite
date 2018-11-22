package com.fcm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.Activities.MainActivity;
import com.Activities.R;
import com.Classes.Call;
import com.Classes.Calltime;
import com.DatabaseHelper;
import com.Helper;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.model.Consts;
import com.model.Model;
import com.uk.U;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

            // get the operation code
            String opCode = remoteMessage.getData().get("opCode");
            JSONObject data;


            try {
                String callId = "";
                data = new JSONObject(remoteMessage.getData().get("data"));
                switch (opCode) {
                    case Consts.OP_CODE_SHOW_PUSH_NOTIFICATION:
                        String title = data.getString("title");
                        String message = data.getString("message");
                        sendNotification(title, message);
                        break;

                    case Consts.OP_CODE_CLOSE_CALL_TIME:
                        callId = data.getString("callId");
                        stopCall(callId);
                        break;

                    case Consts.OP_CODE_UPDATE_CALL_STATUS:
                        callId = data.getString("callId");
                        String statusId = data.getString("statusId");
                        updateCallStatus(callId, statusId);
                        break;
                }
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

        }
    }

    private void updateCallStatus(String callId, String statusId) {

    }

    private void stopCall(String callId) {
        try {
            DatabaseHelper db = DatabaseHelper.getInstance(getApplicationContext());
            Helper helper = new Helper();
            db.updateSpecificValueInTable2("mgnet_calls", "CallID", String.valueOf(callId), "state", "'" + null + "'");
            Calltime ct = DatabaseHelper.getInstance(getApplicationContext()).getCalltimeByCallidAndAction(callId, "work", "");
            // ct = getCTID(String.valueOf(Integer.valueOf(callId)), "work", "");
            if (ct.getCTID() != -1 && ct.getCtq().contains("-2")) {
                closeWorkRow(ct);

                // updateRideRow(ct.getCallID());
                // update ride row
                // Calltime ct2 = getCTID(String.valueOf(Integer.valueOf(callid)), "ride", "-2");
                Calltime ct2 = DatabaseHelper.getInstance(getApplicationContext()).getCalltimeByCallidAndAction(callId, "ride", "-2");

                if (ct2.getCTID() != -1) {
                    ct2.setCtq("-1");
                    DatabaseHelper.getInstance(getApplicationContext()).update_calltime(ct2);
                }
            }

            db.getJsonResultsFromTable("Calltime");
            helper.transferJsonCallTime(getApplicationContext());

            Call call = db.getCallDetailsByCallID(Integer.valueOf(callId));

            // simulate update click
            if (helper.isNetworkAvailable(getApplicationContext())) {
                Model.getInstance().Async_Wz_Update_Call_Field_Listener(helper.getMacAddr(getApplicationContext()), (callId), "internalSN", "'" + call.getInternalSN() + "'", new Model.Wz_Update_Call_Field_Listener() {
                    @Override
                    public void onResult(String str) {

                    }
                });
                Model.getInstance().Async_Wz_Call_Update_Listener(helper.getMacAddr(getApplicationContext()), Integer.valueOf(callId), call.getStatusID(), "" /*txttechanswer.getText().toString()*/, new Model.Wz_Call_Update_Listener() {
                    @Override
                    public void onResult(String str) {
                        try {
                            JSONObject j = null;
                            j = new JSONObject(str);
                            Log.e("MYTAG", str);
                            //get the array [...] in json
                            JSONArray jarray = j.getJSONArray("Wz_Call_Update");
                            String status = jarray.getJSONObject(0).getString("Status");
                            if (status.equals("0")) {
                                //Toast.makeText(getApplicationContext(),"successfully updated", Toast.LENGTH_LONG).show();
                                //finish();
                                //txttechanswer.setText("");
                            } else {
                                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                    }
                });
                Toast.makeText(getApplicationContext(), "successfully updated", Toast.LENGTH_LONG).show();
                // finish();
            }


        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

    }

    private void closeWorkRow(Calltime ct) {
        String date11 = ct.getCallStartTime();
        String fromdateinmillis = String.valueOf(U.stringToDate(date11, "yyyy-MM-dd HH:mm:ss").getTime());
        String currentDateTimeString1 = String.valueOf((new Date().getTime()));
        int dateDifference = (int) Helper.getDateDiff(fromdateinmillis, currentDateTimeString1);
        ct.setCtq("-1");
        ct.setMinute(String.valueOf(Integer.valueOf(dateDifference) + 1));
        DatabaseHelper.getInstance(getApplicationContext()).update_calltime(ct);
    }


    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(token);
    }
    // [END on_new_token]


    /**
     * Persist token to third-party servers.
     * <p>
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
    }

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private void sendNotification(String title, String messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        String channelId = "wizenet";
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.face_outline)
                        .setContentTitle(title)
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary))
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}