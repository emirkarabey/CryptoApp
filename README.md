
# Crypto App
An android application that lists crypto coins, we can add to favorites, and we can search among all cryptos.

<img src="https://github.com/emirkarabey/CryptoApp/blob/master/screens/cryptocurrency-hero.jpg" height="128" width="128" >

# App Gif
![cryptoapp](https://user-images.githubusercontent.com/63194364/175770753-3d7c6019-0b91-49bc-8fc9-6ae681983b94.gif)


# Screens
<img src="https://github.com/emirkarabey/CryptoApp/blob/master/screens/cryptoapp1.png" align="left" height="700" width="400" >
<img src="https://github.com/emirkarabey/CryptoApp/blob/master/screens/cryptoapp2.png" height="700" width="400" >
<img src="https://github.com/emirkarabey/CryptoApp/blob/master/screens/cryptoapp3.png" height="700" width="400" >

# Implementations
You can find following apis implementation in this project.

<ul>
<li>Room</li>
<li>SQLite</li>
<li>Coroutines</li>
<li>Navigation</li>
<li>RXJava</li>
<li>Retrofit</li>
<li>Data Binding</li>
<li>Grid Layout</li>
<li>Gson converter</li>
<li>Glide</li>
<li>View Model</li>
<li>Live Data</li>
<li>MVVM</li>
</ul>

# Features
<ul>
<li>Pull data from crypto api</li>
<li>Listing cryptocurrencies</li>
<li>Adding and removing the cryptocurrencies we want to favorites</li>
<li>Keep favorites in sqlite using room</li>
<li>Searching between cryptocurrencies</li>
<li>Uses view model and live data</li>
</ul>




# Libraries Used

* [Architecture][10] - Start with classes for managing your UI component lifecycle and handling data
  persistence.
  * [lifecycle][22] - As a user navigates through, out of, and back to your app, the Activity instances in your app transition through different states in their lifecycle.
  * [ViewModel][17] - Easily schedule asynchronous tasks for optimal execution.
  * [livedata][52] - is an observable data holder class. Unlike a regular observable, LiveData is lifecycle-aware, meaning it respects the lifecycle of other app components, such as activities, fragments, or services.
  * [Navigation][50] - Handle everything needed for in-app navigation.
  asynchronous tasks for optimal execution.
  * [Data Binding][13] - The Data Binding Library is a support library that allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
  * [Coroutines][51] - A coroutine is a concurrency design pattern that you can use on Android to simplify code that executes asynchronously.
  * [Room][53] - The Room persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
  * [SQLite][54] - The androidx.sqlite library contains abstract interfaces along with basic implementations which can be used to build your own libraries that access SQLite.




* [UI][30] - Details on why and how to use UI Components in your apps - together or separate
  * [Layout][35] - Lay out widgets using different algorithms.
  * [Grid Layout][55] - A layout that places its children in a rectangular grid.
  * [Swipe-to-Refresh Layout][56] The swipe-to-refresh user interface pattern is implemented entirely within the SwipeRefreshLayout widget, which detects the vertical swipe, displays a distinctive progress bar, and triggers callback methods in your app.

* Third party and miscellaneous libraries
  * [Retrofit][90] for turns your HTTP API into a Java interface
  * [Glide][94] for image loading framework for Android that wraps media decoding, memory and disk caching, and resource pooling into a simple and easy to use interface.
  




[11]: https://developer.android.com/topic/libraries/view-binding
[52]: https://developer.android.com/topic/libraries/architecture/livedata
[50]: https://developer.android.com/topic/libraries/architecture/navigation/
[10]: https://developer.android.com/jetpack/compose/architecture
[17]: https://developer.android.com/jetpack/compose/state#viewmodel-state
[30]: https://developer.android.com/guide/topics/ui
[34]: https://developer.android.com/guide/components/fragments
[35]: https://developer.android.com/guide/topics/ui/declaring-layout
[22]: https://developer.android.com/guide/components/activities/activity-lifecycle
[18]: https://developer.android.com/jetpack/guide#fetch-data
[90]: https://square.github.io/retrofit/
[94]: https://github.com/bumptech/glide
[53]: https://developer.android.com/jetpack/androidx/releases/room
[54]: https://developer.android.com/jetpack/androidx/releases/sqlite
[55]: https://developer.android.com/reference/android/widget/GridLayout
[56]: https://developer.android.com/training/swipe/add-swipe-interface
[51]: https://developer.android.com/kotlin/coroutines
[13]: https://developer.android.com/topic/libraries/data-binding
