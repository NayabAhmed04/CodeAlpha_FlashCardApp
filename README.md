Flashcard Quiz App

A simple and beautiful Android application built using Java and XML, where users can create, save, and attempt flashcards for quick learning and revision.

Features:
Add Flashcards (question + answer)
Save flashcards locally using SharedPreferences + Gson
View all saved flashcards
Attempt Quiz Mode to test yourself
Attractive and modern UI
Compatible with Android 8.0+ (API 26+)

Tech Stack:
Language: Java
UI: XML (ConstraintLayout & Material Components)
Data Storage: SharedPreferences + Gson
Min SDK: 28
Target SDK: 35
IDE: Android Studio


Dependencies:
Add this library for JSON conversion:

gradle
implementation 'com.google.code.gson:gson:2.10.1'

AndroidX dependencies (already included):

gradle
implementation 'androidx.appcompat:appcompat:1.7.0'
implementation 'com.google.android.material:material:1.12.0'
implementation 'androidx.constraintlayout:constraintlayout:2.2.0'


How to Run the Project:
1. Clone the repository
2. Open in Android Studio
3. Let Gradle sync
4. Run the project on Emulator or Physical Device

Testing
Basic Unit Tests included for:
Flashcard Model
Flashcard Saving/Loading
Quiz Flow


Author
Nayab Ahmed
Flashcard Learning App (Java + XML version)
