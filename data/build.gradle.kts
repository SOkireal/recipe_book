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
val lifecycleLiveDataVersion = "2.8.7"
val okHttpLoggingInterceptorVersion = "4.10.0"
val junitVersion = "4.13.2"
val junitJupiterVersion = "5.8.2"
val mockitoVersion = "5.4.0"

val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
val coroutineStdLib = "org.jetbrains.kotlin:kotlin-stdlib"
val coroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion"
val roomRuntime = "androidx.room:room-runtime:$roomVersion"
val roomCompiler = "androidx.room:room-compiler:$roomVersion"
val roomKtx = "androidx.room:room-ktx:$roomVersion"
val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleLiveDataVersion"
val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$okHttpLoggingInterceptorVersion"
val junit = "junit:junit:$junitVersion"
val junitJupiter = "org.junit.jupiter:junit-jupiter:$junitJupiterVersion"
val mockitoCore = "org.mockito:mockito-core:$mockitoVersion"
val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:$mockitoVersion"

dependencies {
    implementation(project(":domain"))
    implementation(coroutineStdLib)
    implementation(coroutineCore)
    implementation(retrofit)
    implementation(retrofitConverterGson)
    implementation(roomRuntime)
    kapt(roomCompiler)
    implementation(roomKtx)
    implementation(lifecycleLiveData)
    implementation (okHttpLoggingInterceptor)
    testImplementation(junit)
    testImplementation(junitJupiter)
    testImplementation(mockitoCore)
    testImplementation(mockitoKotlin)
}
