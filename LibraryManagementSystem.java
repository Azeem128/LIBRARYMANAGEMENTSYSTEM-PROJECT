import java.util.*;

public class LibraryManagementSystem {
        private List<Book> books = new ArrayList<>();
        private Map<Book, Student> borrowedBooks = new HashMap<>();
        private Scanner scanner = new Scanner(System.in);

        public static void main(String[] args) {
            LibraryManagementSystem library = new LibraryManagementSystem();
            library.run();
        }

        public void run() {
            while (true) {
                System.out.println("\nLibrary Management System Menu:");
                System.out.println("1. Add a book");
                System.out.println("2. Borrow a book");
                System.out.println("3. Return a book");
                System.out.println("4. List available books");
                System.out.println("5. Quit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addBook();
                        break;

                    case 2:
                        borrowBook();
                        break;

                    case 3:
                        returnBook();
                        break;

                    case 4:
                        listAvailableBooks();
                        break;

                    case 5:
                        System.out.println("Thank you for using the Library Management System.");
                        scanner.close();
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }

        private void addBook() {
            System.out.print("Enter the title of the book to add: ");
            String newBookTitle = scanner.nextLine();
            Book newBook = new Book(newBookTitle);
            books.add(newBook);
            System.out.println(newBookTitle + " has been added to the library.");
        }

        private void borrowBook() {
            System.out.print("Enter your name: ");
            String studentName = scanner.nextLine();

            System.out.print("Enter the title of the book to borrow: ");
            String bookToBorrowTitle = scanner.nextLine();

            Book bookToBorrow = findBookByTitle(bookToBorrowTitle);

            if (bookToBorrow != null && bookToBorrow.isAvailable()) {
                bookToBorrow.borrowBook();
                Student student = new Student(studentName);
                borrowedBooks.put(bookToBorrow, student);
                System.out.println("You have successfully borrowed " + bookToBorrowTitle);
            } else {
                System.out.println("Sorry, " + bookToBorrowTitle + " is not available.");
            }
        }

        private void returnBook() {
            System.out.print("Enter the title of the book to return: ");
            String bookToReturnTitle = scanner.nextLine();
            Book bookToReturn = findBookByTitle(bookToReturnTitle);

            if (bookToReturn != null && borrowedBooks.containsKey(bookToReturn)) {
                borrowedBooks.remove(bookToReturn);
                bookToReturn.returnBook();
                System.out.println("Thank you! You have returned " + bookToReturnTitle);
            } else {
                System.out.println("This book is not borrowed or doesn't exist in the system.");
            }
        }

        private void listAvailableBooks() {
            System.out.println("Available Books:");
            for (Book book : books) {
                if (book.isAvailable()) {
                    System.out.println(book.getTitle());
                }
            }
        }

        private Book findBookByTitle(String title) {
            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(title)) {
                    return book;
                }
            }
            return null;
        }
    }


