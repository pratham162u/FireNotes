# 📝 FireNotes - Smart Cloud Note-Taking App

</br>
<div align="center">
  <img src="https://github.com/user-attachments/assets/c946f020-5398-40fa-b506-8fb6c0b4add1" alt="FireNotes Logo" width="100"/></br></br>
  
**A powerful, secure, and intuitive note-taking application with real-time cloud synchronization. Capture your thoughts, organize your ideas, and access them anywhere with Firebase integration!**

[![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com)
[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com)
[![Firebase](https://img.shields.io/badge/Firebase-039BE5?style=for-the-badge&logo=Firebase&logoColor=white)](https://firebase.google.com)
[![Material Design](https://img.shields.io/badge/Material%20Design-757575?style=for-the-badge&logo=material-design&logoColor=white)](https://material.io/design)

</div>

## 🌟 Overview

**FireNotes** is a modern, feature-rich note-taking application built for **Android devices** using Java and Firebase. With seamless cloud synchronization, robust authentication, and an elegant Material Design interface, FireNotes ensures your important thoughts and ideas are always secure and accessible across all your devices.

## ✨ Features

-   🔐 **Secure Authentication**: Firebase-powered user registration and login system
-   ☁️ **Real-time Sync**: Automatic cloud synchronization across all your devices
-   📱 **Intuitive Interface**: Clean Material Design with smooth animations and transitions
-   ⚡ **Instant Save**: Auto-save functionality with real-time timestamp tracking
-   🗂️ **Smart Organization**: Easy-to-navigate note list with quick access controls
-   🎨 **Rich Text Support**: Create detailed notes with formatted content
-   🔍 **Quick Actions**: Fast note creation, editing, and deletion
-   🔄 **Offline Support**: View and edit notes even without internet connection
-   🗑️ **Safe Deletion**: Confirmation dialogs prevent accidental note loss
-   📅 **Time Tracking**: Automatic date and time stamps for all notes
-   🔒 **Privacy First**: Secure user data with Firebase security rules
-   🎭 **Smooth Animations**: Lottie animations for enhanced user experience

## 🚀 Getting Started

### 📋 Prerequisites

-   **Android Studio**: Arctic Fox (2020.3.1) or higher
-   **Java Development Kit**: JDK 8 or higher
-   **Android SDK**: API Level 29 (Android 10) minimum
-   **Firebase Project**: Set up with Authentication and Firestore
-   **Git**: For version control

### 🛠️ Installation

1. **Clone the Repository**

    ```bash
    git clone https://github.com/mohitooo28/FireNotes.git
    cd FireNotes
    ```

2. **Open in Android Studio**

    - Launch Android Studio
    - Select "Open an existing Android Studio project"
    - Navigate to the cloned repository folder

3. **Configure Firebase**

    - Create a new Firebase project at [Firebase Console](https://console.firebase.google.com)
    - Add an Android app to your project
    - Download `google-services.json` and place it in the `app/` directory
    - Enable Authentication (Email/Password) and Firestore Database

4. **Build and Run**
    ```bash
    ./gradlew build
    ./gradlew installDebug
    ```
    Or use Android Studio's run button (▶️)

## 📁 Project Structure

```
📦 FireNotes/
├── 📱 app/                                
│   ├── 🏗️ build.gradle                   
│   ├── 📋 proguard-rules.pro           
│   ├── 📦 release/                        
│   │   ├── 📱 app-release.apk            
│   │   └── 📊 output-metadata.json       
│   └── 📁 src/
│       └── 📁 main/
│           ├── 📄 AndroidManifest.xml    
│           ├── 🖼️ ic_launcher-playstore.png 
│           ├── 📁 assets/
│           │   └── 🎬 splash.json        
│           ├── ☕ java/com/example/firenotes/
│           │   ├── 🚀 SplashActivity.java     
│           │   ├── 👤 CreateAccountActivity.java 
│           │   ├── 🔑 LoginActivity.java      
│           │   ├── 🔄 ForgotPasswordActivity.java 
│           │   ├── 🏠 MainActivity.java        
│           │   ├── ✏️ NoteDetails.java        
│           │   ├── 📝 Note.java            
│           │   ├── 🔄 NoteAdapter.java       
│           │   └── 🛠️ Utility.java           
│           └── 📁 res/                    
│               ├── 🎨 drawable/           
│               ├── 🔤 font/              
│               ├── 📱 layout/            
│               ├── 🖼️ mipmap-*/          
│               ├── 📊 values/           
│               └── 📄 xml/               
├── 🏗️ build.gradle                       
├── ⚙️ gradle.properties                  
├── 🐧 gradlew                            
├── 🪟 gradlew.bat                        
├── ⚙️ settings.gradle                     
└── 📖 README.md                          
```

## 🎯 How to Use

### 🚀 **Getting Started**

1. **Create Account**: Register with your email and secure password
2. **Login**: Access your notes with your credentials
3. **Create Notes**: Tap the ➕ button to add new notes
4. **Edit Notes**: Tap any note to view and modify content
5. **Auto-Save**: Changes are automatically saved to the cloud
6. **Delete Notes**: Long press or use delete button with confirmation
7. **Logout**: Access settings menu to securely sign out

### ✍️ **Note Management**

-   **Quick Create**: Instant note creation with title and content fields
-   **Real-time Edit**: Live editing with immediate cloud sync
-   **Time Stamps**: Automatic date and time tracking for all notes
-   **Visual Feedback**: Material Design animations for all interactions
-   **Safe Actions**: Confirmation dialogs for destructive operations

---

<div align="center">

**Your thoughts, secured in the cloud ☁️**

[🌟 Star this repo](../../stargazers) • [🐛 Report Bug](../../issues) • [💡 Request Feature](../../issues)

</div>
