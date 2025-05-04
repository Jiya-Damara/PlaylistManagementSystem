import java.util.Scanner;

class SongNode {
    Song data;
    SongNode next;
    
    SongNode(Song data) {
        this.data = data;
        this.next = null;
    }
}

class PlaylistNode {
    Playlist data;
    PlaylistNode next;
    
    PlaylistNode(Playlist data) {
        this.data = data;
        this.next = null;
    }
}

class UserNode {
    User data;
    UserNode next;
    
    UserNode(User data) {
        this.data = data;
        this.next = null;
    }
}

class Song {
    String songName;
    String artistName;

    Song(String songName, String artistName) {
        this.songName = songName;
        this.artistName = artistName;
    }

    @Override
    public String toString() {
        return songName + " by " + artistName;
    }
    
    public boolean equals(Song other) {
        if (other == null) return false;
        return songName.equals(other.songName) && artistName.equals(other.artistName);
    }
}

class SongLinkedList {
    private SongNode head;
    private int size;
    
    public SongLinkedList() {
        this.head = null;
        this.size = 0;
    }
    
    public void add(Song song) {
        SongNode newNode = new SongNode(song);
        
        if (head == null) {
            head = newNode;
        } else {
            SongNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }
    
    public boolean remove(String songName, String artistName) {
        if (head == null) {
            return false;
        }
        
        // If the head is the song to remove
        if (head.data.songName.equals(songName) && head.data.artistName.equals(artistName)) {
            head = head.next;
            size--;
            return true;
        }
        
        // Check the rest of the list
        SongNode current = head;
        while (current.next != null) {
            if (current.next.data.songName.equals(songName) && 
                current.next.data.artistName.equals(artistName)) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        
        return false;
    }

    public Song get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        
        SongNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        
        return current.data;
    }

    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return head == null;
    }
}

class PlaylistLinkedList {
    private PlaylistNode head;
    private int size;
    
    public PlaylistLinkedList() {
        this.head = null;
        this.size = 0;
    }
    
    public void add(Playlist playlist) {
        PlaylistNode newNode = new PlaylistNode(playlist);
        
        if (head == null) {
            head = newNode;
        } else {
            PlaylistNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public Playlist get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        
        PlaylistNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        
        return current.data;
    }
    
    public Playlist findByName(String name) {
        PlaylistNode current = head;
        
        while (current != null) {
            if (current.data.getName().equals(name)) {
                return current.data;
            }
            current = current.next;
        }
        
        return null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }
    
    // Sort playlists by name (bubble sort)
    public void sort() {
        if (head == null || head.next == null) {
            return; 
        }
        
        boolean swapped;
        PlaylistNode ptr1;
        PlaylistNode lptr = null;
        
        do {
            swapped = false;
            ptr1 = head;
            
            while (ptr1.next != lptr) {
                if (ptr1.data.getName().compareTo(ptr1.next.data.getName()) > 0) {
                    // Swap the data
                    Playlist temp = ptr1.data;
                    ptr1.data = ptr1.next.data;
                    ptr1.next.data = temp;
                    swapped = true;
                }
                ptr1 = ptr1.next;
            }
            lptr = ptr1;
        } while (swapped);
    }
}

class UserLinkedList {
    private UserNode head;
    private int size;
    
    public UserLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public void add(User user) {
        UserNode newNode = new UserNode(user);
        
        if (head == null) {
            head = newNode;
        } else {
            UserNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public User findByUsername(String username) {
        UserNode current = head;
        
        while (current != null) {
            if (current.data.getUsername().equals(username)) {
                return current.data;
            }
            current = current.next;
        }
        
        return null;
    }
    
    public User get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        
        UserNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        
        return current.data;
    }
    
    public int size() {
        return size;
    }
}

class StringLinkedList {
    private class StringNode {
        String data;
        StringNode next;
        
        StringNode(String data) {
            this.data = data;
            this.next = null;
        }
    }
    
    private StringNode head;
    private int size;
    
    public StringLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public void add(String str) {
        StringNode newNode = new StringNode(str);
        
        if (head == null) {
            head = newNode;
        } else {
            StringNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }
   
    public String get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        
        StringNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        
        return current.data;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }
}

class Playlist {
    private String name;
    private SongLinkedList songs;

    public Playlist(String name) {
        this.name = name;
        this.songs = new SongLinkedList();
    }

    public String getName() {
        return name;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public boolean removeSong(String songName, String artistName) {
        return songs.remove(songName, artistName);
    }

    public SongLinkedList getAllSongs() {
        return songs;
    }

    public boolean isEmpty() {
        return songs.isEmpty();
    }

    public Song playSong(int index) {
        if (isEmpty()) {
            return null;
        }
        
        return songs.get(index);
    }
}

class User {
    private String username;
    private String password;
    private PlaylistLinkedList playlists;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.playlists = new PlaylistLinkedList();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public PlaylistLinkedList getPlaylists() {
        return playlists;
    }

    public void addPlaylist(Playlist playlist) {
        playlists.add(playlist);
    }

    public Playlist findPlaylist(String name) {
        return playlists.findByName(name);
    }

    public StringLinkedList getPlaylistNames() {
        StringLinkedList names = new StringLinkedList();
        for (int i = 0; i < playlists.size(); i++) {
            names.add(playlists.get(i).getName());
        }
        return names;
    }

    public void sortPlaylists() {
        playlists.sort();
    }
}

public class MusicPlaylistSystem {
    private static UserLinkedList users = new UserLinkedList();
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser = null;

    public static void main(String[] args) {
        boolean exit = false;
        
        while (!exit) {
            if (currentUser == null) {
                System.out.println("\n=== Music Playlist System ===");
                System.out.println("1. Login");
                System.out.println("2. Register");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");
                
                int choice = getIntInput();
                
                switch (choice) {
                    case 1:
                        login();
                        break;
                    case 2:
                        registerUsers();
                        break;
                    case 3:
                        exit = true;
                        System.out.println("Thank you for using Music Playlist System. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } else {
                showMainMenu();
            }
        }
        
        scanner.close();
    }

    private static void login() {
        if (users.size() == 0) {
            System.out.println("No users registered. Please register first.");
            return;
        }
        
        System.out.println("\nRegistered Users:");
        for (int i = 0; i < users.size(); i++) {
            System.out.println((i + 1) + ". " + users.get(i).getUsername());
        }
        
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        User user = users.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            System.out.println("Welcome " + username + "!");
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private static void registerUsers() {
        System.out.print("How many users do you want to register? ");
        int numUsers = getIntInput();
        
        if (numUsers <= 0) {
            System.out.println("Invalid number. Please enter a positive number.");
            return;
        }
        
        for (int i = 0; i < numUsers; i++) {
            System.out.println("\nRegistering User " + (i + 1) + ":");
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            
            // Check if username exists
            if (users.findByUsername(username) != null) {
                System.out.println("Username already exists. Please choose another one.");
                i--; // Retry this user
                continue;
            }
            
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            
            User newUser = new User(username, password);
            users.add(newUser);
            
            System.out.println("User " + username + " registered successfully!");
        }
        
        System.out.println("\nAll users registered successfully! You can now login.");
    }

    private static void showMainMenu() {
        boolean logout = false;
        
        while (!logout) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Create a playlist");
            System.out.println("2. Add songs to a playlist");
            System.out.println("3. Remove songs from a playlist");
            System.out.println("4. Play a song from a playlist");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    createPlaylist();
                    break;
                case 2:
                    addSongToPlaylist();
                    break;
                case 3:
                    removeSongFromPlaylist();
                    break;
                case 4:
                    playSongFromPlaylist();
                    break;
                case 5:
                    logout = true;
                    currentUser = null;
                    System.out.println("Logged out successfully.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void createPlaylist() {
        System.out.print("Enter playlist name: ");
        String playlistName = scanner.nextLine();
        
        if (currentUser.findPlaylist(playlistName) != null) {
            System.out.println("A playlist with this name already exists.");
            return;
        }
        
        Playlist newPlaylist = new Playlist(playlistName);
        currentUser.addPlaylist(newPlaylist);
        System.out.println("Playlist '" + playlistName + "' created successfully!");
    }

    // Add a song to a playlist
    private static void addSongToPlaylist() {
        StringLinkedList playlistNames = currentUser.getPlaylistNames();
        
        if (playlistNames.isEmpty()) {
            System.out.println("You don't have any playlists. Create one first.");
            return;
        }
        
        System.out.println("Your playlists:");
        for (int i = 0; i < playlistNames.size(); i++) {
            System.out.println((i + 1) + ". " + playlistNames.get(i));
        }
        
        System.out.print("Choose a playlist (1-" + playlistNames.size() + "): ");
        int playlistIndex = getIntInput() - 1;
        
        if (playlistIndex < 0 || playlistIndex >= playlistNames.size()) {
            System.out.println("Invalid playlist selection.");
            return;
        }
        
        String selectedPlaylistName = playlistNames.get(playlistIndex);
        Playlist selectedPlaylist = currentUser.findPlaylist(selectedPlaylistName);
        
        System.out.print("Enter song name: ");
        String songName = scanner.nextLine();
        System.out.print("Enter artist name: ");
        String artistName = scanner.nextLine();
        
        Song newSong = new Song(songName, artistName);
        selectedPlaylist.addSong(newSong);
        
        System.out.println(songName + " by " + artistName + " added successfully to " + selectedPlaylistName);
    }

    // Remove a song from a playlist
    private static void removeSongFromPlaylist() {
        StringLinkedList playlistNames = currentUser.getPlaylistNames();
        
        if (playlistNames.isEmpty()) {
            System.out.println("You don't have any playlists.");
            return;
        }
        
        System.out.println("Your playlists:");
        for (int i = 0; i < playlistNames.size(); i++) {
            System.out.println((i + 1) + ". " + playlistNames.get(i));
        }
        
        System.out.print("Choose a playlist (1-" + playlistNames.size() + "): ");
        int playlistIndex = getIntInput() - 1;
        
        if (playlistIndex < 0 || playlistIndex >= playlistNames.size()) {
            System.out.println("Invalid playlist selection.");
            return;
        }
        
        String selectedPlaylistName = playlistNames.get(playlistIndex);
        Playlist selectedPlaylist = currentUser.findPlaylist(selectedPlaylistName);
        
        SongLinkedList songs = selectedPlaylist.getAllSongs();
        
        if (songs.isEmpty()) {
            System.out.println("This playlist is empty.");
            return;
        }
        
        System.out.println("Songs in " + selectedPlaylistName + ":");
        for (int i = 0; i < songs.size(); i++) {
            System.out.println((i + 1) + ". " + songs.get(i));
        }
        
        System.out.print("Enter song name to remove: ");
        String songName = scanner.nextLine();
        System.out.print("Enter artist name: ");
        String artistName = scanner.nextLine();
        
        boolean removed = selectedPlaylist.removeSong(songName, artistName);
        
        if (removed) {
            System.out.println(songName + " by " + artistName + " successfully deleted from " + selectedPlaylistName);
        } else {
            System.out.println("Song not found in the playlist.");
        }
    }

    // Play a song from a playlist
    private static void playSongFromPlaylist() {
        StringLinkedList playlistNames = currentUser.getPlaylistNames();
        
        if (playlistNames.isEmpty()) {
            System.out.println("You don't have any playlists.");
            return;
        }
        
        System.out.println("Your playlists:");
        for (int i = 0; i < playlistNames.size(); i++) {
            System.out.println((i + 1) + ". " + playlistNames.get(i));
        }
        
        System.out.print("Choose a playlist (1-" + playlistNames.size() + "): ");
        int playlistIndex = getIntInput() - 1;
        
        if (playlistIndex < 0 || playlistIndex >= playlistNames.size()) {
            System.out.println("Invalid playlist selection.");
            return;
        }
        
        String selectedPlaylistName = playlistNames.get(playlistIndex);
        Playlist selectedPlaylist = currentUser.findPlaylist(selectedPlaylistName);
        
        SongLinkedList songs = selectedPlaylist.getAllSongs();
        
        if (songs.isEmpty()) {
            System.out.println("This playlist is empty.");
            return;
        }
        
        System.out.println("Songs in " + selectedPlaylistName + ":");
        for (int i = 0; i < songs.size(); i++) {
            System.out.println((i + 1) + ". " + songs.get(i));
        }
        
        System.out.print("Choose a song to play (1-" + songs.size() + "): ");
        int songIndex = getIntInput() - 1;
        
        if (songIndex < 0 || songIndex >= songs.size()) {
            System.out.println("Invalid song selection.");
            return;
        }
        
        Song selectedSong = songs.get(songIndex);
        System.out.println("Now playing: " + selectedSong);
    }

    // Helper method to get integer input
    private static int getIntInput() {
        try {
            int input = Integer.parseInt(scanner.nextLine());
            return input;
        } catch (NumberFormatException e) {
            return -1; 
        }
    }
}
