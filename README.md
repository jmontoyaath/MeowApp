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

MeowApp es una aplicación Android desarrollada con **Kotlin** que consume la API pública de [TheCatApi](https://thecatapi.com/) para mostrar y explorar imágenes y datos de gatos. El proyecto está construido con una **arquitectura modular**, pensada para practicar buenas prácticas de organización de código, separación de responsabilidades y escalabilidad, tal como se estructuraría una app real de producción.

> Proyecto personal en desarrollo activo.

## 📱 Screen shoots

| Home | Detail | Search | Favorites | 
|:---:|:---:|:---:|:---:|
| ![Home](docs/screenshots/home.png) | ![Detalle](docs/screenshots/detail.png) | ![Search](docs/screenshots/favorites.png) | ![Favoritos](docs/screenshots/favorites.png) | 

## 🎨 Design (Figma)

[![Ver diseño en Figma](https://img.shields.io/badge/Figma-Ver%20diseño-F24E1E?logo=figma&logoColor=white)](https://www.figma.com/file/TU-LINK-AQUI)

![Preview del diseño](docs/figma-preview.png)

El diseño completo —sistema de diseño, pantallas y prototipo navegable— está disponible en Figma. Haz clic en el badge de arriba para explorarlo (no necesitas cuenta de Figma para verlo).

## 🏗️ Arquitectura

El proyecto sigue una **arquitectura modular** para separar responsabilidades y mejorar los tiempos de build:

```
MeowApp/
├── app/            → Módulo principal, ensambla el resto de módulos
├── core/            → Código compartido (network, ui, common, etc.)
├── feature/         → Módulos por funcionalidad (home, detail, favorites…)
├── build-logic/     → Configuración centralizada de Gradle (convention plugins)
└── gradle/          → Version catalog y wrapper
```

**Stack técnico:**
- Kotlin 100%
- Arquitectura modular multi-módulo con Gradle convention plugins
- [TheCatApi](https://thecatapi.com/) como fuente de datos
- Inyección de dependencias con Hilt
- Integración con Retrofit para consumo de servicios
- Bases de datos con ROOM

## 🚀 Cómo ejecutar el proyecto

```bash
git clone https://github.com/jmontoyaath/MeowApp.git
cd MeowApp
```

1. Abre el proyecto en **Android Studio** (versión recomendada: *indica aquí la versión*).
2. Consigue una API key gratuita en [thecatapi.com](https://thecatapi.com/).
3. Añade tu API key en el archivo correspondiente *(ej. `local.properties`)*:
   ```properties
   API_KEY=tu_api_key_aquí
   ```
4. Sincroniza Gradle y ejecuta la app en un emulador o dispositivo físico.

## 📄 Licencia

Este proyecto está bajo la licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más detalles.

---

<div align="center">
Hecho con 🐾 por <a href="https://github.com/jmontoyaath">jmontoyaath</a>
</div>
