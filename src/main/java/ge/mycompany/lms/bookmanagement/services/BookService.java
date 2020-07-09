package ge.mycompany.lms.bookmanagement.services;

import ge.mycompany.lms.bookmanagement.entities.Book;
import ge.mycompany.lms.bookmanagement.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public Book add(Book givenBook) {
        Book existingBook = findByTitle(givenBook.getTitle());
        if (existingBook != null) {
            existingBook.setCount(existingBook.getCount() + givenBook.getCount());
            return bookRepository.save(existingBook);
        }
        return bookRepository.save(givenBook);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}
