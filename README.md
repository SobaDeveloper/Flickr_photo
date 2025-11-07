# Flickr Photo
A demo Android photo search app, demonstrating modern practices using Kotlin, Jetpack Compose, Flickr API, Koin, and Clean / MVVM architecture.

![screen-20251106-1736412-ezgif com-optimize (2)](https://github.com/user-attachments/assets/1cb16da8-9d21-4cb3-972a-822a1a79c818)


## Built With

- **[Kotlin](https://kotlinlang.org/)** – First class and official programming language for Android development
- **[Jetpack Compose](https://developer.android.com/jetpack/compose)** – Modern toolkit for building native UI
- **[Coroutines](https://developer.android.com/kotlin/coroutines)** – Asynchronous programming and concurrency
- **[Flow](https://developer.android.com/kotlin/flow)** – Reactive streams for handling asynchronous data
- **[Koin](https://insert-koin.io/)** – Lightweight dependency injection for Kotlin
- **[Jetpack Libraries](https://developer.android.com/jetpack)**
  - [Navigation Compose](https://developer.android.com/guide/navigation) - Type-safe navigation for Compose
  - [Material 3](https://developer.android.com/jetpack/androidx/releases/compose-material3) - Material Design 3 components
- **[Moshi](https://github.com/square/moshi)** – JSON serialization library
- **[Retrofit](https://square.github.io/retrofit/)**  - Type-safe HTTP client for Android and Java.
- **[Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization)** – Kotlin's native serialization library.  
- **[Glide](https://github.com/bumptech/glide)** – Image loading library for Android.
- **[DataStore](https://developer.android.com/topic/libraries/architecture/datastore)** – Modern replacement for SharedPreferences.
- **[JUnit](https://junit.org/junit4/)** - Unit testing framework
- **[MockK](https://mockk.io/)** - Mocking library for Kotlin


## Feature Requirements

- Set up a project from scratch.
- The app should have a Text box that will let users enter text and search for images,
When enter/button is hit, if user does not enter any text, it should grab recent photos,
otherwise it should query based on the text user entered
- The app should display Images in a grid view with 3 columns. Images should be square.
- There should be error handling for network calls and loading UI, simple spinning circular
progress and snackbar for error will do.
- The app should allow simple pagination when scrolling. * Please do not use the Paging
lib from google and have your own implementation *
- You can use any libraries you want to use (compose is cool if you are proficient in it)


## Architecture Overview

```
📦 WeatherApp
├── 🧩 app             # Main entry point and application class
│   └── navigation     # Compose destinations and navigation graph.
│
├── 🧩 core            # Shared utilities and common functionalities
│   └── util           # Date and time formatting, extension functions, etc.
│
├── 🧩 core-ui         # Reusable UI components across features
│   └── theme          # Material 3 styling, theming
│
├── 🧩 data            # Data sources (network, local)
│   ├── local          # Datastore
│   ├── service        # Retrofit interfaces and API DTOs
│   └── network        # Networking config (Retrofit, OkHttp, interceptors)
│
├── 🧩 domain          # Business logic, use cases, and domain models
│   ├── mapper         # DTO to domain model mappers
│   ├── models         # Domain models
│   ├── repo           # Repositories
│   └── usecase        # UseCases coordinating business rules
│
├── 🧩 feature         # Feature modules (can include multiple features)
│   └── photos         # Photo search, photo details
```

## Dependency Graphs

core -> data -> domain -> feature/photos -> app

core -> core-ui -> feature/photos

## Project Setup

To build and run this project, please add the following to the `local.properties` file at the root of your project:

```
FLICKR_BASE_URL=https://www.flickr.com
FLICKR_API_KEY=[YOUR API KEY]
```
