# Social Cooper Android

## Description

Social Cooper is an Android application built with Jetpack Compose that allows users to share posts, interact with other posts, and perform various social actions.

## Key Features

- **MVVM Architecture**: The application follows the Model-View-ViewModel design pattern to clearly separate responsibilities.
- **UseCase-Repository Pattern**: It uses the UseCase-Repository pattern to handle business operations and communication with the data source.
- **Separated Package**: The view, model, and data layers are clearly separated for better code organization and maintenance.
- **Coil**: Coil is used for asynchronous image handling, providing efficient image loading in the application.
- **Hilt**: Hilt is used for dependency injection, with some custom annotations to handle okhttp instantiation and add the api-key to post-registration requests.

## Backend

The SocialKtor backend is built in Kotlin using Ktor. You can find more information about the backend at this link: https://github.com/bernatdelgado87/SocialKtor

## Video Demo

### Register

https://github.com/bernatdelgado87/Social-Cooper-Android/assets/52576076/bbab8494-7d08-4d47-adb9-d7f63941b7fd

### Publish Post

https://github.com/bernatdelgado87/Social-Cooper-Android/assets/52576076/3e4b15a4-685b-4a8e-825d-4b6ad8713480

### Comments and Likes

https://github.com/bernatdelgado87/Social-Cooper-Android/assets/52576076/3d2efa65-a6e8-4e17-89b2-f1aea467da14

## Contribution

Contributions are welcome! If you would like to contribute to SocialKtor, please follow these steps:

1. Fork the repository.
2. Create a branch for your new feature: git checkout -b new-feature
3. Make your changes and commit them: git commit -m 'Add new feature'
4. Push the branch: git push origin new-feature
5. Create a pull request on GitHub.

License
This project is licensed under the Creative Commons License. See the LICENSE file for more details.

2024 SocialKtor. Developed by Bernat Delgado.
