# Seeds Android SDK
[View Seeds' documentation](https://developers.playseeds.com/docs/android-sdk-setup.html)


[Seeds](http://www.playseeds.com) increases paying user conversion for freemium mobile games motivating users to make their first purchase by letting them know that their purchase will help finance microloans in the developing world. The SDK implements this with an interstitial ad and event tracking analytics.

## The following platforms are now available:
- [Unity SDK](https://github.com/therealseeds/seeds-sdk-unity)
- [iOS](https://github.com/therealseeds/seeds-sdk-ios)
- [Android](https://github.com/therealseeds/seeds-sdk-android)
- [API](https://github.com/therealseeds/seeds-public-api)

## Pull requests welcome
We're built on open source and welcome bug fixes and other contributions.

## Download

Gradle:
```groovy
repositories {
  jcenter()
}

dependencies {
  compile 'com.playseeds.android-sdk:0.5.0'
}
```
Or Maven:
```xml
<dependency>
  <groupId>com.playseeds</groupId>
  <artifactId>android-sdk</artifactId>
  <version>0.4.3</version>
  <type>pom</type>
</dependency>
```
Seeds requires at minimum Android 4.1.

## ProGuard

If you are using ProGuard you might need to add the following options (currently SDK do not support ProGuard):
```
-keep com.playseeds.**
```

## Usage
Seeds functionality is spread into two parts for more comfortable usage: [Interstitials](#interstitials_header) that represents all functionality that is connected to the advirtisments and [Events](#events_header) that represents logging analytics data.

### Initialization
It is better to initialize Seeds on the very beggining of the app start if you have an Application subclass with Seeds.init() in the onCreate(). Otherwise, it is okay to initialize the Seeds in any suitable place, but remember: **you will get an error if use the Seeds API before initialize the SDK**. App key, that is needed for the Seeds.init(), can be found in the [Seeds' Dashboard](https://developers.playseeds.com/index.html).

```java
Seeds.init(Context, APP_KEY);
```

### <a name="interstitials_header"></a>Seeds.Interstitials


#### General rules:
* InterstitialId is the id of the interstitial that you want to operate with;
* Context as String (if other isn't specified) is the context of the place (e.g. "level_1", "pause", "app_start"), that will be used as the main option for the interstitials in the future releases, thus it's optional, but suggested;
* Manual price is the localized price of the interstitial (if it exists).


- Set the listener to receive the callback about events from the SDK where you need to, using [InterstitialListener](#interstitials_listener) or InterstitialListenerAdapter with overridden methods (if you don't need to handle all events):
```java
Seeds.interstitials().setListener(InterstitialListener);
```
- Pre-load the interstital. There are two options: without manual price (price will be loaded from the Play Store) or with it:
```java
Seeds.interstitials().fetch(String interstitialId, @Nullable String context);
or
Seeds.interstitials().fetch(String idinterstitialId, @Nullable String context, String manualPrice);
```
- Check, if the interstitial is already loaded. Will return true or false:
```java
Seeds.interstitials().isLoaded(String id);
```
- Show the interstitial. **Please note, that onError() method of the listener will be called also if the interstitial wasn't previously fetched (loaded)**:
```java
Seeds.interstitials().show(String interstitialId, @Nullable String context);
```
#### <a name="interstitials_listener"></a>IterstitialsListener
The listener implementation contains five methods for treating different scenarios after the opening of an interstitial has been attempted. 
It operates with the objects-wrapper - [SeedsInterstitial](#seeds_interstitial) to share the info about interstitials. 
It's okay to set this listener in the any place you want and left the process of clearing it for the Seeeds, as we use [WeakReference](https://docs.oracle.com/javase/7/docs/api/java/lang/ref/WeakReference.html) for it, but if you want to be sure that there won't be any memory leaks, it's also okay to set the null to clear the listener.
```java
void onLoaded(SeedsInterstitial seedsInterstitial);
void onClick(SeedsInterstitial seedsInterstitial);
void onShown(SeedsInterstitial seedsInterstitial);
void onDismissed(SeedsInterstitial seedsInterstitial);
void onError(String interstitialId, Exception exception);
```


#### <a name="seeds_interstitial"></a>SeedsInterstitial
Object-wrapper for sharing info about interstitials. As for the last release, it contains:
* InterstitialId as String;
* Context as String, but this is optional, might be empty;
* Manual price as String, might be empty too.

### <a name="events_header"></a>Seeds.Events


Event in the Seeds is the generalized mechanism for tracking any usefull data and in-app purchases. If you want to keep your analytic IAP data safe and sound it's the place for you. **Seeds.Events** use two approaches for logging data: direct logging for the purchases, that were made in the app, and object-based approach for sharing any other data. 
It is reccomended to use **logSeedsIAPPayment()** or **logIAPPayment()** after any successful purchase and **logUser()** with the provided wrapper to share the existed user data that will help the Seeds' reccomendation mechanism in the future.
Also, there are additional way to log your app's custom data - **logEvent()**, that use object-base approach with custom attributes.

- Used to provide the info about in-app purchases with the Seeds goods included for the Seeds. **This method should be called after any sucessfull purchase in the app to help Seeds track the purchases and generate usefull tips, statistic and issue correct invoices.** Tracks the Seeds-promoted in-app purchases for trancaction with transactionId. Key is required here, as it's is the key of the purchase:
```java
Seeds.events().logSeedsIAPPayment(String key, double price, @Nullable String transactionId);
```
- Used to provide the info about other in-app purchases with for the Seeds. **This method should be called after any sucessfull purchase in the app to help Seeds track the purchases and generate usefull tips, statistic and issue correct invoices.** Tracks all other in-app purchases for trancaction with transactionId. Key is required here, as it's is the key of the purchase:
```java
Seeds.events().logIAPPayment(String key, double price, @Nullable String transactionId);
```
- Track userInfo. **UserInfo is the predefined wrapper, that provide all supported parameters**:
```java
Seeds.events().logUser(UserInfo info);
```
- Track common events using the predefined **Event** class or creating the subclass of it and using **putAttribute(String key, String value)**:
```java
Seeds.events().logEvent(Event event);
```

## License

    Copyright (c) 2012, 2013 Countly

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in
    all copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
    THE SOFTWARE.
