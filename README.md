![Group 18cover](https://github.com/user-attachments/assets/96bc8a98-ed3c-4f51-99be-6b70f6e9db6b)

## Preview
https://github.com/user-attachments/assets/1a91143e-d2c7-4f89-b8db-297c18287f34



## Tech stack & Open-source libraries
- Minimum SDK level 24.
- [Kotlin](https://kotlinlang.org/) based, utilizing [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous operations.
- Jetpack Libraries:
  - Jetpack Compose: Android’s modern toolkit for declarative UI development.
  - Lifecycle: Observes Android lifecycles and manages UI states upon lifecycle changes.
  - ViewModel: Manages UI-related data and is lifecycle-aware, ensuring data survival through configuration changes.
  - Navigation: Facilitates screen navigation, complemented by [Hilt Navigation Compose](https://developer.android.com/jetpack/compose/libraries#hilt) for dependency injection.
  - Room: Constructs a database with an SQLite abstraction layer for seamless database access.
  - Map Compose - Google map for jetpack compose
  - [Hilt](https://dagger.dev/hilt/): Facilitates dependency injection.
- Architecture:
  - MVVM Architecture (View - ViewModel - Model): Facilitates separation of concerns and promotes maintainability.
  - Repository Pattern: Acts as a mediator between different data sources and the application's business logic.
- [Ktor Client](https://ktor.io): Asynchronous, multiplatform support HTTP client and server.
- [ksp](https://github.com/google/ksp): Kotlin Symbol Processing API for code generation and analysis.
- [Coil](https://coil-kt.github.io/coil/compose): Image loading library that fetches and displays network images with Coil.
- [Datastore](https://developer.android.com/topic/libraries/architecture/datastore): Jetpack DataStore is a data storage solution that allows you to store key-value pairs or typed objects with [protocol buffers](https://developers.google.com/protocol-buffers)
- [Material 3](https://m3.material.io/): Latest version of Google’s open-source design system.
- [CameraX](https://developer.android.com/media/camera/camerax): A Jetpack library, built to help make camera app development easier. Recommended way to implement camera features.

# License
```xml
Designed and developed by 2024 x86xFX (Theekshana Nirmana)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
