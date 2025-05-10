import java.io.*;
import java.util.Scanner;

public class PlaylistManager {
    static Scanner sc = new Scanner(System.in);
    static final String USER_FILE = "users.txt";

    static class User {
        String username, password, role;
        User next;

        User(String u, String p, String r) {
            username = u;
            password = p;
            role = r;
        }
    }

    static class Song {
        String title, artist;
        Song next;

        Song(String t, String a) {
            title = t;
            artist = a;
        }

        public String toString() {
            return title + "," + artist;
        }

        static Song fromString(String line) {
            String[] parts = line.split(",");
            return new Song(parts[0], parts[1]);
        }
    }

    static class Playlist {
        String name;
        Song head;
        Playlist next;

        Playlist(String n) {
            name = n;
        }

        void addSong(String title, String artist) {
            Song s = new Song(title, artist);
            if (head == null) head = s;
            else {
                Song temp = head;
                while (temp.next != null) temp = temp.next;
                temp.next = s;
            }
        }

        void displaySongs() {
            if (head == null) System.out.println("No songs.");
            else {
                int i = 1;
                Song temp = head;
                while (temp != null) {
                    System.out.println(i++ + ". " + temp.title + " by " + temp.artist);
                    temp = temp.next;
                }
            }
        }

        Song getSong(int index) {
            int i = 1;
            Song temp = head;
            while (temp != null) {
                if (i == index) return temp;
                i++;
                temp = temp.next;
            }
            return null;
        }

        boolean removeSong(int index) {
            if (index == 1) {
                if (head != null) {
                    head = head.next;
                    return true;
                }
            }
            int i = 1;
            Song prev = null;
            Song curr = head;
            while (curr != null) {
                if (i == index) {
                    if (prev != null) prev.next = curr.next;
                    return true;
                }
                prev = curr;
                curr = curr.next;
                i++;
            }
            return false;
        }

        void saveToFile(String user) throws IOException {
            BufferedWriter bw = new BufferedWriter(new FileWriter("playlist-" + user + "-" + name + ".txt"));
            Song temp = head;
            while (temp != null) {
                bw.write(temp.toString());
                bw.newLine();
                temp = temp.next;
            }
            bw.close();
        }

        void loadFromFile(String user) throws IOException {
            head = null;
            File f = new File("playlist-" + user + "-" + name + ".txt");
            if (!f.exists()) return;

            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                Song s = Song.fromString(line);
                addSong(s.title, s.artist);
            }
            br.close();
        }
    }

    static class PlaylistList {
        Playlist head;

        void add(String name) {
            Playlist p = new Playlist(name);
            if (head == null) head = p;
            else {
                Playlist temp = head;
                while (temp.next != null) temp = temp.next;
                temp.next = p;
            }
        }

        Playlist get(int index) {
            int i = 1;
            Playlist temp = head;
            while (temp != null) {
                if (i == index) return temp;
                i++;
                temp = temp.next;
            }
            return null;
        }

        void display(String user) {
            sort(); // Sort playlists before displaying
            int i = 1;
            Playlist temp = head;
            while (temp != null) {
                try {
                    temp.loadFromFile(user);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int songCount = 0;
                Song s = temp.head;
                while (s != null) {
                    songCount++;
                    s = s.next;
                }
                System.out.println(i++ + ". " + temp.name + " (Songs: " + songCount + ")");
                temp = temp.next;
            }
        }

        void loadAll(String user) throws IOException {
            File[] files = new File(".").listFiles((dir, name) -> name.startsWith("playlist-" + user + "-") && name.endsWith(".txt"));
            if (files == null) return;
            for (File f : files) {
                String name = f.getName().replace("playlist-" + user + "-", "").replace(".txt", "");
                Playlist p = new Playlist(name);
                p.loadFromFile(user);
                if (head == null) head = p;
                else {
                    Playlist temp = head;
                    while (temp.next != null) temp = temp.next;
                    temp.next = p;
                }
            }
        }

        void sort() {
            if (head == null || head.next == null) {
                return; // Empty or single-node list is already sorted
            }

            boolean swapped;
            do {
                swapped = false;
                Playlist current = head;
                Playlist prev = null;

                while (current != null && current.next != null) {
                    Playlist next = current.next;
                    // Compare playlist names case-insensitively
                    if (current.name.compareToIgnoreCase(next.name) > 0) {
                        // Swap nodes
                        swapped = true;
                        if (prev == null) {
                            // Swapping at the head
                            head = next;
                            current.next = next.next;
                            next.next = current;
                        } else {
                            // Swapping in the middle or end
                            prev.next = next;
                            current.next = next.next;
                            next.next = current;
                        }
                        // Update prev to point to the new position of current
                        prev = next;
                    } else {
                        // No swap, move to next pair
                        prev = current;
                        current = next;
                    }
                }
            } while (swapped); // Continue until no swaps are needed
        }
    }

    static class UserList {
        User head;

        void load() throws IOException {
            head = null;
            File f = new File(USER_FILE);
            if (!f.exists()) f.createNewFile();

            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                add(data[0], data[1], data[2]);
            }
            br.close();
        }

        void save() throws IOException {
            BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE));
            User temp = head;
            while (temp != null) {
                bw.write(temp.username + "," + temp.password + "," + temp.role);
                bw.newLine();
                temp = temp.next;
            }
            bw.close();
        }

        void add(String u, String p, String r) {
            User user = new User(u, p, r);
            if (head == null) head = user;
            else {
                User temp = head;
                while (temp.next != null) temp = temp.next;
                temp.next = user;
            }
        }

        User find(String u, String p) {
            User temp = head;
            while (temp != null) {
                if (temp.username.equals(u) && temp.password.equals(p)) return temp;
                temp = temp.next;
            }
            return null;
        }

        boolean exists(String u) {
            User temp = head;
            while (temp != null) {
                if (temp.username.equals(u)) return true;
                temp = temp.next;
            }
            return false;
        }

        void display() {
            int i = 1;
            User temp = head;
            while (temp != null) {
                System.out.println(i++ + ". " + temp.username + " (" + temp.role + ")");
                temp = temp.next;
            }
        }

        boolean remove(int index) {
            if (index == 1) {
                if (head != null) {
                    head = head.next;
                    return true;
                }
            }
            int i = 1;
            User prev = null;
            User curr = head;
            while (curr != null) {
                if (i == index) {
                    if (prev != null) prev.next = curr.next;
                    return true;
                }
                prev = curr;
                curr = curr.next;
                i++;
            }
            return false;
        }

        User get(int index) {
            int i = 1;
            User temp = head;
            while (temp != null) {
                if (i == index) return temp;
                temp = temp.next;
                i++;
            }
            return null;
        }
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    static void updatePlaylist(String username, PlaylistList playlists) throws IOException {
        if (playlists.head == null) {
            System.out.println("No playlists available.");
            return;
        }

        System.out.println("Your playlists:");
        playlists.display(username);
        System.out.print("Choose a playlist to update (1-N): ");
        int playlistIndex = Integer.parseInt(sc.nextLine());
        Playlist selectedPlaylist = playlists.get(playlistIndex);

        if (selectedPlaylist == null) {
            System.out.println("Invalid playlist selection.");
            return;
        }

        selectedPlaylist.loadFromFile(username);

        if (selectedPlaylist.head == null) {
            System.out.println("This playlist is empty.");
            return;
        }

        System.out.println("Songs in " + selectedPlaylist.name + ":");
        selectedPlaylist.displaySongs();

        System.out.print("Choose a song to rename (1-N): ");
        int songIndex = Integer.parseInt(sc.nextLine());
        Song selectedSong = selectedPlaylist.getSong(songIndex);

        if (selectedSong == null) {
            System.out.println("Invalid song selection.");
            return;
        }

        System.out.println("Current song title: " + selectedSong.title);
        System.out.print("Enter new song title: ");
        String newTitle = sc.nextLine();

        System.out.print("Enter new artist name (or press Enter to keep current '" + selectedSong.artist + "'): ");
        String newArtist = sc.nextLine();

        selectedSong.title = newTitle;
        if (!newArtist.trim().isEmpty()) {
            selectedSong.artist = newArtist;
        }

        selectedPlaylist.saveToFile(username);
        System.out.println("Song updated successfully!");
    }

    public static void main(String[] args) throws IOException {
        UserList users = new UserList();
        users.load();
        if (!users.exists("admin")) {
            users.add("admin", "admin123", "Admin");
            users.save();
        }

        while (true) {
            System.out.println("\n=== Music Playlist System ===");
            System.out.println("1. Login\n2. Register\n3. Register Multiple Users\n4. Exit");
            System.out.print("Choose an option: ");
            int ch = Integer.parseInt(sc.nextLine());

            if (ch == 1) {
                System.out.print("Enter username: ");
                String user = sc.nextLine();
                System.out.print("Enter password: ");
                String pass = sc.nextLine();
                User u = users.find(user, pass);
                if (u != null) {
                    if (u.role.equals("Admin")) adminMenu(users);
                    else userMenu(user);
                } else System.out.println("Invalid username or password.");
            } else if (ch == 2) {
                System.out.print("Enter username: ");
                String u = sc.nextLine();
                System.out.print("Enter password: ");
                String p = sc.nextLine();
                if (users.exists(u)) System.out.println("Username already exists.");
                else {
                    users.add(u, p, "User");
                    users.save();
                    System.out.println("User " + u + " registered successfully!");
                }
            } else if (ch == 3) {
                registerMultipleUsers(users);
            } else if (ch == 4) {
                System.out.println("Thank you for using Music Playlist System. Goodbye!");
                break;
            } else System.out.println("Invalid choice.");
        }
    }

    static void registerMultipleUsers(UserList users) throws IOException {
        System.out.println("\n=== Register Multiple Users ===");
        System.out.print("Enter number of users to register: ");
        int numUsers = Integer.parseInt(sc.nextLine());
        
        int successCount = 0;
        for (int i = 1; i <= numUsers; i++) {
            System.out.println("\nRegistering User " + i + "/" + numUsers);
            System.out.print("Enter username: ");
            String username = sc.nextLine();
            
            if (users.exists(username)) {
                System.out.println("Username '" + username + "' already exists. Skipping this user.");
                continue;
            }
            
            System.out.print("Enter password: ");
            String password = sc.nextLine();
            
            users.add(username, password, "User");
            successCount++;
            System.out.println("User '" + username + "' registered successfully!");
        }
        
        if (successCount > 0) {
            users.save();
            System.out.println("\nSuccessfully registered " + successCount + " out of " + numUsers + " users.");
        } else {
            System.out.println("\nNo users were registered.");
        }
    }

    static void userMenu(String username) throws IOException {
        PlaylistList playlists = new PlaylistList();
        playlists.loadAll(username);

        while (true) {
            System.out.println("\n=== User Menu ===");
            System.out.println("1. Create a playlist");
            System.out.println("2. Add songs to a playlist");
            System.out.println("3. Remove songs from a playlist");
            System.out.println("4. Play a song from a playlist");
            System.out.println("5. Update an existing playlist");
            System.out.println("6. Logout");
            System.out.print("Choose an option: ");
            int ch = Integer.parseInt(sc.nextLine());

            if (ch == 1) {
                System.out.print("Enter playlist name: ");
                String name = sc.nextLine();
                playlists.add(name);
                System.out.println("Playlist '" + name + "' created successfully!");
            } else if (ch == 2) {
                if (playlists.head == null) System.out.println("No playlists available.");
                else {
                    System.out.println("Your playlists:");
                    playlists.display(username);
                    System.out.print("Choose a playlist (1-N): ");
                    int idx = Integer.parseInt(sc.nextLine());
                    Playlist p = playlists.get(idx);
                    if (p == null) System.out.println("Invalid selection.");
                    else {
                        System.out.print("Enter song name: ");
                        String t = sc.nextLine();
                        System.out.print("Enter artist name: ");
                        String a = sc.nextLine();
                        p.addSong(t, a);
                        p.saveToFile(username);
                        System.out.println(t + " by " + a + " added successfully to " + p.name);
                    }
                }
            } else if (ch == 3) {
                System.out.println("Your playlists:");
                playlists.display(username);
                System.out.print("Choose a playlist: ");
                int idx = Integer.parseInt(sc.nextLine());
                Playlist p = playlists.get(idx);
                if (p != null) {
                    p.loadFromFile(username);
                    p.displaySongs();
                    int s = getIntInput("Enter song number to remove: ");

                    if (p.removeSong(s)) {
                        p.saveToFile(username);
                        System.out.println("Song removed.");
                    } else System.out.println("Invalid song number.");
                }
            } else if (ch == 4) {
                playlists.display(username);
                System.out.print("Choose a playlist: ");
                int idx = Integer.parseInt(sc.nextLine());
                Playlist p = playlists.get(idx);
                if (p != null) {
                    p.loadFromFile(username);
                    p.displaySongs();
                    System.out.print("Choose a song to play: ");
                    int s = Integer.parseInt(sc.nextLine());
                    Song song = p.getSong(s);
                    if (song != null) System.out.println("Now playing: " + song.title + " by " + song.artist);
                    else System.out.println("Invalid song selection.");
                }
            } else if (ch == 5) {
                updatePlaylist(username, playlists);
            } else if (ch == 6) {
                System.out.println("Logged out successfully.");
                break;
            } else System.out.println("Invalid choice.");
        }
    }

    static void adminMenu(UserList users) throws IOException {
        while (true) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. View all users");
            System.out.println("2. Remove a user");
            System.out.println("3. View all playlists");
            System.out.println("4. View all songs in a playlist");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            int ch = Integer.parseInt(sc.nextLine());

            if (ch == 1) {
                System.out.println("\n=== All Users ===");
                users.display();
            } else if (ch == 2) {
                users.display();
                System.out.print("Choose user to remove: ");
                int idx = Integer.parseInt(sc.nextLine());
                if (users.remove(idx)) {
                    users.save();
                    System.out.println("User removed.");
                } else System.out.println("Invalid selection.");
            } else if (ch == 3) {
                System.out.println("\n=== All Playlists ===");
                User temp = users.head;
                while (temp != null) {
                    System.out.println("\nUser: " + temp.username);
                    String currentUsername = temp.username;
                    File[] files = new File(".").listFiles((dir, name) -> name.startsWith("playlist-" + currentUsername));

                    int i = 1;
                    for (File f : files) {
                        String name = f.getName().replace("playlist-" + temp.username + "-", "").replace(".txt", "");
                        BufferedReader br = new BufferedReader(new FileReader(f));
                        int count = 0;
                        while (br.readLine() != null) count++;
                        br.close();
                        System.out.println(i++ + ". " + name + " (Songs: " + count + ")");
                    }
                    temp = temp.next;
                }
            } else if (ch == 4) {
                System.out.println("\n=== View Songs in Playlist ===");
                users.display();
                System.out.print("Choose a user: ");
                int uidx = Integer.parseInt(sc.nextLine());
                User u = users.get(uidx);
                if (u == null) System.out.println("Invalid user selection.");
                else {
                    File[] files = new File(".").listFiles((dir, name) -> name.startsWith("playlist-" + u.username));
                    int i = 1;
                    for (File f : files) {
                        String name = f.getName().replace("playlist-" + u.username + "-", "").replace(".txt", "");
                        System.out.println(i++ + ". " + name);
                    }
                    System.out.print("Choose a playlist: ");
                    int pidx = Integer.parseInt(sc.nextLine());
                    if (pidx <= 0 || pidx > files.length) System.out.println("Invalid playlist.");
                    else {
                        BufferedReader br = new BufferedReader(new FileReader(files[pidx - 1]));
                        String line;
                        int j = 1;
                        while ((line = br.readLine()) != null) {
                            Song s = Song.fromString(line);
                            System.out.println(j++ + ". " + s.title + " by " + s.artist);
                        }
                        br.close();
                    }
                }
            } else if (ch == 5) {
                System.out.println("Logged out successfully.");
                break;
            } else System.out.println("Invalid option.");
        }
    }

    private static void savePlaylists(PlaylistList playlists) throws IOException {
        Playlist temp = playlists.head;
        while (temp != null) {
            temp.saveToFile(temp.name);
            temp = temp.next;
        }
    }

    private static void loadPlaylists(PlaylistList playlists, String username) throws IOException {
        File folder = new File(".");
        File[] files = folder.listFiles((dir, name) -> name.startsWith("playlist-" + username + "-") && name.endsWith(".txt"));

        if (files == null) return;

        for (File file : files) {
            String playlistName = file.getName().replace("playlist-" + username + "-", "").replace(".txt", "");
            Playlist playlist = new Playlist(playlistName);
            
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                Song song = Song.fromString(line);
                playlist.addSong(song.title, song.artist);
            }
            br.close();

            if (playlists.head == null) playlists.head = playlist;
            else {
                Playlist temp = playlists.head;
                while (temp.next != null) temp = temp.next;
                temp.next = playlist;
            }
        }
    }
