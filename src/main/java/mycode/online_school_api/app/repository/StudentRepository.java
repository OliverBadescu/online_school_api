package mycode.online_school_api.app.repository;

import mycode.online_school_api.app.model.Student;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    @EntityGraph(attributePaths = {"books"}, type = EntityGraph.EntityGraphType.FETCH)
    Optional<Student> findByFirstNameAndLastName(String firstName, String lastName);
    @EntityGraph(attributePaths = {"books"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT s FROM Student s WHERE s.id IN (SELECT b.student.id FROM Book b GROUP BY b.student.id HAVING COUNT(b) = (SELECT MAX(book_count) FROM (SELECT COUNT(b1) as book_count FROM Book b1 GROUP BY b1.student.id) as subquery))")
    Optional<List<Student>> findStudentsWithMostBooks();



}
