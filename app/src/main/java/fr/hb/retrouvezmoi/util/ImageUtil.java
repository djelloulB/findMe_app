package fr.hb.retrouvezmoi.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

public class ImageUtil {
    public static String convert(Bitmap bitmap) {
        String encodedStr = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            encodedStr = Base64.getEncoder().encodeToString(outputStream.toByteArray());

        } else {
            encodedStr = android.util.Base64.encodeToString(outputStream.toByteArray(), android.util.Base64.DEFAULT);
        }
        return encodedStr;
    }

    public static Bitmap convert(String base64Str) {
        byte[] bytes = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            bytes = Base64.getDecoder().decode(base64Str);
        } else {
            bytes = android.util.Base64.decode(base64Str, android.util.Base64.DEFAULT);
        }
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
