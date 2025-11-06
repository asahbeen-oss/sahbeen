import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Library Menu =====");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Show Inventory");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter Book ID: ");
                        String id = sc.nextLine();
                        System.out.print("Enter Title: ");
                        String title = sc.nextLine();
                        System.out.print("Enter Author: ");
                        String author = sc.nextLine();
                        library.addBook(new Book(id, title, author));
                        break;

                    case 2:
                        System.out.print("Enter Member ID: ");
                        String memberId = sc.nextLine();
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        library.addMember(new Member(memberId, name));
                        break;

                    case 3:
                        System.out.print("Enter Book ID: ");
                        String bId = sc.nextLine();
                        System.out.print("Enter Member ID: ");
                        String mId = sc.nextLine();
                        library.issueBook(bId, mId);
                        break;

                    case 4:
                        System.out.print("Enter Book ID: ");
                        String rbId = sc.nextLine();
                        System.out.print("Enter Member ID: ");
                        String rmId = sc.nextLine();
                        System.out.print("Enter Days Late: ");
                        int daysLate = sc.nextInt();
                        library.returnBook(rbId, rmId, daysLate);
                        break;

                    case 5:
                        library.showInventory();
                        break;

                    case 6:
                        System.out.println("Exiting... Goodbye!");
                        sc.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (BookNotAvailableException | InvalidReturnException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
