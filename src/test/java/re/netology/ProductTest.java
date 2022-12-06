package re.netology;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.TShirt;
import ru.netology.exceptions.NotFoundException;
import ru.netology.repository.Repository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductTest {
    private Repository repository = new Repository();

    Book firstBook = new Book(1, "Book1", 100, "Author1", 10, 2001);
    Book secondBook = new Book(2, "Book2", 200, "Author2", 20, 2002);
    Book thirdBook = new Book(3, "Book3", 300, "Author3", 30, 2003);
    TShirt firstTShirt = new TShirt(4, "TShirt1", 400, "blue", "Size 21");
    TShirt secondTShirt = new TShirt(5, "TShirt2", 500, "red", "Size 22");
    TShirt thirdTShirt = new TShirt(6, "TShirt3", 600, "black", "Size 23");

    @Test
    public void shouldAssesDeleteElement() {
        repository.save(firstBook);
        repository.save(secondBook);
        repository.save(thirdBook);
        repository.save(firstTShirt);
        repository.save(secondTShirt);
        repository.save(thirdTShirt);
        repository.removeById(4);

        Product[] expected = {firstBook, secondBook, thirdBook, secondTShirt, thirdTShirt};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldExceptionWhenNoItem() {
        repository.save(firstBook);
        repository.save(secondBook);
        repository.save(firstTShirt);
        repository.save(secondTShirt);

        assertThrows(NotFoundException.class, () -> {repository.removeById(3);});
    }
}
