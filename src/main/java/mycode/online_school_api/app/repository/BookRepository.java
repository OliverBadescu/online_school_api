package mycode.online_school_api.app.repository;

import mycode.online_school_api.app.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {


    Optional<Book> findByBookName(String bookName);


    //todo: refactor
    @Query("SELECT b.bookName, COUNT(b.student) as studentCount FROM Book b GROUP BY b.bookName ORDER BY COUNT(b.student) DESC")
    Optional<List<Object[]>> bookThatMostStudentsHave();

}
