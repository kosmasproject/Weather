# Weather

<h1 align="center">Project: Weather Prediction with MVVM Clear Architecture</h1>

This project implements MVVM Clean Architecture using Kotlin, DaggerHilt, Retrofit, Coroutines, Flow, etc.

<div align="center"/>
<h2 align="left">1. Clean Architecture</h2>
<img align="left"  width="400" height="400" src="https://developer.android.com/static/topic/libraries/architecture/images/mad-arch-overview.png" alt="Image Clean Architecture"/>

<br>
<br>
<br>
<br>

<h3 align="left">1.1. UI layer</h3>
<div align="center"/>
<img align="left"  width="200" height="200" src="https://developer.android.com/static/topic/libraries/architecture/images/mad-arch-overview-ui.png" alt="Image Clean Architecture"/>

<br>
<br>
<br>
<br>

<h3 align="left">1.2. Data layer</h3>
<div align="center"/>
<img align="left"  width="200" height="200" src="https://developer.android.com/static/topic/libraries/architecture/images/mad-arch-overview-data.png" alt="Image Clean Architecture"/>

<br>
<br>
<br>
<br>

<h3 align="left">1.3. Domain layer</h3>
<div align="center"/>
<img align="left"  width="200" height="200" src="https://developer.android.com/static/topic/libraries/architecture/images/mad-arch-overview-domain.png" alt="Image Clean Architecture"/>

<br>
<br>
<br>
<br>

### Highlights
- MVVM Architecture
- Kotlin
- DaggerHilt
- Retrofit
- Coroutines
- Flow
- Room Local Database

### The app has following modules & package:

- **data**: contains all the data layer, including dependency injection related, service, network, interceptor, localdb using room, repository, and data model.
- **domain**: contains all the domain layer, including dependency injection related, usecase, domain model, and mapper
- **app**: View classes along with their corresponding ViewModel.
- **utils**: Utility classes.
- **core**: Base classes.

### Resources to learn MVVM Architecture and other components used in this project:

- MVVM
  Architecture: [MVVM Clean Architecture
        - Set up a new project with Kotlin and other dependencies required.
        - Project Structure.
        - Set up the data layer.
        - Set up the domain layer.
        - Set up the di layer.
        - Set up the core package.
        - Set up the utils package.
        - Set up UI layer, build and run the project.
  
- Mastering Kotlin
  Coroutines: [Mastering Kotlin Coroutines](https://amitshekhar.me/blog/kotlin-coroutines)
- Flow API in Kotlin: [Flow API in Kotlin](https://amitshekhar.me/blog/flow-api-in-kotlin)
- Learn Kotlin Topics: [Kotlin Blog](https://amitshekhar.me/tags/kotlin)
