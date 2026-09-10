<div align="center">

# 🐈 MeowApp 

**Modular application using the API of [TheCatApi](https://thecatapi.com/)**

![Kotlin](https://img.shields.io/badge/Kotlin-100%25-7F52FF?logo=kotlin&logoColor=white)
![Platform](https://img.shields.io/badge/Platform-Android-3DDC84?logo=android&logoColor=white)
![Architecture](https://img.shields.io/badge/Architecture-Modular-blue)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

</div>

---

## 📖 Description

MeowApp is an Android native app in **Kotlin** that use the [TheCatApi](https://thecatapi.com/) API for show Cats images and information. The project follow an **modular architecture**, mostly for better practices and personal practices, allowing responsibility separation and stability.

> Personal project for practice purposes and display cats.

## 📱 Screen shoots

|                      Favorites                      | 
|:---------------------------------------------------:|
| ![Favorites](docs/screenshots/screen_favorites.png) | 

[//]: # (| Home | Detail | Search | Favorites | )

[//]: # (|:---:|:---:|:---:|:---:|)

[//]: # (| ![Home]&#40;docs/screenshots/home.png&#41; | ![Detalle]&#40;docs/screenshots/detail.png&#41; | ![Search]&#40;docs/screenshots/favorites.png&#41; | ![Favoritos]&#40;docs/screenshots/favorites.png&#41; | )

[//]: # (## 🎨 Design &#40;Figma&#41;)

[//]: # ()
[//]: # ([![Ver diseño en Figma]&#40;https://img.shields.io/badge/Figma-Ver%20diseño-F24E1E?logo=figma&logoColor=white&#41;]&#40;https://www.figma.com/file/TU-LINK-AQUI&#41;)

[//]: # ()
[//]: # (![Preview del diseño]&#40;docs/figma-preview.png&#41;)

[//]: # ()
[//]: # (El diseño completo —sistema de diseño, pantallas y prototipo navegable— está disponible en Figma. Haz clic en el badge de arriba para explorarlo &#40;no necesitas cuenta de Figma para verlo&#41;.)

## 🏗️ Architecture

This follow a **modular architecture**:

```
MeowApp/
├── app/            → Main module 
├── core/            → Common code (network, ui, common, etc.)
├── feature/         → Functional modules (home, search, favorites…)
├── build-logic/     → Gradle (convention plugins)
└── gradle/          → Version catalog and wrapper
```

**Technical Stack:**
- Kotlin 100%
- Multi-module Architecture with Gradle convention plugins
- [TheCatApi](https://thecatapi.com/) main API service
- Dependencies injection with Hilt
- Retrofit and OkHTTP
- ROOM Database

## 🚀 How to run the project?

```bash
git clone https://github.com/jmontoyaath/MeowApp.git
cd MeowApp
```

1. Open in **Android Studio** (recommended version: *Panda (2026)+*).
2. Get your API Key in [thecatapi.com](https://thecatapi.com/).
3. Add your API key in the *`local.properties`* file:
   ```properties
   API_KEY=your_api_key_here
   ```
4. Sync Gradle and run the app in your device (or emulator).

## 📄 License

This project use the MIT License. For more information go to the file [LICENSE](LICENSE).

---

<div align="center">
Made with 🐾 by <a href="https://github.com/jmontoyaath">jmontoyaath</a>
</div>
