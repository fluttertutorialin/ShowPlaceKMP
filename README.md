# Showplace

Kotlin Multiplatform project that displays all the latest shows and events in Baltimore. 
Includes:
- Android app (Jetpack Compose)
- iOS app (SwiftUI)
- Shared Module for networking, logging, coroutines, and serialization 

All data is ingested from [ShowplaceServices](https://github.com/GranthamAnthem/ShowPlaceServices)


## Prerequisites

- Xcode and Android Studio
- KMP Plugin in Android Studio
- JDK version 11+

## Getting Started

**1. Clone the Project**

- `git clone https://github.com/GranthamAnthem/Showplace.git`

**2. Open in Android Studio**

- Navigate to the root directory of the cloned project.
- Open the project in Android Studio by selecting "File" > "Open" and choosing the project folder.

**3. Open in Xcode**

- Navigate to the iosApp directory within the cloned project.
- Open iosApp.xcodeproj in Xcode.

**4. Run the Applications**

- In Android Studio, run the Android app using the play button.
- In Xcode, select the target device and run the iOS app.

## Project Structure

The project is organized into three main modules:

1. **Android App (Jetpack Compose)**
   - The `androidApp` module contains the Android application built using Jetpack Compose, the modern Android UI toolkit.
   - The app's UI components, navigation, and platform-specific features are implemented here.

2. **iOS App (SwiftUI)**
   - The `iosApp` module includes the iOS application with a native user interface built using SwiftUI.
   - This module handles the iOS-specific UI components and platform-dependent functionality.

3. **Shared Module**
   - The `shared` module is a common module shared between the Android and iOS apps. It contains code that is platform-independent, promoting code reuse across both platforms.
   - Key functionalities include:
      - **Networking:** The networking layer for fetching data from the [ShowplaceServices](https://github.com/GranthamAnthem/ShowPlaceServices) API.
      - **Date Formatting:** Shared utilities for consistent date formatting.
      - **Serialization:** Using [Kotlin Serialization](https://github.com/Kotlin/kotlinx.serialization) for consistent data serialization.
      - **Models:** Common data models used by both the Android and iOS apps.

The separation into distinct modules allows for a clear distinction between platform-specific code and shared logic. This architecture follows the principles of Kotlin Multiplatform, enabling code sharing while catering to the unique requirements of each platform.


## Tech Stack

The Showplace app incorporates a diverse set of technologies and libraries:

- **[Ktor](https://ktor.io/):** A versatile multiplatform HTTP client empowering efficient network communication.
  
- **[Koin](https://insert-koin.io):** A lightweight yet powerful dependency injection framework for managing dependencies and services across the codebase.
  
- **[Kotlin Serialization](https://github.com/Kotlin/kotlinx.serialization):** A multiplatform serialization library ensuring consistent data serialization throughout the project.
  
- **[Kermit](https://kermit.touchlab.co/):** A multiplatform logging library providing unified logging capabilities for streamlined development and debugging.
  
- **[Jetpack Compose](https://developer.android.com/jetpack/compose):** The modern Android UI toolkit used to craft the native user interface of the Android app.
  
- **[SwiftUI](https://developer.apple.com/xcode/swiftui/):** Employed for creating the declarative user interface of the iOS app.
  
- **[KMP Native Coroutines](https://github.com/rickclephas/KMP-NativeCoroutines):** A library facilitating the seamless integration of Kotlin Coroutines from Swift code in KMP applications.

This tech stack ensures a smooth and efficient development process while delivering a native user experience on both Android and iOS platforms.


## List and Detail Screen
![Screenshot 2023-12-22 at 1 56 49 PM](https://github.com/GranthamAnthem/ShowPlaceAppKMP/assets/40502200/ff86f75d-b6bb-4d5b-91c7-dfecb5cc3ee0)
![Screenshot 2023-12-22 at 1 56 58 PM](https://github.com/GranthamAnthem/ShowPlaceAppKMP/assets/40502200/ae4f086b-c33b-4f01-9647-dd14155cd680)


## License

This project is licensed under the MIT License, making it open for contributions and use.


## Contributing

If you'd like to contribute to Showplace, please send me a message.
