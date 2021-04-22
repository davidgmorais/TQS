package tqsua;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BookSearchSteps {
    private final Library library = new Library();
    private List<Book> result = new ArrayList<>();

    @ParameterType("([0-9]{2}) ([a-zA-Z]+) ([0-9]{4})")
    public LocalDateTime iso8601Date(String day, String month, String year) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH);
        return LocalDate.parse(String.format("%s %s %s", day, month, year), formatter).atStartOfDay();
    }

    @Given("(a|another) book with the title {string}, written by {string}, published in {iso8601Date}")
    public void addBook(String title, String author, LocalDateTime release) {
        Book newBook = new Book(title, author, release);
        library.add(newBook);
    }

    @When("the customer searches for books by {string}")
    public void booksByAuthor(String author) {
        result = library.findByAuthor(author);
    }

    @When("the customer searches for books from {iso8601Date} to {iso8601Date}")
    public void booksInPublicationRange(LocalDateTime from, LocalDateTime to) {
        result = library.findByPublicationRange(from, to);
    }

    @Then("{int} book(s?) should be found")
    public void verifyAmountFound(int expected) {
        assertEquals(expected, result.size());
    }

    @Then("Book {int} should have the title {string}")
    public void verifyBookTitle(int position, String expectedTitle) {
        assertEquals(expectedTitle, result.get(position-1).getTitle());
    }
}
