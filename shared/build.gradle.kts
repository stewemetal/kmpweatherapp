import org.jetbrains.kotlin.gradle.dsl.KotlinCompile

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.ksp)
    alias(libs.plugins.native.coroutines)
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = libs.versions.java.get()
            }
        }
    }

    jvm("desktop") {
        jvmToolchain(libs.versions.java.get().toInt())
    }

    dependencies {
        add("kspCommonMainMetadata", libs.koin.ksp.compiler)
    }

    tasks.withType<KotlinCompile<*>>().configureEach {
        if (name != "kspCommonMainKotlinMetadata") {
            dependsOn("kspCommonMainKotlinMetadata")
        }
    }
    afterEvaluate {
        tasks.filter {
            it.name.contains("SourcesJar", true)
        }?.forEach {
            println("SourceJarTask====>${it.name}")
            it.dependsOn("kspCommonMainKotlinMetadata")
        }
    }

    listOf(
        iosSimulatorArm64(),
        iosX64(),
        iosArm64(),
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }


    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.bundles.ktor.shared)
                implementation(libs.koin.core)
                implementation(libs.koin.annotations)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.io.ktor.client.android)
                implementation(libs.koin.android)
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by getting {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(libs.io.ktor.client.darwin)
            }
        }
    }
}

android {
    namespace = "com.stewemetal.kmpweatherapp"
    compileSdk = 34
    defaultConfig {
        minSdk = 30
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
