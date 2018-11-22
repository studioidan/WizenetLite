package com.studioidan.popapp.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.MediaMetadataRetriever;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Dor on 23/04/2014.
 */
public class BitmapUtils {

    private static final String TAG = BitmapUtils.class.getSimpleName();


    private static final String APP_DIRECTORY = "MyApp";

    public static final String PICTURE_PREFIX = "picture_";
    public static final int DEFAULT_RADIUS = 80;

    @SuppressLint("NewApi")
    public static Bitmap blur(Context context, Bitmap sentBitmap, int radius) {
        if (sentBitmap == null) {
            return null;
        }

        Bitmap bitmap = null;

        try {
            bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);

            if (radius < 1) {
                return (null);
            }

            int w = bitmap.getWidth();
            int h = bitmap.getHeight();

            int[] pix = new int[w * h];
            bitmap.getPixels(pix, 0, w, 0, 0, w, h);

            int wm = w - 1;
            int hm = h - 1;
            int wh = w * h;
            int div = radius + radius + 1;

            int r[] = new int[wh];
            int g[] = new int[wh];
            int b[] = new int[wh];
            int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
            int vmin[] = new int[Math.max(w, h)];

            int divsum = (div + 1) >> 1;
            divsum *= divsum;
            int dv[] = new int[256 * divsum];
            for (i = 0; i < 256 * divsum; i++) {
                dv[i] = (i / divsum);
            }

            yw = yi = 0;

            int[][] stack = new int[div][3];
            int stackpointer;
            int stackstart;
            int[] sir;
            int rbs;
            int r1 = radius + 1;
            int routsum, goutsum, boutsum;
            int rinsum, ginsum, binsum;

            for (y = 0; y < h; y++) {
                rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
                for (i = -radius; i <= radius; i++) {
                    p = pix[yi + Math.min(wm, Math.max(i, 0))];
                    sir = stack[i + radius];
                    sir[0] = (p & 0xff0000) >> 16;
                    sir[1] = (p & 0x00ff00) >> 8;
                    sir[2] = (p & 0x0000ff);
                    rbs = r1 - Math.abs(i);
                    rsum += sir[0] * rbs;
                    gsum += sir[1] * rbs;
                    bsum += sir[2] * rbs;
                    if (i > 0) {
                        rinsum += sir[0];
                        ginsum += sir[1];
                        binsum += sir[2];
                    } else {
                        routsum += sir[0];
                        goutsum += sir[1];
                        boutsum += sir[2];
                    }
                }
                stackpointer = radius;

                for (x = 0; x < w; x++) {

                    r[yi] = dv[rsum];
                    g[yi] = dv[gsum];
                    b[yi] = dv[bsum];

                    rsum -= routsum;
                    gsum -= goutsum;
                    bsum -= boutsum;

                    stackstart = stackpointer - radius + div;
                    sir = stack[stackstart % div];

                    routsum -= sir[0];
                    goutsum -= sir[1];
                    boutsum -= sir[2];

                    if (y == 0) {
                        vmin[x] = Math.min(x + radius + 1, wm);
                    }
                    p = pix[yw + vmin[x]];

                    sir[0] = (p & 0xff0000) >> 16;
                    sir[1] = (p & 0x00ff00) >> 8;
                    sir[2] = (p & 0x0000ff);

                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];

                    rsum += rinsum;
                    gsum += ginsum;
                    bsum += binsum;

                    stackpointer = (stackpointer + 1) % div;
                    sir = stack[(stackpointer) % div];

                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];

                    rinsum -= sir[0];
                    ginsum -= sir[1];
                    binsum -= sir[2];

                    yi++;
                }
                yw += w;
            }
            for (x = 0; x < w; x++) {
                rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
                yp = -radius * w;
                for (i = -radius; i <= radius; i++) {
                    yi = Math.max(0, yp) + x;

                    sir = stack[i + radius];

                    sir[0] = r[yi];
                    sir[1] = g[yi];
                    sir[2] = b[yi];

                    rbs = r1 - Math.abs(i);

                    rsum += r[yi] * rbs;
                    gsum += g[yi] * rbs;
                    bsum += b[yi] * rbs;

                    if (i > 0) {
                        rinsum += sir[0];
                        ginsum += sir[1];
                        binsum += sir[2];
                    } else {
                        routsum += sir[0];
                        goutsum += sir[1];
                        boutsum += sir[2];
                    }

                    if (i < hm) {
                        yp += w;
                    }
                }
                yi = x;
                stackpointer = radius;
                for (y = 0; y < h; y++) {
                    // Preserve alpha channel: ( 0xff000000 & pix[yi] )
                    pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16) | (dv[gsum] << 8) | dv[bsum];

                    rsum -= routsum;
                    gsum -= goutsum;
                    bsum -= boutsum;

                    stackstart = stackpointer - radius + div;
                    sir = stack[stackstart % div];

                    routsum -= sir[0];
                    goutsum -= sir[1];
                    boutsum -= sir[2];

                    if (x == 0) {
                        vmin[y] = Math.min(y + r1, hm) * w;
                    }
                    p = x + vmin[y];

                    sir[0] = r[p];
                    sir[1] = g[p];
                    sir[2] = b[p];

                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];

                    rsum += rinsum;
                    gsum += ginsum;
                    bsum += binsum;

                    stackpointer = (stackpointer + 1) % div;
                    sir = stack[stackpointer];

                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];

                    rinsum -= sir[0];
                    ginsum -= sir[1];
                    binsum -= sir[2];

                    yi += w;
                }
            }

            bitmap.setPixels(pix, 0, w, 0, 0, w, h);

        } catch (Exception e) {
            Log.e(TAG, "Cannot blur image");
        } catch (Throwable e) {
            Log.e(TAG, "Cannot blur image, probably outofmemory");
        }

        return bitmap;
    }

    /**
     * Scale bitmap according to specific factor
     *
     * @param origin      the original bitmap
     * @param scaleFactor the factor
     * @return scaled bitmap by the giver factor
     */
    public static Bitmap scaleBitmap(Bitmap origin, int scaleFactor) {
        if (origin == null) {
            return null;
        }

        Bitmap scaledBitmap;
        scaledBitmap = Bitmap.createScaledBitmap(origin, origin.getWidth() / scaleFactor, origin.getHeight() / scaleFactor, true);
        return scaledBitmap;
    }

    public static final Bitmap decodeBitmap(String filePath, int targetW, int targetH) {
        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap src = BitmapFactory.decodeFile(filePath, bmOptions);
        Matrix matrix = getMatrixForImageOrientation(filePath);

        if (src != null) {
            return Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
        }

        return null;
    }

    public static Matrix getMatrixForImageOrientation(String src) {
        Matrix matrix = new Matrix();
        try {
            int orientation = getExifOrientation(src);
            switch (orientation) {
                case 2:
                    matrix.setScale(-1, 1);
                    break;
                case 3:
                    matrix.setRotate(180);
                    break;
                case 4:
                    matrix.setRotate(180);
                    matrix.postScale(-1, 1);
                    break;
                case 5:
                    matrix.setRotate(90);
                    matrix.postScale(-1, 1);
                    break;
                case 6:
                    matrix.setRotate(90);
                    break;
                case 7:
                    matrix.setRotate(-90);
                    matrix.postScale(-1, 1);
                    break;
                case 8:
                    matrix.setRotate(-90);
                    break;
                default:
                    return matrix;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return matrix;
    }

    private static int getExifOrientation(String src) throws Exception {
        ExifInterface exifInterface = new ExifInterface(src);
        return exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
    }

    public static int getScaleFactor(String filepath, int targetW, int targetH) {

        int inSampleSize = 1;

        // Get the dimensions of the image from EXIF
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filepath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        if (photoH > targetH || photoW > targetW) {

            // Calculate ratios of height and width to requested height and width
            final int heightRatio = Math.round((float) photoH / (float) targetH);
            final int widthRatio = Math.round((float) photoW / (float) targetW);

            // Choose the smallest ratio as inSampleSize value, this will guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

        return inSampleSize;

    }

    public static byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public static String savePhoto(Bitmap bitmap, Context context, String fileNameWithoutExtention) {
        byte[] arr = bitmapToByteArray(bitmap);
        return savePhoto(arr, context, fileNameWithoutExtention);
    }

    public static String savePhoto(byte[] data, Context context, String fileNameWithoutExtention) {
        File pictureFileDir = getDir();

        //make dir if not exists
        if (!pictureFileDir.exists() && !pictureFileDir.mkdirs()) {
            Log.d(TAG, "Can't create directory to save image.");
            return null;
        }
        String photoFile = "";
        if (TextUtils.isEmpty(fileNameWithoutExtention)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
            String date = dateFormat.format(new Date());
            photoFile = BitmapUtils.PICTURE_PREFIX + date + ".jpg";
        } else {
            photoFile = fileNameWithoutExtention + ".jpg";
        }

        String filename = pictureFileDir.getPath() + File.separator + photoFile;

        File pictureFile = new File(filename);

        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            fos.write(data);
            fos.close();
            Log.d(TAG, "New Image saved:" + photoFile);

            // Let media scanner scan the image so it will be immediately visible in the gallery
            MediaScannerConnection.scanFile(context, new String[]{filename}, null, null);

            return filename;
        } catch (Exception error) {
            Log.d(TAG, "File" + filename + "not saved: " + error.getMessage());

        }
        return null;
    }

    public static Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 0) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public static File getDir() {
        File sdDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        return new File(sdDir, APP_DIRECTORY);
    }

    public static Bitmap getBitmapFromView(View view, int width, int height) {
        Bitmap returnedBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        view.measure(View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY));
        view.layout(0, 0, width, height);
        view.draw(canvas);
        return returnedBitmap;
    }

    public static void openGallery(Fragment fragment, int requestCode) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            Intent getPictureIntent = new Intent();
            getPictureIntent.setType("image/*");
            getPictureIntent.setAction(Intent.ACTION_GET_CONTENT);
            fragment.startActivityForResult(Intent.createChooser(getPictureIntent, "fragment.getResources().getString(R.string.ask_select_picture)"), requestCode);

        } else {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/jpeg");
            fragment.startActivityForResult(intent, requestCode);
        }
    }

    public static void openGallery(Activity activity, int requestCode) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            Intent getPictureIntent = new Intent();
            getPictureIntent.setType("image/*");
            getPictureIntent.setAction(Intent.ACTION_GET_CONTENT);
            activity.startActivityForResult(Intent.createChooser(getPictureIntent, "fragment.getResources().getString(R.string.ask_select_picture)"), requestCode);

        } else {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/jpeg");
            activity.startActivityForResult(intent, requestCode);
        }
    }

    public static synchronized void recycleBitmap(Bitmap bitmap) {
        Log.i(TAG, "recycleBitmap");
        try {
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
                bitmap = null;
            }
        } catch (Exception e) {
            Log.i(TAG, "Recycle failed");
        }

    }

    public static boolean createImageFromVideo(Bitmap bitmap, String toFile) {

        boolean succeeded = false;

        new File(toFile).getParentFile().mkdirs();

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(toFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            succeeded = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        recycleBitmap(bitmap);
        return succeeded;
    }

    //    public static Bitmap getImageFromVideo(String videoPath, int fromPosition) {
    //        FFmpegMediaMetadataRetriever mediaMetadataRetriever = new FFmpegMediaMetadataRetriever();
    //        mediaMetadataRetriever.setDataSource(videoPath);
    //        Bitmap bmFrame = mediaMetadataRetriever.getFrameAtTime(fromPosition * 1000, MediaMetadataRetriever.OPTION_CLOSEST); //unit in microsecond
    //        mediaMetadataRetriever.release();
    //        return bmFrame;
    //    }
    public static long getVideoDurationInMillis(String videoFile) {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(videoFile);
        String time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        long timeInmillisec = Long.parseLong(time);
        retriever.release();
        return timeInmillisec;
    }

    public static String getPathFromUri(Context context, Uri uri) {
        String result = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, proj, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndexOrThrow(proj[0]);
                result = cursor.getString(column_index);
            }
            cursor.close();
        }
        if (result == null) {
            result = "Not found";
        }
        return result;
    }

    public static int getImageHeight(int imgResource, Context context) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        //Returns null, sizes are in the options variable
        BitmapFactory.decodeResource(context.getResources(), imgResource, options);
        return options.outHeight;
    }

    public static int getImageWidth(int imgResource, Context context) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        //Returns null, sizes are in the options variable
        BitmapFactory.decodeResource(context.getResources(), imgResource, options);
        return options.outWidth;
    }

    public static double getImageRatio(int imgResource, Context context) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        //Returns null, sizes are in the options variable
        BitmapFactory.decodeResource(context.getResources(), imgResource, options);
        return (double) options.outWidth / (double) options.outHeight;
    }
}
