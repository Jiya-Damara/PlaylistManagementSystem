### Music Playlist System 🎵

## Overview 📖

The Music Playlist System is a Java-based console application designed to manage music playlists using data structures and algorithms. It leverages singly linked lists to store users, playlists, and songs, providing functionalities like user registration, playlist creation, song management, and playback. A bubble sort algorithm sorts playlists alphabetically, ensuring a seamless user experience. 🚀

## Features ✨

- **User Management 👤**: Register and log in users with unique usernames and password validation.
- **Playlist Management 📚**: Create playlists, add/remove songs, and sort playlists by name.
- **Song Playback 🎧**: Select and "play" songs by displaying their details.
- **Data Structures 🗂️**: Uses singly linked lists (SongLinkedList, PlaylistLinkedList, UserLinkedList, StringLinkedList) for dynamic storage.
- **Algorithm ⚙️**: Implements bubble sort for alphabetical playlist sorting.
- **Error Handling 🛡️**: Robust validation for invalid inputs and edge cases.


## Prerequisites 🛠️

- Java Development Kit (JDK) 8 or higher ☕
- A Java-compatible IDE (e.g., IntelliJ IDEA, Eclipse) or command-line environment 💻


## Installation 📦

1. Clone or download the project repository to your local machine. 📥
2. Navigate to the project directory containing the source code. 📂
3. Compile the Java files:

```shellscript
javac MusicPlaylistSystem.java
```


4. Run the application:

```shellscript
java MusicPlaylistSystem
```




## Usage 🎮

### Launch the Application 🚀

Run the program to access the main menu.

### Main Menu Options:

- **Login 🔑**: Enter a registered username and password.
- **Register ✍️**: Create a new user account with a unique username and password.
- **Exit 🚪**: Close the application.


### Logged-In Menu:

- **Create a Playlist 📝**: Enter a unique playlist name.
- **Add Songs ➕**: Select a playlist and input song name and artist.
- **Remove Songs ➖**: Choose a playlist and specify a song to remove.
- **Play a Song ▶️**: Select a playlist and song to display its details.
- **Logout 👋**: Return to the main menu.


Follow console prompts to navigate and interact. 🖱️

## Project Structure 🏗️

### Core Classes:

- **Song 🎶**: Stores song details (songName, artistName) with comparison and display methods.
- **Playlist 📃**: Manages a SongLinkedList for songs with add, remove, and play operations.
- **User 👨‍💻**: Handles user data (username, password) and a PlaylistLinkedList.
- **MusicPlaylistSystem 🖥️**: Main class with console interface and menu logic.


### Data Structures 📊:

- **SongLinkedList**: Stores songs.
- **PlaylistLinkedList**: Manages playlists with bubble sort.
- **UserLinkedList**: Stores user accounts.
- **StringLinkedList**: Holds playlist names.


### Algorithm 🧮

Bubble sort in PlaylistLinkedList for alphabetical sorting.

## Example Workflow 🌟

### Register a user:

- **Input**: Username = "john_doe", Password = "pass123"
- **Output**: "User john_doe registered successfully!" ✅


### Log in:

- **Input**: Username = "john_doe", Password = "pass123"
- **Output**: "Welcome john_doe!" 👋


### Create a playlist:

- **Input**: Playlist name = "Rock Hits"
- **Output**: "Playlist 'Rock Hits' created successfully!" 🎉


### Add a song:

- **Input**: Playlist = "Rock Hits", Song = "Sweet Child O' Mine", Artist = "Guns N' Roses"
- **Output**: "Sweet Child O' Mine by Guns N' Roses added successfully to Rock Hits" 🎸


### Play a song:

- **Input**: Playlist = "Rock Hits", Song index = 1
- **Output**: "Now playing: Sweet Child O' Mine by Guns N' Roses" ▶️


## Contributing 🤝

Contributions are welcome! To contribute:

1. **Fork the repository.** 🍴
2. **Create a branch for your feature or bug fix.** 🌿
3. **Submit a pull request with a detailed description.** 📬


## License 📜

This project is licensed under the MIT License. See the LICENSE file for details.
