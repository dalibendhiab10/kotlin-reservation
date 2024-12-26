plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt") // Add this line

}

android {
    namespace = "com.example.mykotlin"
    compileSdk = 35  // Update from 34 to 35

    defaultConfig {
        applicationId = "com.example.mykotlin"
        minSdk = 24
        //noinspection EditedTargetSdkVersion
        targetSdk = 35  // Set target SDK to 35
        versionCode = 1
        versionName = "1.0"
        buildConfigField("String", "BASE_URL", "\"http://192.168.1.79:8080/\"")

    }

    buildFeatures {
        compose = true // Enable Jetpack Compose
        buildConfig = true

    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3" // Use the latest stable version
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    // Core dependencies
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.navigation:navigation-runtime-ktx:2.8.5")
    implementation("androidx.navigation:navigation-compose:2.8.5")
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    val composeVersion = "1.5.1" // Ensure you use the correct and compatible version
    val lifecycleVersion = "2.6.2"
    val activityComposeVersion = "1.7.2"

    // Core Compose dependencies
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.material3:material3:1.2.0-alpha06")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
    implementation("androidx.activity:activity-compose:$activityComposeVersion")

    // Material Icons
    implementation("androidx.compose.material:material-icons-core:$composeVersion") // Core Icons
    implementation("androidx.compose.material:material-icons-extended:$composeVersion") // Extended Icons

    // Debugging tools for Compose
    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeVersion")

    // Test dependencies
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

    //////API handling
    // Retrofit for API calls
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // OkHttp for logging network calls
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")

    ////gif
    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation ("com.github.bumptech.glide:glide:4.15.1") // Glide dependency
    kapt ("com.github.bumptech.glide:compiler:4.15.1") // Glide annotation processor

}
