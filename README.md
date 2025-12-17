# 📝 FireNotes – Secure & Intelligent Cloud-Based Note App

<p align="center">
  <img src="https://github.com/pratham162u/FireNotes/blob/main/Firenotes.png" alt="FireNotes Logo" width="180" />
</p>

A powerful, secure, and intuitive **Android note‑taking application** with real‑time cloud synchronization.
Capture your thoughts, organize your ideas, and access them anytime, anywhere using **Firebase**.

**Android | Java | Firebase | Material Design**

---

## 🌟 Overview

FireNotes is a modern, feature‑rich note‑taking application developed for Android devices using **Java** and **Firebase**.
It provides secure user authentication, real‑time cloud sync, offline access, and a clean Material Design UI, making it perfect for daily note‑taking and productivity.

Your notes are stored safely in the cloud and synced instantly across sessions, ensuring data reliability and security.

---

## ✨ Features

* 🔐 **Secure Authentication** – Firebase Email/Password login system
* ☁️ **Real‑time Cloud Sync** – Notes sync instantly using Firestore
* 📱 **Clean UI** – Modern Material Design interface
* ⚡ **Auto‑Save** – Notes save automatically with live updates
* 🗂️ **Smart Organization** – Easy note listing and navigation
* ✏️ **Create & Edit Notes** – Simple and fast editing experience
* 🔄 **Offline Support** – Access and edit notes without internet
* 🗑️ **Safe Delete** – Confirmation dialog to prevent data loss
* 📅 **Time Stamps** – Automatic date & time for every note
* 🔒 **Privacy First** – Firebase security rules protect user data
* 🎭 **Smooth Animations** – Lottie animations for better UX

---



## 🚀 Getting Started

### 📋 Prerequisites

* Android Studio Arctic Fox (2020.3.1) or higher
* Java JDK 8 or above
* Android SDK (API Level 29+)
* Firebase Account
* Git installed

---

## 🛠️ Installation

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/pratham162u/FireNotes.git
cd FireNotes
```

### 2️⃣ Open Project in Android Studio

* Open Android Studio
* Click **Open an existing project**
* Select the FireNotes folder

### 3️⃣ Firebase Configuration

* Go to Firebase Console
* Create a new Firebase project
* Add an Android app
* Download **google-services.json**
* Place it inside the app/ directory
* Enable **Authentication (Email/Password)**
* Enable **Cloud Firestore**

### 4️⃣ Run the App

* Click **Run ▶️** in Android Studio
* Or connect a physical device / emulator

---

## 📁 Project Structure

```
FireNotes/                                        📦 Root Project
├── app/                                          📱 Android App Module
│   ├── src/main/java/com/example/firenotes/      ☕ Java Source Code
│   │   ├── SplashActivity.java                   🚀 App Splash Screen
│   │   ├── CreateAccountActivity.java            👤 User Registration
│   │   ├── LoginActivity.java                    🔑 User Login
│   │   ├── ForgotPasswordActivity.java           🔄 Password Recovery
│   │   ├── MainActivity.java                     🏠 Home / Notes List
│   │   ├── NoteDetails.java                      ✏️ Create & Edit Notes
│   │   ├── Note.java                             📝 Note Model Class
│   │   ├── NoteAdapter.java                      🔗 RecyclerView Adapter
│   │   └── Utility.java                          🛠️ Firebase Utilities
│   └── res/                                      🎨 App Resources
│       ├── layout/                               📐 XML Layout Files
│       ├── drawable/                             🖼️ Images & Icons
│       ├── values/                               🎨 Colors, Styles, Themes
│       └── xml/                                  ⚙️ Config XML Files
├── Firenotes.png                                 🖼️ App Logo / README Image
├── README.md                                     📖 Project Documentation
├── build.gradle                                  🏗️ Project Build Script
└── settings.gradle                               ⚙️ Gradle Settings
```

---


## 🎯 How to Use


1. **Create Account** – Register using email and password
2. **Login** – Securely sign in
3. **Add Note** – Tap ➕ to create a new note
4. **Edit Note** – Tap a note to modify content
5. **Auto Save** – Notes save automatically
6. **Delete Note** – Long press to delete with confirmation
7. **Logout** – Sign out securely



---


## ✍️ Note Management
- Quick Create: Instant note creation with title and content fields
- Real-time Edit: Live editing with immediate cloud sync
- Time Stamps: Automatic date and time tracking for all notes
- Visual Feedback: Material Design animations for all interactions
- Safe Actions: Confirmation dialogs for destructive operations


---


## 🔐 Security


- Firebase Authentication for user identity
- Firestore security rules for data protection
- User‑specific note storage

