plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.rn.tuaobraparapedreiro"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.rn.tuaobraparapedreiro"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

val retrofit_version = "2.9.0"
val gson_version = "2.9.0"
val livedata_version = "2.8.6"
val viewmodel_version = "2.8.6"
val navigation_version = "2.8.1"
val navigation_ui_version = "2.8.1"
val appcompat_version = "1.7.0"
val material_version = "1.12.0"
val constraint_layout_version = "2.1.4"

dependencies {

    implementation("androidx.core:core-ktx:$retrofit_version")
    implementation("androidx.appcompat:appcompat:$appcompat_version")
    implementation("com.google.android.material:material:$material_version")
    implementation("androidx.constraintlayout:constraintlayout:$constraint_layout_version")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$livedata_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$viewmodel_version")
    implementation("androidx.navigation:navigation-fragment-ktx:$navigation_version")
    implementation("androidx.navigation:navigation-ui-ktx:$navigation_ui_version")
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.retrofit2:converter-gson:$gson_version")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}