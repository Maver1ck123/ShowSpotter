# Showspotter - Movie & TV Discovery App

## Features
- Dual-pane view for Movies/TV Shows
- Shimmer loading effects
- Error handling with Snackbars
- MVVM architecture with RxJava
- Dependency Injection using Koin
- Jetpack Compose UI

## Tech Stack
- Kotlin
- Jetpack Compose
- Retrofit 2.9
- RxJava 3
- Koin 3.5
- Coil 2.5

## Setup
1. Get API key from [Watchmode](https://www.watchmode.com/)
2. Add to `local.properties`: watchmode.api.key="YOUR_KEY"
3. Build and run using Android Studio Giraffe+

## Testing
./gradlew test # Unit tests
./gradlew connectedAndroidTest # Instrumentation tests

## APK Generation
1. Build -> Generate Signed Bundle/APK
2. Select APK
3. Use included keystore (password: android)
4. Output: app/release/showstopper.apk
