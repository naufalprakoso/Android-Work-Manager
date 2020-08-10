plugins {
	id("com.android.application")
	id("kotlin-android")
	id("kotlin-android-extensions")
	id("kotlin-kapt")
	id("dagger.hilt.android.plugin")
}
apply(from = "$rootDir/ktlint.gradle.kts")

android {
	compileSdkVersion(29)
	buildToolsVersion("29.0.3")

	defaultConfig {
		applicationId = AppConfiguration.applicationId
		minSdkVersion(AppConfiguration.minSdkVersion)
		targetSdkVersion(AppConfiguration.targetSdkVersion)
		versionCode = AppConfiguration.versionCode
		versionName = AppConfiguration.versionName
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}

	buildTypes {
		getByName("release") {
			isMinifyEnabled = false
			proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
		}
		getByName("debug") {
			applicationIdSuffix = ".debug"
			isDebuggable = true
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

dependencies {
	implementation("androidx.appcompat:appcompat:1.2.0")
	implementation("androidx.constraintlayout:constraintlayout:1.1.3")
	implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

	// Dependencies for Android Support
	implementation(Dependencies.appCompat)
	implementation(Dependencies.materialDesign)
	implementation(Dependencies.constraintLayout)
	implementation(Dependencies.annotation)
	implementation(Dependencies.legacySupport)

	// Dependencies for Architecture Component
	implementation(Dependencies.appCompat)
	implementation(Dependencies.lifeCycleExtension)
	kapt(Dependencies.lifeCycleCommonJava)

	// Dependencies for Room
	implementation(Room.runtime)
	implementation(Room.ktx)
	kapt(Room.compiler)

	// Dependencies for Hilt
	implementation(Hilt.hilt)
	implementation(Hilt.viewModel)
	kapt(Hilt.compiler)
	kapt(Hilt.viewModelCompiler)

	// Dependencies for Testing
	testImplementation(Testing.jUnit)
	androidTestImplementation(Testing.extJUnit)

	// Retrofit2
	implementation(Retrofit.retrofit)
	implementation(Retrofit.converterGson)

	// Work Manager
	implementation(Dependencies.workManager)
	androidTestImplementation(Testing.workManager)

	// Gson
	implementation(Dependencies.gson)

	// Ktx
	implementation(Dependencies.fragmentKtx)
}
