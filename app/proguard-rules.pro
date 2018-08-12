# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-libraryjars libs/android-support-v4.jar
-libraryjars libs/core.jar

-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

-dontwarn android.support.v4.**

-keep class android.support.v4.app.** { *; }

-keep interface android.support.v4.app.** { *; }

-keep class com.actionbarsherlock.** { *; }

-keep interface com.actionbarsherlock.** { *; }

-keepattributes *Annotation*

-keep public class * extends android.app.Activity

-keep public class * extends android.support.v4.app.Fragment

-keep public class * extends android.support.v4.app.FragmentActivity