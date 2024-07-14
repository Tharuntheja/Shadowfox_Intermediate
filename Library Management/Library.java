package Intermediate;
import java.util.List;
import java.util.Scanner;

class Book {
    private int id;
    private String title;
    private String author;
    private String genre;
    private boolean available;

    public Book(String title, String author, String genre, boolean available) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.available = available;
    }
    public int getId() { 
        return id; 
    }
    public void setId(int id) {
        this.id = id; 
    }
    public String getTitle() { 
        return title; 
    }
    public void setTitle(String title) { 
        this.title = title; 
    }
    public String getAuthor() { 
        return author; 
    }
    public void setAuthor(String author) { 
        this.author = author; 
    }
    public String getGenre() { 
        return genre; 
    }
    public void setGenre(String genre) { 
        this.genre = genre; 
    }
    public boolean isAvailable() { 
        return available; 
    }
    public void setAvailable(boolean available) { 
        this.available = available; 
    }
}
public class Library {
    public static void main(String[] args) {
        Database.createTables();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Library Management");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter email: ");
                    String email = sc.nextLine();
                    System.out.print("Enter password: ");
                    String password = sc.nextLine();

                    users newUser = new users(name, email, password);
                    Manager.registerUser(newUser);
                    System.out.println("Registration successful.");
                    break;
                case 2:
                    System.out.print("Enter email: ");
                    email = sc.nextLine();
                    System.out.print("Enter password: ");
                    password = sc.nextLine();

                    users user = Manager.loginUser(email, password);
                    if (user != null) {
                        System.out.println("Login successful.");
                        userMenu(user, sc);
                    } else {
                        System.out.println("Invalid email or password.");
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void userMenu(users user, Scanner scan) {
        while (true) {
            System.out.println("\nUser Menu");
            System.out.println("1. View Recommended Books");
            System.out.println("2. Logout");
            System.out.print("Enter your choice: ");

            int choice = scan.nextInt();
            scan.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    List<Book> recommendations = Manager.recommendBooks(user);
                    System.out.println("Recommended Books:");
                    for (Book book : recommendations) {
                        System.out.println("Title: " + book.getTitle());
                        System.out.println("Author: " + book.getAuthor());
                        System.out.println("Genre: " + book.getGenre());
                        System.out.println("Available: " + (book.isAvailable() ? "Yes" : "No"));
                        System.out.println();
                    }
                    break;
                case 2:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
