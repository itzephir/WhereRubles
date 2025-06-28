# Keep the serializer classes
-keepclassmembers class ** {
    @kotlinx.serialization.Serializable <fields>;
}

# Keep the generated serializers for all your Serializable classes
-keepclassmembers class **$* {
    public static ** serializer(...);
}

# Keep all classes annotated with @Serializable
-keep @kotlinx.serialization.Serializable class ** { *; }

# Keep Kotlin Metadata (for reflection)
-keep class kotlinx.serialization.** { *; }
-keep class kotlin.Metadata { *; }