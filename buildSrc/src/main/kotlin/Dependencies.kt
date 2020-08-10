object Versions {
    // App dependencies
    const val kotlinVersion = "1.3.72"
    const val androidxTestVersion = "1.1.0"
    const val androidxAnnotations = "1.0.1"
    const val androidxLegacySupport = "1.0.0"
    const val appCompatVersion = "1.1.0"
    const val constraintLayoutVersion = "1.1.3"
    const val roomVersion = "2.2.5"
    const val archLifecycleVersion = "2.1.0"
    const val junitVersion = "4.12"
    const val materialVersion = "1.2.0-alpha06"
    const val retrofit2Version = "2.6.0"
    const val gsonVersion = "2.8.6"
    const val hiltVersion = "2.28-alpha"
    const val hiltLifeCycleVersion = "1.0.0-alpha01"
    const val fragmentKtxVersion = "1.2.5"
    const val workManagerVersion = "2.3.4"
}

object AppConfiguration {
    const val applicationId = "com.naufalprakoso.researchworkmanager"
    const val versionName = "1.0"
    const val versionCode = 1
    const val minSdkVersion = 21
    const val targetSdkVersion = 29
}

object Dependencies {
    const val gradle = "com.android.tools.build:gradle:4.0.0"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val materialDesign = "com.google.android.material:material:${Versions.materialVersion}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    const val annotation = "androidx.annotation:annotation:${Versions.androidxAnnotations}"
    const val legacySupport = "androidx.legacy:legacy-support-v4:${Versions.androidxLegacySupport}"
    const val lifeCycleExtension = "androidx.lifecycle:lifecycle-extensions:2.2.0"
    const val lifeCycleCommonJava = "androidx.lifecycle:lifecycle-common-java8:${Versions.archLifecycleVersion}"
    const val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtxVersion}"
    const val workManager =  "androidx.work:work-runtime-ktx:${Versions.workManagerVersion}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit2Version}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit2Version}"
}

object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val ktx = "androidx.room:room-ktx:${Versions.roomVersion}"
    const val compiler = "androidx.room:room-compiler:${Versions.roomVersion}"
}

object Hilt {
    const val hilt = "com.google.dagger:hilt-android:${Versions.hiltVersion}"
    const val compiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}"
    const val plugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltVersion}"
    const val viewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltLifeCycleVersion}"
    const val viewModelCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltLifeCycleVersion}"
}

object Testing {
    const val jUnit = "junit:junit:${Versions.junitVersion}"
    const val extJUnit = "androidx.test.ext:junit:${Versions.androidxTestVersion}"
}