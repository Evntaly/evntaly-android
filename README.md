# Evntaly Android SDK

## Configuration
1. Intialize the SDK 
```java
EvntalySDK.init("token", "developersecret");
```
to enable sdk debug logs
```java
EvntalySDK.init("token", "developersecret", true);
```


2. Send an Event
```java
Evnt.Builder()
    .title("Test Event")
    .description("Test description")
    .build();
```

3. Identify User
```java
EvntalySDK.identifyUser(
        EvntalyUser.Builder()
            .setId("0f6934fd-99c0-41ca-3333")
            .setEmail("Alameer@evntaly.com")
            .setFullName("Alameer Ashraf")
            .setOrganization("Evntaly")
            .setData(mapOf(
                "Location" to "Egypt",
                "timezone" to "Africa/Cairo"
            ))
            .build();
)
```

## Add OkHttp Library
In your app's build.gradle.kts file, add the following dependency:

```groovy
    implementation "com.squareup.okhttp3:okhttp:4.12.0"
```
