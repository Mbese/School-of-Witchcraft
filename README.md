# School-of-Witchcraft

Introduction
------------

This application is written in Kotlin and uses Clean Architecture based on MVVM and Repository pattern.
Android Jetpack is used as an Architecture glue including but not limited to ViewModel, LiveData,
Lifecycles and Room. This application does network HTTP requests via Retrofit, OkHttp and GSON. Loaded data is saved to SQL based database, Room.

This application consist of 5 screens: The MainActivity, which is the landing screen with 3 navigation buttons,
HousesActivity which displays the list of houses in the school, HouseCharactersActivity which displays all characters in a selected house,
StudentsActivity displays all students in the school and SpellsActivity for the list of all spells.

Libraries Used
--------------

* [Foundation] - Components for core system capabilities.
  * [AppCompat] - Degrade gracefully on older versions of Android.
  * [Android KTX] - Write more concise, idiomatic Kotlin code.
  * [Test] - An Android testing framework for unit and runtime UI tests.
* [Architecture] - A collection of libraries that help with robust design, testable, and maintainable code.
  * [Lifecycles] - Create a UI that automatically responds to lifecycle events.
  * [LiveData] - Build data objects that notify views when the underlying database changes.
  * [Room] - SQLite database with in-app objects and compile-time checks.
  * [ViewModel] - Store UI-related data that isn't destroyed on app rotations. Easily schedule asynchronous tasks for optimal execution.
  * [WorkManager] - Manage your Android background jobs.
  * [Animations & Transitions] - Move widgets and transition between screens.
  * [Layout] - Lay out widgets using different algorithms.
  * [Material] - Material Components.
* Third party
  * [Kotlin Coroutines] for managing background threads with simplified code and reducing needs for callbacks.
  * [Koin] A fast dependency injector.
  * [Retrofit 2] A configurable REST client.
  * [OkHttp 3] A type-safe HTTP client.
  * [GSON] A Json - Object converter using reflection.
