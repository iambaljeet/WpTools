plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(Apps.compileSdk)

    defaultConfig {
        applicationId = Apps.applicationId
        minSdkVersion(Apps.minSdk)
        targetSdkVersion(Apps.targetSdk)
        versionCode = Apps.versionCode
        versionName = Apps.versionName

        testInstrumentationRunner = Apps.testInstrumentation
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
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
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Android.kotlin)
    implementation(Android.kotlinJdk7)

    implementation(Room.room)
    kapt(Room.roomRuntime)

    implementation(AndroidX.constraintLayout)
    implementation(AndroidX.lifecycle)
    implementation(AndroidX.liveData)
    implementation(AndroidX.material)
    implementation(AndroidX.navigation)
    implementation(AndroidX.navigationUi)
    implementation(AndroidX.recyclerView)
    implementation(AndroidX.runtime)
    implementation(AndroidX.supportV4)
    implementation(AndroidX.viewModel)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.coreKtx)

    implementation(Gson.gson)

    testImplementation(Testing.espresso)
    testImplementation(Testing.jUnit)
    testImplementation(Testing.jUnitExt)
}