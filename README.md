# LessonSqlCode - Android SQLite Notes App

## Description

A simple Android application for creating and managing notes with images using SQLite database. The app allows users to create, store, and view notes with titles, descriptions, and optional image attachments.

## Features

- Create and store notes with titles and descriptions
- Add images to notes
- View list of saved notes
- SQLite database integration
- Material Design UI components
- Image URI persistence

## Technical Stack

- **Language:** Kotlin
- **Min SDK:** 19 (Android 4.4)
- **Target SDK:** 32
- **Database:** SQLite
- **Architecture Components:**
  - RecyclerView
  - ConstraintLayout
  - Material Design Components
  - SQLiteOpenHelper

## Project Structure

```
app/
├── src/main/
│   ├── java/com/example/lessonsqlcode/
│   │   ├── MainActivity.kt
│   │   ├── EditActivity.kt
│   │   └── db/
│   │       ├── MyDbManager.kt
│   │       ├── MyDbHelper.kt
│   │       ├── DbNameClass.kt
│   │       ├── ListItem.kt
│   │       └── MyIntentConstants.kt
│   ├── res/
│   │   ├── layout/
│   │   │   ├── activity_main.xml
│   │   │   ├── edit_activity.xml
│   │   │   └── rc_item.xml
│   │   └── values/
│   │       ├── colors.xml
│   │       ├── strings.xml
│   │       └── themes.xml
│   └── AndroidManifest.xml
```

## Setup Instructions

1. Clone the repository
2. Open in Android Studio
3. Sync Gradle files
4. Run on emulator or device (minimum SDK 19)

## Database Schema

```sql
TABLE Test_Table (
    _ID INTEGER PRIMARY KEY,
    Title TEXT,
    Content TEXT,
    Uri TEXT
)
```

## Key Components

- `MainActivity`: Main screen with RecyclerView displaying notes
- `EditActivity`: Screen for creating/editing notes
- `MyDbManager`: Handles database operations
- `MyAdapter`: RecyclerView adapter for displaying notes

## Dependencies

```gradle
dependencies {
    implementation 'androidx.core:core-ktx:1.7.0'
    implementation 'androidx.appcompat:appcompat:1.5.0'
    implementation 'com.google.android.material:material:1.6.1'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
}
```

## Build Configuration

- Gradle Version: 7.3.3
- Kotlin Version: 1.7.10
- Compile SDK: 32
- Build Tools: Android Studio default

## License

[Add License Information]
