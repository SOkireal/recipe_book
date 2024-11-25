plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.recipebook"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.recipebook"
        minSdk = 27
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
    }
}

val daggerVersion = "2.52"
val coreKtxVersion = "1.9.0"
val navigationKtx = "2.8.3"
val appcompatVersion = "1.7.0"
val materialVersion = "1.12.0"
val constraintLayoutVersion = "2.1.4"
val junitVersion = "4.13.2"
val junitTestVersion = "1.2.1"
val espressoVersion = "3.6.1"
val glideVersion = "4.16.0"


val dagger = "com.google.dagger:dagger:$daggerVersion"
val daggerCompiler = "com.google.dagger:dagger-compiler:$daggerVersion"
val coreKtx = "androidx.core:core-ktx:$coreKtxVersion"
val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:$navigationKtx"
val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:$navigationKtx"
val appcompat = "androidx.appcompat:appcompat:$appcompatVersion"
val material = "com.google.android.material:material:$materialVersion"
val constraintLayout = "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"
val javaxInject = "javax.inject:javax.inject:1"
val junit = "junit:junit:$junitVersion"
val junitTest = "androidx.test.ext:junit:$junitTestVersion"
val espresso = "androidx.test.espresso:espresso-core:$espressoVersion"
val glide = "com.github.bumptech.glide:glide:$glideVersion"

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(coreKtx)
    implementation(appcompat)
    implementation(material)
    implementation(constraintLayout)
    implementation(navigationFragmentKtx)
    implementation(navigationUiKtx)
    implementation(dagger)
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    kapt(daggerCompiler)
    implementation(javaxInject)
    testImplementation(junit)
    androidTestImplementation(junitTest)
    androidTestImplementation(espresso)
    implementation (glide)
}
