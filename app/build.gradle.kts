@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt.library)
}

android {
    namespace = "cd.ghost.fakestore"
    compileSdk = 33

    defaultConfig {
        applicationId = "cd.ghost.fakestore"
        minSdk = 24
        targetSdk = 33
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

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(project(mapOf("path" to ":data")))
    implementation(project(mapOf("path" to ":source")))
    implementation(project(mapOf("path" to ":feature:catalog")))
    implementation(project(mapOf("path" to ":feature:cart")))
    implementation(project(mapOf("path" to ":feature:profile")))
    implementation(project(mapOf("path" to ":feature:auth")))
    implementation(project(mapOf("path" to ":feature:orders")))
    implementation(project(mapOf("path" to ":core:common")))
    implementation(project(mapOf("path" to ":core:presentation")))
    implementation(project(mapOf("path" to ":core:common-impl")))

    // for testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)
    kapt(libs.room.kapt)
    implementation(libs.room.ktx)
    //https://github.com/romychab/element-adapter
    implementation(libs.element.adapter)
    implementation(libs.coil)

    // dependency injection
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    // networking
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)

}