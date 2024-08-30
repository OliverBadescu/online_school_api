package mycode.online_school_api.app.repository;

import mycode.online_school_api.app.model.Course;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Integer> {


    @EntityGraph(attributePaths = {"enrolments"}, type = EntityGraph.EntityGraphType.FETCH)
    Optional<Course> findByNameAndDepartment(String name, String department);

    @EntityGraph(attributePaths = {"enrolments"}, type = EntityGraph.EntityGraphType.FETCH)
    Optional<Course> findById(int id);

}
