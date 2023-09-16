plugins {
    id("com.android.application")
    kotlin("android")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.stewemetal.kmpweatherapp.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.stewemetal.kmpweatherapp.android"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = libs.versions.java.get()
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(libs.bundles.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.activity.compose)

    implementation(libs.accompanist.permissions)

    implementation(libs.play.services.location)

    implementation(libs.bundles.compose)

    implementation(libs.bundles.koin)
    implementation(libs.koin.annotations)
    ksp(libs.koin.ksp.compiler)
}
