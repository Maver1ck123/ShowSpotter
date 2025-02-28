# ShowSpotter - Movie/TV Show Discovery App

## Project Overview
A modern Android app built with:
- Kotlin
- Jetpack Compose
- MVVM Architecture
- Retrofit for API integration
- Coil for image loading
- Watchmode API for data

## Features Implemented
- Swipeable tab layout for Movies/TV Shows
- Details screen with synopsis and metadata
- Shimmer loading effects
- Error handling and placeholder images
- Modern Material 3 design system

## Challenges Faced
1. **API Integration**: Handling Watchmode API's nested JSON structure
2. **Image Loading**: Implementing Coil with proper error handling
3. **State Management**: Managing loading/error/success states in ViewModel
4. **UI Consistency**: Maintaining design coherence across multiple screen sizes

## Setup Instructions
1. Clone repository: git clone https://github.com/yourusername/ShowSpotter.git
2. Add API key in `local.properties`: API_KEY=your_watchmode_key
3. Build and run in Android Studio Flamingo+

## Assumptions
- Minimum API Level 24 (Android 7.0)
- Internet permission required
- Dark mode supported but not enforced

## Testing Coverage
- ViewModel unit tests
- Repository data parsing tests
- UI interaction tests


