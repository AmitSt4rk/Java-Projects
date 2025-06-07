package Practice_Projects;

import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private String isbn;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
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

    public String getISBN() {
        return isbn;
    }

    public void setISBN(String isbn) {
        this.isbn = isbn;
    }

    public String bookDetails() {
        return "Title: " + title + ", Author: " + author + ", ISBN: " + isbn;
    }
}

class Library {
    ArrayList<Book> books = new ArrayList<>();

    public void addBook(Book book) {

        for(int i = 0; i < books.size(); i++){
            if (books.get(i).getISBN().equals(book.getISBN())) {
                System.out.println("Book already exist with ISBN: "+book.getISBN());
                return;
            }
        }
        books.add(book);
        System.out.println("Book added Successfully.");
    }

    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books: Library is Empty.");
        } else {
            for (Book b : books) {
                System.out.println(b.bookDetails());
            }
        }
    }

    public void bookByAuthor(String author) {
        boolean found = false;

        for (Book b : books) {
            if (b.getAuthor().equalsIgnoreCase(author)) {
                System.out.println(b.bookDetails());
                found = true;
            }
        }

        if (!found) {
            System.out.println("Books from "+author+"is not available");
        }
    }

    public void deleteByISBN(String isbn) {
        boolean found = false;

        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getISBN().equals(isbn)) {
                books.remove(i);
                found = true;
                System.out.println("Book deleted successfully.");
            }
        }
        if (!found) {
            System.out.println("Book not found from ISBN: "+isbn);
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library lib = new Library();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n____Library Management System____");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. View book by Author");
            System.out.println("4. Delete book by ISBN");
            System.out.println("5. Exit");

            System.out.print("\nEnter your Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter book Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author name: ");
                    String author = sc.nextLine();
                    System.out.print("Enter ISBN: ");
                    String isbn = sc.nextLine();

                    lib.addBook(new Book(title, author, isbn));
                    break;

                case 2:
                    System.out.println("All Books are:- ");
                    lib.viewBooks();
                    break;

                case 3:
                    System.out.print("Enter author name: ");
                    String a = sc.nextLine();

                    lib.bookByAuthor(a);
                    break;

                case 4:
                    System.out.print("Enter ISBN to Delete book: ");
                    String i = sc.nextLine();

                    lib.deleteByISBN(i);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Error: Wrong Choice.");
                    break;
            }
        }
    }

}
