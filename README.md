![badge-android](http://img.shields.io/badge/platform-android-6EDB8D.svg?style=flat)
![badge-desktop](http://img.shields.io/badge/platform-desktop-DB413D.svg?style=flat)
![badge-js](http://img.shields.io/badge/platform-web-FDD835.svg?style=flat)

# Artphoria

Artphoria is a Kotlin and Compose Multiplatform app that offers an immersive way to explore the art
collection of the famous Rijksmuseum in Amsterdam. This project demonstrates the power of Kotlin
Multiplatform by sharing business logic across Android, Desktop, and Web platforms.

## Key Features:

- **Kotlin Multiplatform:** Maximum code reusability across platforms.
- **Compose Multiplatform:** Modern UI toolkit for building native-like experiences.
- **Coroutines and Flow:** Asynchronous programming for efficient data handling.
- **Jetpack Navigation:** The official navigation library from Google.
- **Koin:** Dependency injection for streamlined management.
- **Ktor:** Networking for reliable data fetching.
- **Material 3:** Consistent and modern UI design.
- **MVVM+ Pattern:** Architecture for maintainable and scalable codebase.
- **Clean Architecture:** The codebase inherits data, domain, and presentation layers.

## Project Structure

- **common:** Contains the shared business logic, models, and network layer.
- **android:** Android-specific code, including UI and platform-specific implementations.
- **desktop:** Desktop-specific code, including UI and platform-specific implementations.
- **web:** Web-specific code, including UI and platform-specific implementations.

## Screenshots

### Android

<div align="center">
  <img style="float: left; margin-right: 15px;" src="https://github.com/user-attachments/assets/7d5c9d12-c8d2-45c0-897a-1992e320f18c" width="33%">
  <img style="float: left; margin-left: 15px; margin-right: 15px;" src="https://github.com/user-attachments/assets/10e72c61-1b23-4f58-90c4-169be46cab14" width="33%">
  <img style="float: left; margin-left: 15px;" src="https://github.com/user-attachments/assets/4dff5135-de8a-47c0-b8be-0a5453aec84d" width="33%">
</div>
<div align="center">
  <img style="float: left; margin-right: 15px;" src="https://github.com/user-attachments/assets/8ef969a3-a054-40e1-94a1-36f96ff54360" width="33%">
  <img style="float: left; margin-left: 15px; margin-right: 15px;" src="https://github.com/user-attachments/assets/7b8210c4-4b33-4992-a00e-3073d7737104" width="33%">
  <img style="float: left; margin-left: 15px;" src="https://github.com/user-attachments/assets/a1fb56f5-b16a-42a3-b8f1-4fb1f4c954ac" width="33%">
</div>

### Desktop

<div align="center">
  <img style="float: left; margin-right: 15px;" src="https://github.com/user-attachments/assets/33b32157-4040-45ad-9a23-70ed212fe8fa" width="100%">
</div>

### Wasm

<div align="center">
  <img style="float: left; margin-right: 15px;" src="https://github.com/user-attachments/assets/029a1298-f47d-48e2-b96a-98e5d0125d63" width="100%">
</div>

## Application design

Adapted from [Gallery of art App](https://dribbble.com/shots/20446337-Gallery-of-art-App)
by [Mari Andrianova](https://dribbble.com/ar_mour/).

## Platform support

Currently, the application is functional on Android, Desktop and Wasm platforms. The project is
architected with future iOS compatibility in mind. Thanks to extensive code sharing (99%) and a
modular structure using the Kotlin Multiplatform, the iOS implementation can be easily integrated
once the necessary development environment is available.