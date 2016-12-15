# Android User Agent Android Library
Library that generates User-Agent string for http requests. 

## Purpose of this library
In Ackee, we need more info in User-Agent header than standard `System.getProperty("http.agent")` provides and purpose of this library is to provide better one. 
The format of our User-Agent is 
```
application_name/application_version (package_name; build:version_code; Android android_version; Model:device_model) network_client_user_agent
```
so example of one of our application
```
Tapito/2.5.8 (com.tapmedia.tapito.debug; build:1520; Android 7.0; Model:Nexus 5X) okhttp/3.2.0
```

## Dependencies
```groovy
compile "cz.ackee:user-agent:1.0.0"
```

### Usage
Usage is very simple, basically this whole library is just one singleton class. Because of abstraction from http library you use there is need for passing user agent string of this library. We are practically everywhere using OkHttp but who knows. 

Example in OkHttp interceptor 
```java
Request request = chain.request()
                      .newBuilder()
                      .header("User-Agent", UserAgent.getInstance(App.getInstance()).getUserAgentString(Version.userAgent()))
                      .build();
```

`Version.userAgent()` is getter for OkHttp string like `okhttp/3.2.0`

