# Productive Android App
[![Circle CI](https://circleci.com/gh/infinum/Productive-Android-app.svg?style=svg)](https://circleci.com/gh/infinum/Productive-Android-app)

## Requirements

* Android Studio **1.4+**

## Running tests

    ./gradlew testStagingDebugUnitTest

## Doing a new Play Store release

1. Increment `versionCode` and `versionName` if not already done
2. Run tests with `./gradlew testStagingDebugUnitTest`
3. Build and install play store build with `./gradlew clean installProductionRelease`
4. Test app manually
5. Upload `app-production-release.apk` to Play Store
