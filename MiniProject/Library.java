import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;

public class Library {
    private HashMap<String, Book> inventory;
    private HashMap<String, Member> members;
    private static final String LOG_FILE = "logs/library_log.txt";

    public Library() {
        inventory = new HashMap<>();
        members = new HashMap<>();
    }

    public void addBook(Book book) {
        inventory.put(book.getId(), book);
        logOperation("Added book: " + book.getTitle());
    }

    public void addMember(Member member) {
        members.put(member.getMemberId(), member);
        logOperation("Added member: " + member.getName());
    }

    public void issueBook(String bookId, String memberId) throws BookNotAvailableException {
        Book book = inventory.get(bookId);
        Member member = members.get(memberId);

        if (book == null || member == null || book.isIssued()) {
            throw new BookNotAvailableException("Book not available or invalid member.");
        }

        book.setIssued(true);
        member.borrowBook(bookId);
        logOperation("Book issued: " + book.getTitle() + " to " + member.getName());
    }

    public void returnBook(String bookId, String memberId, int daysLate) throws InvalidReturnException {
        Book book = inventory.get(bookId);
        Member member = members.get(memberId);

        if (book == null || member == null || !book.isIssued() || !member.getBorrowedBookIds().contains(bookId)) {
            throw new InvalidReturnException("Invalid return operation.");
        }

        book.setIssued(false);
        member.returnBook(bookId);
        double fine = daysLate * 2.0;
        logOperation("Book returned: " + book.getTitle() + " by " + member.getName() +
                " | Fine: ₹" + fine);
    }

    public void showInventory() {
        System.out.println("\n--- Library Inventory ---");
        for (Book book : inventory.values()) {
            System.out.println(book);
        }
    }

    public void logOperation(String message) {
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write(LocalDateTime.now() + " - " + message + "\n");
        } catch (IOException e) {
            System.err.println("Error writing to log file.");
        }
    }
}
