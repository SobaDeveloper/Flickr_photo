import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}

val localProperties = Properties().apply {
    load(rootProject.file("local.properties").inputStream())
}
val flickrBaseUrl = localProperties["FLICKR_BASE_URL"] as String
val flickrApiKey = localProperties["FLICKR_API_KEY"] as String

android {
    namespace = "com.example.core"
    compileSdk = 36

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "FLICKR_BASE_URL", "\"$flickrBaseUrl\"")
        buildConfigField("String", "FLICKR_API_KEY", "\"$flickrApiKey\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Koin
    api(libs.koin.android)

    // Moshi
    api(libs.moshi)
    api(libs.moshi.kotlin)
    ksp(libs.moshi.kotlin.codegen)
}