package com.evntaly.sdk;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class EvntalySDK {
    private static String projectToken;
    private static String developerSecret;
    private static final String baseUrl = "https://app.evntaly.com/prod";
    private static final String eventEndpoint = "/api/v1/register/event";
    private static final String userEndpoint = "/api/v1/register/user";
    private static OkHttpClient client;
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static void init(String projectToken, String developerSecret, boolean enableLogs) {
        enableDebugLogs(enableLogs);
        init(projectToken, developerSecret);
    }

    public static void init(String projectToken, String developerSecret) {
        if (projectToken == null || projectToken.isEmpty()) {
            EvntalyLogger.e("Project token cannot be null or empty");
            return;
        }
        if (developerSecret == null || developerSecret.isEmpty()) {
            EvntalyLogger.e("Developer secret cannot be null or empty");
            return;
        }

        EvntalySDK.projectToken = projectToken;
        EvntalySDK.developerSecret = developerSecret;
        client = new OkHttpClient();
        EvntalyLogger.i("Evntaly initialized 🚀");
    }

    public static void enableDebugLogs(boolean enabled) {
        EvntalyLogger.enableDebug(enabled);
    }

    public static void sendEvent(Evnt evnt) {
        try {
            EvntalyLogger.d("Calling sendEvent() API");

            if (projectToken == null || developerSecret == null) {
                EvntalyLogger.e("SDK not initialized. Call init() first.");
                return;
            }

            if (evnt == null) {
                EvntalyLogger.e("Event cannot be null");
                return;
            }

                String json = evnt.toJson();
                RequestBody body = RequestBody.create(json, JSON);

                Request request = new Request.Builder()
                        .url(baseUrl + eventEndpoint)
                        .addHeader("pat", projectToken)
                        .addHeader("secret", developerSecret)
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        EvntalyLogger.e("Failed to send event: " + e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseBody = response.body().string();
                        if (response.isSuccessful()) {
                            EvntalyLogger.d("Event sent successfully: " + responseBody + "\n" + EvntalyLogger.prettyJson(responseBody));
                        } else {
                            EvntalyLogger.e("Failed to send event. Status code: " + response.code() + "\n" + EvntalyLogger.prettyJson(responseBody));
                        }
                    }
                });
        } catch (Exception e) {
                EvntalyLogger.e("Error preparing event: " + e.getMessage());
            // silent fail, already error logs are logged.
        }
    }

    public static void identifyUser(EvntalyUser user) {
        try {
            EvntalyLogger.d("Calling identifyUser() API");

            if (projectToken == null || developerSecret == null) {
                EvntalyLogger.e("SDK not initialized. Call init() first.");
                return;
            }

            if (user == null) {
                EvntalyLogger.e("User cannot be null");
                return;
            }

            final JSONObject jsonObject = new JSONObject();

            if (user.id != null) jsonObject.put("id", user.id);
            if (user.email != null) jsonObject.put("email", user.email);
            if (user.fullName != null) jsonObject.put("full_name", user.fullName);
            if (user.organization != null) jsonObject.put("organization", user.organization);

            if (user.data != null && !user.data.isEmpty()) {
                final JSONObject dataObject = new JSONObject();
                for (Map.Entry<String, Object> entry : user.data.entrySet()) {
                    dataObject.put(entry.getKey(), entry.getValue());
                }
                jsonObject.put("data", dataObject);
            }

            RequestBody body = RequestBody.create(jsonObject.toString(), JSON);
            Request request = new Request.Builder()
                    .url(baseUrl + userEndpoint)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("pat", projectToken)
                    .addHeader("secret", developerSecret)
                    .post(body)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    EvntalyLogger.e("Failed to identifyUser: " + e.getMessage());
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    String responseBody = response.body().string();
                    if (response.isSuccessful()) {
                        EvntalyLogger.d("Event sent successfully: " + responseBody + "\n" + EvntalyLogger.prettyJson(responseBody));
                    } else {
                        EvntalyLogger.e("Failed to send event. Status code: " + response.code() + "\n" + EvntalyLogger.prettyJson(responseBody));
                    }
                }
            });

        } catch (JSONException e) {
            EvntalyLogger.e("Something went wrong while identifying user", e);
        }
    }
}