plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.dime"
        minSdk = 23
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0"
    }

    packagingOptions {
        resources {
            excludes.add("META-INF/DEPENDENCIES")
            excludes.add("META-INF/NOTICE")
            excludes.add("META-INF/LICENSE")
        }
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.0")

    // Jetpack Compose
    implementation("androidx.compose.ui:ui:1.4.0")
    implementation("androidx.compose.material:material:1.4.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.1")

    // Room Database
    implementation("androidx.room:room-ktx:2.5.0")
    kapt("androidx.room:room-compiler:2.5.0")

    // CameraX
    implementation("androidx.camera:camera-camera2:1.2.0")
    implementation("androidx.camera:camera-lifecycle:1.2.0")
    implementation("androidx.camera:camera-view:1.2.0")

    // Location
    implementation("com.google.android.gms:play-services-location:21.0.1")

    // Other dependencies as needed
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("com.google.android.material:material:1.8.0")
}

kapt {
    correctErrorTypes = true
}