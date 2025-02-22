# Core Android
-keep class androidx.lifecycle.ViewModel
-keep class androidx.lifecycle.ViewModel$OnRequery
-keep class androidx.lifecycle.ViewModelProvider
-keep class androidx.lifecycle.ViewModelStore

# Hilt
-keep class dagger.hilt.** { *; }
-keep class * extends dagger.hilt.android.internal.managers.ViewComponentManager { *; }
-keep class * extends dagger.hilt.internal.GeneratedComponentManagerHolder { *; }
-keepclassmembers class * {@dagger.hilt.* *;}

# Retrofit
-keep class com.squareup.okhttp3.** { *; }
-keep interface okhttp3.logging.HttpLoggingInterceptor$Logger
-keep class retrofit2.** { *; }
-keepclasseswithmembers class * { @retrofit2.http.* <methods>; }

# Moshi
-keep class com.squareup.moshi.** { *; }
-keepclasseswithmembers class * { @com.squareup.moshi.JsonClass <methods>; }
-keep class * extends com.squareup.moshi.JsonAdapter

# Room
-keep class * extends androidx.room.RoomDatabase
-keep class * extends androidx.room.Entity
-keep class * implements androidx.room.Dao

# Navigation Component
-keep class androidx.navigation.** { *; }
-keep class * extends androidx.navigation.fragment.NavHostFragment

# Compose
-keep class androidx.compose.runtime.** { *; }
-keep class androidx.compose.ui.** { *; }
-keep class androidx.compose.material3.** { *; }
-keep class com.google.accompanist.** { *; }

# Glide
-keep public class * implements com.bumptech.glide.module.AppGlideModule
-keep class * extends com.bumptech.glide.module.AppGlideModule
-keep class com.bumptech.glide.** { *; }

# GSON
-keep class com.google.gson.** { *; }
-keep class * implements com.google.gson.TypeAdapter

# Coil
-keep class coil.** { *; }
-keep class com.google.accompanist.coil.** { *; }

# Arrow
-keep class arrow.** { *; }

# DataStore
-keep class androidx.datastore.** { *; }

# View Binding
-keep class * extends androidx.viewbinding.ViewBinding {
    public static * inflate(android.view.LayoutInflater);
}

# Google Maps
-keep class com.google.android.gms.maps.** { *; }
-keep class com.google.maps.android.** { *; }

# Lifecycle
-keep class androidx.lifecycle.** { *; }

# JSR 305
-dontwarn javax.annotation.**

# OkHttp
-keepattributes Signature
-keepattributes *Annotation*
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }

# Coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineDispatcher
-keepnames class kotlinx.coroutines.android.AndroidDispatcherFactory

# Jetpack WindowManager
-keep class androidx.window.** { *; }

# Para mantener clases de tu aplicación
# Application classes that will be serialized/deserialized over Gson
-keep class com.juanzurita.framework.remote.ads.models.**{*;}
