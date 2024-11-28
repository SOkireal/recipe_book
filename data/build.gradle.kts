plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 27

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

val retrofitVersion = "2.11.0"
val coroutineVersion = "1.6.2"
val roomVersion = "2.6.1"

val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
val coroutineStdLib = "org.jetbrains.kotlin:kotlin-stdlib"
val coroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion"
val room = "androidx.room:room-runtime:$roomVersion"
val roomCompiler = "androidx.room:room-compiler:$roomVersion"

dependencies {
    implementation(project(":domain"))
    implementation(coroutineStdLib)
    implementation(coroutineCore)
    implementation(retrofit)
    implementation(retrofitConverterGson)
    implementation(room)
    kapt(roomCompiler)
    implementation("androidx.room:room-ktx:$roomVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.10.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:4.13.2")
}
