package tqsua;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private final List<Book> store = new ArrayList<>();

    public void add(Book book) {
        store.add(book);
    }

    public List<Book> findByAuthor(String author) {
        return store.stream().filter(book -> book.getAuthor().contains(author)).collect(Collectors.toList());
    }

    public List<Book> findByPublicationRange(LocalDateTime from, LocalDateTime to) {
        return store.stream().filter(
                book -> book.getPublished().isAfter(from) && book.getPublished().isBefore(to)
        ).collect(Collectors.toList());
    }
}
