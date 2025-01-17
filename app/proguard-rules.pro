## Add project specific ProGuard rules here.
## You can control the set of applied configuration files using the
## proguardFiles setting in build.gradle.
##
## For more details, see
##   http://developer.android.com/guide/developing/tools/proguard.html
#
## If your project uses WebView with JS, uncomment the following
## and specify the fully qualified class name to the JavaScript interface
## class:
##-keepclassmembers class fqcn.of.javascript.interface.for.webview {
##   public *;
##}
#
## Uncomment this to preserve the line number information for
## debugging stack traces.
##-keepattributes SourceFile,LineNumberTable
#
## If you keep the line number information, uncomment this to
## hide the original source file name.
#-renamesourcefileattribute SourceFile
#
## Retrofit 2.X
### https://square.github.io/retrofit/ ##
#
#-dontwarn retrofit2.**
#-keep class retrofit2.** { *; }
#-keepattributes Signature
#-keepattributes Exceptions
#
#-keepclasseswithmembers class * {
#    @retrofit2.http.* <methods>;
#}
#-dontwarn okio.**
#
#
## OkHttp
#-keepattributes Signature
#-keepattributes *Annotation*
#-keep class okhttp3.** { *; }
#-keep interface okhttp3.** { *; }
#-dontwarn okhttp3.**
#
#
#-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
#public static java.lang.String TABLENAME;
#}
#-keep class **$Properties
#
## If you do not use SQLCipher:
#-dontwarn org.greenrobot.greendao.database.**
## If you do not use Rx:
#-dontwarn rx.**
#
#
## Kryo
#-dontwarn com.esotericsoftware.kryo.**
#-dontwarn org.objenesis.instantiator.sun.**
#-keep class com.esotericsoftware.** { *; }