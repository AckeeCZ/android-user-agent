[ ![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.ackeecz/user-agent/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.ackeecz/user-agent)

# Android User Agent Library
Library that generates User-Agent string for HTTP requests. 

## Purpose of this library
In Ackee, we need more info in User-Agent header than standard `System.getProperty("http.agent")` provides and purpose of this library is to provide better one. 
The default format of our User-Agent is:
```
application_name/application_version (package_name; build:version_code; Android android_version; Model:device_model) network_client_user_agent
```
so example of one of our application
```
Tapito/2.5.8 (com.tapmedia.tapito.debug; build:1520; Android 7.0; Model:Nexus 5X) okhttp/3.2.0
```

## Dependencies
```groovy
implementation "io.github.ackeecz:user-agent:x.x.x"
```

### Usage
Usage is very simple, just create an instance of `UserAgent` class with an optional `Normalizer` 
instance for a custom normalization. Library already provides a few `Normalizer` implementations
for a quick setup. 

Because of an abstraction from the HTTP library you use, there is a need for passing the user agent 
string of this library. We use practically everywhere OkHttp but who knows. 

Example in OkHttp interceptor:

```kotlin
// Create the instance somewhere. Probably outside of the interceptor proceed call.
val userAgent = UserAgent(context)

val request = chain.request()
                  .newBuilder()
                  .header("User-Agent", userAgent.getUserAgentString(Version.userAgent()))
                  .build()
```

`Version.userAgent()` is getter for OkHttp string like `okhttp/3.2.0`

## License
Copyright 2023 Ackee, s.r.o.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

