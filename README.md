# GitHub Explorer

An Android app to explore GitHub user profiles, built with **Kotlin**, **Jetpack Compose**, and **Clean Architecture + MVVM**. Search any GitHub username to view their profile, stats, and more — with smart 1-hour local caching.

---

## Screenshots

> Add your screenshots here after taking them from the app!
> `![Search Screen](screenshots/search_screen.png)`
> `![Profile Screen](screenshots/profile_screen.png)`

---

## Features

- Search any GitHub username
- View profile — name, avatar, bio, location
- Stats — followers, following, public repos
- Smart local caching with 1-hour expiry (Room)
- Retrofit for network calls
- Back navigation with hardware + gesture support
- Error handling with user-friendly messages

---

## Tech Stack

| Category | Library |
|---|---|
| Language | Kotlin |
| UI | Jetpack Compose |
| Architecture | Clean Architecture + MVVM |
| DI | Hilt |
| Networking | Retrofit + Gson |
| Local Cache | Room |
| Image Loading | Coil |
| Navigation | Jetpack Navigation Compose |
| Unit Testing | JUnit + MockK + Coroutines Test |

---

## Architecture

This project follows **Clean Architecture** with three distinct layers:

```
presentation/
├── SearchScreen.kt
├── UserDetailScreen.kt
├── UserViewModel.kt
├── UiState.kt
└── NavGraph.kt

domain/
├── model/
│   └── UserDetails.kt
├── repository/
│   └── UserRepository.kt        ← interface only
└── usecase/
    └── GetUserUseCase.kt

data/
├── remote/
│   ├── UserRemoteDataSource.kt
│   └── apiservice/
│       └── GetUserApiService.kt
├── local/
│   ├── UserLocalDataSource.kt
│   ├── UserDao.kt
│   ├── UserDatabase.kt
│   └── UserEntity.kt
├── mapper/
│   ├── UserResponseMapper.kt
│   └── UserEntityMapper.kt
└── repository/
    └── UserRepositoryImplementation.kt

di/
└── AppModule.kt
```

### Data flow

```
SearchScreen → UserViewModel → GetUserUseCase → UserRepository
                                                      ↙         ↘
                                          RemoteDataSource   LocalDataSource
                                          (Retrofit)         (Room)
```

### Caching strategy

- Cache expiry: **1 hour**
- On search: checks local cache first
- If cache is valid → returns cached data (no network call)
- If cache is expired or empty → fetches from GitHub API, saves to Room

---

## Getting Started

### Prerequisites
- Android Studio Hedgehog or later
- Min SDK 26
- Kotlin 2.0+

### Setup

1. Clone the repository:
```bash
git clone https://github.com/NarendraKumar-2259/android-github-explorer.git
```

2. Open in Android Studio

3. Run the app on an emulator or physical device

> No API key required — uses the public GitHub API

---

## Unit Tests

```bash
./gradlew test
```

Tests cover:
- `GetUserUseCase` — success and failure cases
- Fake repository pattern for isolated testing

---

## What I Learned

This project was built as a learning exercise to practice:
- Clean Architecture principles and layer separation
- Dependency Injection with Hilt
- Repository pattern with network + local caching
- Jetpack Compose Navigation
- Unit testing with fake dependencies
- Version catalog (`libs.versions.toml`) for dependency management

---

## Author

**Narendra Kumar**
- GitHub: [@NarendraKumar-2259](https://github.com/NarendraKumar-2259)

---

## License

```
MIT License — feel free to use this project for learning purposes
```
