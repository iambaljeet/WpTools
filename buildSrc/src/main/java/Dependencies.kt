object Apps {
    const val applicationId = "com.app.whatsapptools"
    const val compileSdk = 30
    const val minSdk = 21
    const val targetSdk = 30
    const val versionCode = 1
    const val versionName = "1.0.0"
    const val testInstrumentation = "androidx.test.runner.AndroidJUnitRunner"
}
object App {
    const val buildGradle = "com.android.tools.build:gradle:${Versions.gradle_version}"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_version}"
}
object Versions {
    const val gradle_version = "4.0.1"
    const val kotlin_version = "1.4.10"
    const val core_ktx_version = "1.3.1"
    const val app_compat_version = "1.2.0"
    const val support_version = "1.0.0"
    const val constraint_layout_version = "2.0.1"
    const val recycler_view_version = "1.1.0"
    const val material_design_version = "1.2.1"
    const val lifecycle_extensions_version = "2.2.0"
    const val room_version = "2.2.5"
    const val nav_version = "2.3.0"
    const val gson_version = "2.8.6"

    const val junit_version = "4.13"
    const val junit_ext_version = "1.1.2"
    const val espresso_core_version = "3.3.0"
}
object Android {
    const val kotlinJdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin_version}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin_version}"
}
object Room {
    const val room = "androidx.room:room-runtime:${Versions.room_version}"
    const val roomRuntime = "androidx.room:room-compiler:${Versions.room_version}"
}
object AndroidX {
    const val coreKtx = "androidx.core:core-ktx:${Versions.core_ktx_version}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.app_compat_version}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout_version}"
    const val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle_extensions_version}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_extensions_version}"
    const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle_extensions_version}"
    const val navigation = "androidx.navigation:navigation-fragment-ktx:${Versions.nav_version}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.nav_version}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle_extensions_version}"
    const val supportV4 = "androidx.legacy:legacy-support-v4:${Versions.support_version}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recycler_view_version}"
    const val material = "com.google.android.material:material:${Versions.material_design_version}"
}
object Gson {
    const val gson = "com.google.code.gson:gson:${Versions.gson_version}"
}
object Testing {
    const val jUnit = "junit:junit:${Versions.junit_version}"
    const val jUnitExt = "androidx.test.ext:junit:${Versions.junit_ext_version}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso_core_version}"
}