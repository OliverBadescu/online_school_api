package mycode.online_school_api.app.repository;

import mycode.online_school_api.app.model.Enrolment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnrolmentRepository extends JpaRepository<Enrolment, Integer> {

    Optional<List<Enrolment>> findAllByStudentId(int id);


}
