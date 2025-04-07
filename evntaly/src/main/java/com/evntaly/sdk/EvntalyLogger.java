package com.evntaly.sdk;

import android.util.Log;

public class EvntalyLogger {
    private static boolean isDebugEnabled = false;
    private static final String TAG = "Evntaly";

    public static void enableDebug(boolean enabled) {
        isDebugEnabled = enabled;
    }

    public static void d(String message) {
        if (isDebugEnabled) Log.d(TAG, decorate("💬 DEBUG", message));
    }

    public static void i(String message) {
        if (isDebugEnabled) Log.i(TAG, decorate("ℹ️ INFO", message));
    }

    public static void w(String message) {
        if (isDebugEnabled) Log.w(TAG, decorate("⚠️ WARN", message));
    }

    public static void e(String message, Throwable throwable) {
        if (isDebugEnabled) Log.e(TAG, decorate("❌ ERROR", message), throwable);
    }    
    
    public static void e(String message) {
        if (isDebugEnabled)  Log.e(TAG, decorate("❌ ERROR", message));
    }

    public static void json(String label, String json) {
        if (!isDebugEnabled) return;
        try {
            org.json.JSONObject obj = new org.json.JSONObject(json);
            Log.d(TAG, decorate("📦 " + label, obj.toString(2)));
        } catch (Exception e) {
            Log.d(TAG, decorate("📦 " + label, json));
        }
    }

    private static String decorate(String prefix, String message) {
        return "\n======== [" + prefix + " - Evntaly SDK] ========\n" +
                message + "\n" +
                "==============================================";
    }

    public static String prettyJson(String raw) {
        try {
            raw = raw.trim();
            if (raw.startsWith("{")) {
                org.json.JSONObject json = new org.json.JSONObject(raw);
                return json.toString(2);
            } else if (raw.startsWith("[")) {
                org.json.JSONArray json = new org.json.JSONArray(raw);
                return json.toString(2);
            } else {
                return raw; // Not a JSON
            }
        } catch (Exception e) {
            return raw; // Return raw if it's not valid JSON
        }
    }
}
