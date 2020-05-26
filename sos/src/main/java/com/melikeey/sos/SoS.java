package com.melikeey.sos;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SoS {

    public static void showToast(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void logError(String message){
        Log.e("Error", message);
    }

    public static void logVerbose(String verbose) {
        Log.e("Verbose", verbose);
    }

    public static Intent getEmailIntent(String[] to, String subject, String content, String mailTo, String chooserTitle) {

        Intent emailIntent = new Intent(Intent.ACTION_VIEW);

        emailIntent.setData(Uri.parse(mailTo));
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, to);
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, content);

        return  Intent.createChooser(emailIntent, chooserTitle);
    }

    public static void showTextDialog(Context context, String message) {
        new AlertDialog.Builder(context).setMessage(message).show();
    }

    public static void showTextDialog(Context context, String message, String title) {
        new AlertDialog.Builder(context).setMessage(message).setTitle(title).show();
    }

    public static void showTextDialog(Context context, String message, String positiveButtonText, DialogInterface.OnClickListener positiveButtonClickListener, boolean cancelable) {
        new AlertDialog.Builder(context).setMessage(message).setPositiveButton(positiveButtonText, positiveButtonClickListener).setCancelable(cancelable).show();
    }

    public static void showTextDialog(Context context, String message, String title, String positiveButtonText, DialogInterface.OnClickListener positiveButtonClickListener, boolean cancelable) {
        new AlertDialog.Builder(context).setMessage(message).setTitle(title).setPositiveButton(positiveButtonText, positiveButtonClickListener).setCancelable(cancelable).show();
    }

    public static void showTextDialog(Context context, String message, String positiveButtonText, DialogInterface.OnClickListener positiveButtonClickListener, String negativeButtonText,
                                      DialogInterface.OnClickListener negativeButtonClickListener, boolean cancelable) {
        new AlertDialog.Builder(context).setMessage(message).setPositiveButton(positiveButtonText, positiveButtonClickListener).setNegativeButton(negativeButtonText,
                negativeButtonClickListener).setCancelable(cancelable).show();
    }

    public static void showTextDialog(Context context, String message, String title, String positiveButtonText, DialogInterface.OnClickListener positiveButtonClickListener, String negativeButtonText,
                                      DialogInterface.OnClickListener negativeButtonClickListener, boolean cancelable) {
        new AlertDialog.Builder(context).setMessage(message).setTitle(title).setPositiveButton(positiveButtonText, positiveButtonClickListener).setNegativeButton(negativeButtonText,
                negativeButtonClickListener).setCancelable(cancelable).show();
    }

    public static void openNavigationChooserDialog(Context context, String uriString) {

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uriString));
        intent.setFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
        intent.setFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
        intent.setData(Uri.parse(uriString));

        context.startActivity(intent);
    }

    private static void setPickerDialogLanguage(Context context, String language) {

        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config, null);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static Object convertJsonStringToPOJO(InputStream json, Type objectName) {

        Object jsonObject = null;

        try (Reader reader = new InputStreamReader(json, StandardCharsets.UTF_8)) {
            Gson gson = new GsonBuilder().create();
            jsonObject = gson.fromJson(reader, objectName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    public static Object convertJsonStringToPOJO(String json, Type objectName) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(json, objectName);
    }

    public static String convertPOJOToJsonString(Object pojo) {
        Gson gson = new GsonBuilder().create();

        return gson.toJson(pojo);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static InputStream stringToInputStream(String str) {

        if (str == null) {
            str = "";
        }

        return new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8));
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String loadJSONFromAsset(Context context, String fileName) {

        String json;

        try {

            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }

    public static HashMap<String, Object> parseJSONObjectToMap(JSONObject jsonObject) throws JSONException {

        HashMap<String, Object> mapData = new HashMap<>();
        Iterator<String> keysItr = jsonObject.keys();

        while (keysItr.hasNext()) {

            String key = keysItr.next();
            Object value = jsonObject.get(key);

            if (value instanceof JSONArray) {
                value = parseJSONArrayToList((JSONArray) value);

            } else if (value instanceof JSONObject) {
                value = parseJSONObjectToMap((JSONObject) value);
            }

            mapData.put(key, value);
        }

        return mapData;
    }

    public static List<Object> parseJSONArrayToList(JSONArray array) throws JSONException {

        List<Object> list = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            Object value = array.get(i);

            if (value instanceof JSONArray) {

                value = parseJSONArrayToList((JSONArray) value);
            } else if (value instanceof JSONObject) {

                value = parseJSONObjectToMap((JSONObject) value);
            }

            list.add(value);
        }

        return list;
    }

    public static HashMap<String, Object> convertJsonStringToMap(String jsonString) {

        HashMap<String, Object> map = new HashMap<>();

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            map = SoS.parseJSONObjectToMap(jsonObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return map;
    }

    public static Object castJsonObjectToPOJO(JSONObject jsonObject, Type objectType) {

        Gson gson = new Gson();
        return gson.fromJson(jsonObject.toString(), objectType);
    }
    public static boolean isNetworkConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public static String[] splitString(String input, String regex) {
        return input.split(regex);
    }

    public static boolean isEmailValid(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static String fromCamelCaseToSnakeCase(String key) {
        return key.replaceAll("(.)(\\p{Upper})", "$1_$2").toLowerCase();
    }
}
