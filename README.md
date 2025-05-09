### 🎵 Music Playlist System

## 📖 Overview

The Music Playlist System is a Java-based console application that manages music playlists using custom-implemented data structures. It uses singly linked lists to efficiently store and manage users, playlists, and songs, providing a comprehensive music management solution with persistent storage.

## ✨ Features

### 👤 User Management

- **User Registration**: Create individual accounts with username and password
- **Batch Registration**: Register multiple users at once
- **Role-Based Access**: Separate interfaces for regular users and administrators
- **Authentication**: Secure login with username and password validation


### 📚 Playlist Management

- **Create Playlists**: Build personalized music collections
- **Add/Remove Songs**: Easily modify playlist contents
- **Update Song Details**: Edit song titles and artist information
- **Playback Simulation**: "Play" songs by displaying their details


### 👨‍💼 Admin Dashboard

- **User Administration**: View and manage all registered users
- **System Overview**: Access all playlists across the system
- **Content Management**: Examine any user's playlist contents
- **User Removal**: Delete user accounts when necessary


### 💾 Data Persistence

- **File-Based Storage**: All data is saved to text files for persistence
- **Automatic Loading**: User data and playlists load automatically on startup
- **Real-Time Saving**: Changes are immediately written to storage files


### 🧠 Data Structures Implementation

- **Custom Linked Lists**: Efficient implementation of singly linked lists
- **Dynamic Memory Management**: Flexible storage that grows as needed
- **Optimized Traversal**: Fast access to users, playlists, and songs


## 🛠️ Technical Implementation

### Core Classes

```plaintext
PlaylistManager
├── User                 // Stores user credentials and role
├── Song                 // Represents a song with title and artist
├── Playlist             // Contains a linked list of songs
├── PlaylistList         // Manages multiple playlists
└── UserList             // Manages multiple users
```

### File Structure

- **users.txt**: Stores all user accounts with format `username,password,role`
- **playlist-username-playlistname.txt**: Individual playlist files with format `title,artist` for each song


## 📋 Usage Guide

### Installation

1. Ensure Java Development Kit (JDK) 8 or higher is installed
2. Download the PlaylistManager.java file
3. Compile the program:

```plaintext
javac PlaylistManager.java
```


4. Run the application:

```plaintext
java PlaylistManager
```




### Main Menu

```plaintext
=== Music Playlist System ===
1. Login
2. Register
3. Register Multiple Users
4. Exit
```

### User Menu

```plaintext
=== User Menu ===
1. Create a playlist
2. Add songs to a playlist
3. Remove songs from a playlist
4. Play a song from a playlist
5. Update an existing playlist
6. Logout
```

### Admin Menu

```plaintext
=== Admin Menu ===
1. View all users
2. Remove a user
3. View all playlists
4. View all songs in a playlist
5. Logout
```

## 🔍 Key Implementation Details

### Error Handling

- Input validation for numeric entries
- Checks for existing usernames during registration
- Validation of playlist and song selections


### File I/O Operations

- Buffered reading and writing for efficient file operations
- Automatic file creation when needed
- Proper file closing to prevent resource leaks


### Data Structure Operations

- Insertion at the end of linked lists for users, playlists, and songs
- Traversal algorithms for finding and displaying items
- Removal algorithms that handle edge cases (first item, last item, etc.)


## 🚀 Getting Started

1. **First Launch**: On first run, an admin account is automatically created (username: "admin", password: "admin123")
2. **Login**: Access the system using your credentials or register a new account
3. **Create Playlists**: Start building your music collection
4. **Add Songs**: Populate your playlists with your favorite music
5. **Enjoy**: Navigate through your music collection with ease


## 💡 Future Enhancements

- Graphical user interface
- Search functionality for finding songs and playlists
- Sorting options for organizing playlists
- Import/export features for sharing playlists
- Media player integration for actual music playback
