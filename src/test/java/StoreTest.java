import org.eBookStore.models.DemoBook;
import org.eBookStore.models.EBook;
import org.eBookStore.models.PaperBook;
import org.eBookStore.models.Store;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
public class StoreTest {
    private Store store;
    private PaperBook paperBook;
    private EBook eBook;
    private DemoBook demoBook;

    @BeforeEach
    public void setUp() throws Exception {
        store = Store.getInstance();
        store.inventory.clearBooks();
        paperBook = new PaperBook(100.0, "ISBN-PAPER", "Paper Book", 5);
        eBook = new EBook(50.0, "ISBN-EBOOK", "E-Book", "pdf");
        demoBook = new DemoBook(0.0, "ISBN-DEMO", "Demo Book");
        store.inventory.addBook(paperBook);
        store.inventory.addBook(eBook);
        store.inventory.addBook(demoBook);
    }

    @Test
    @DisplayName("Buy Paper Book")
    public void testBuyPaperBook() throws Exception {
            store.Buy("ISBN-PAPER", 2, "user@example.com", "123 Main St");
            assertEquals(3, paperBook.getQuantity());

    }

    @Test
    @DisplayName("But E-Book")
    public void testBuyEBook() throws Exception {
            store.Buy("ISBN-EBOOK", 1, "user@example.com", null);
            assertTrue(store.inventory.Books.contains(eBook));

    }

    @Test
    @DisplayName("Store Test || Out of stock")
    public void testBuyMoreThanAvailable() {
        try {
            store.Buy("ISBN-PAPER", 10, "user@example.com", "123 Main St");
        } catch (Exception e) {
            assertEquals("Book: Paper Book out of stock.", e.getMessage());
        }
    }

    @Test
    @DisplayName("Remove out dated books")
    public void testRemoveOutdatedBooks() {
        paperBook.setPublishDate(LocalDate.now().minusYears(6));
        store.inventory.removeOutDatedBooks();
        assertFalse(store.inventory.Books.contains(paperBook));
    }

    @Test
    @DisplayName("Add book with negative price")
    public void testAddBookWithNegativePrice() {
        try {
            store.inventory.addBook(new PaperBook(-10.0, "ISBN-NEGATIVE", "Negative Price Book", 5));
        } catch (Exception e) {
            assertEquals("can not add negative price", e.getMessage());
        }
    }

    @Test
    @DisplayName("Add book with empty ISBN")
    public void testAddBookWithEmptyISBN() {
        try {
            store.inventory.addBook(new PaperBook(10.0, "", "Empty ISBN Book", 5));
        } catch (Exception e) {
            assertEquals("can not add empty ISBN", e.getMessage());
        }
    }

    @Test
    @DisplayName("Buy non existence book")
    public void testBuyNonExistentBook() {
        try {
            store.Buy("ISBN-NOTFOUND", 1, "user@example.com", null);
        } catch (Exception e) {
            assertEquals("We do not have this book.", e.getMessage());
        }
    }

    @Test
    @DisplayName("Remove out dated books when non exist")
    public void testRemoveOutdatedBooksWhenNoneExist() {
        store.inventory.removeOutDatedBooks();
        assertEquals(3, store.inventory.Books.size());
    }

    @Test
    @DisplayName("Remove book from stock")
    public void testRemoveBookFromStock() {
        store.inventory.remove(paperBook);
        assertFalse(store.inventory.Books.contains(paperBook));
    }
}