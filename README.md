# Evntaly Android SDK

## Configuration


### 1. Add Dependencies
```groovy
dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
```
Add the following to build.gradle
```groovy
dependencies {
    implementation 'com.github.evntaly:evntaly-android:Tag'
}
```
### 2. Intialize the SDK 
```java
EvntalySDK.init("token", "developersecret");
```
to enable sdk debug logs
```java
EvntalySDK.init("token", "developersecret", true);
```

### 3. Send an Event
```java
Evnt.Builder()
    .title("Test Event")
    .description("Test description")
    .build();
```

### 4. Identify User
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
