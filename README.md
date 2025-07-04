# Rick and Morty App

### About the app

Rick and Morty app is an app that search products, list the result and show their details using [The Rick and Morty API](https://rickandmortyapi.com/).

This app runs on API 33 or higher.


### Before run the app

You need a physical device or have installed an android emulator with API 33 or higher.


### How run the app

1. Clone the repository writing on a terminal this command: `git clone https://github.com/hanarvaez/rick_and_morty_app.git`
2. Once the repository is cloned on your local, open it with the latest Android Studio version (Android Studio Narwhal | 2025.1.1 when this was written)
3. Press button `Sync Projects with Gradle Files` (the one who looks like an elephant with an arrow)
4. Press button `Build` > `Assemble 'app' Run Configuration`
5. Press button `Run 'app'`


### Proposed Architecture

This app uses MVI, clean architecture and repository pattern as shown next:

![MVI](https://miro.medium.com/max/1400/0*5xaFEBxI6_zK6Xmn.webp)

Figure 1. MVI

![Clean architecture](https://i.stack.imgur.com/FwHET.jpg)

Figure 2. Clean architecture

![Repository pattern](https://i.stack.imgur.com/CMJFn.png)

Figure 3. Repository pattern


#### Benefits of the proposed architecture

1. Avoid conflicts ("Single Source of truth"). Each action will be restricted to an state, so the user can't make undesired changes on the app.
2. Easier to find bugs. In this case each bug will be restricted to an specific state, so for track a bug only is needed to follow the flow of the specific state where is generated.
3. Single responsability. UI and logic are totally decoupled and independent.
4. Very testeable.
5. Very mantainable.

#### Disadvantages of the proposed architecture

1. This architecture implies to write too much code because every action of the user must be represented with an state.
2. Each action of the user implies to create objects and this is expensive to the machine.

### Libraries used

This project uses the following libraries:
- Coroutines
- Lifecycle Ktx
- Hilt
- Retrofit
- Room Database
- Glide
- Jetpack Compose
