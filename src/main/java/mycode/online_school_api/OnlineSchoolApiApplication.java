package mycode.online_school_api;

import mycode.online_school_api.app.model.Course;
import mycode.online_school_api.app.model.Enrolment;
import mycode.online_school_api.app.model.Student;
import mycode.online_school_api.app.repository.BookRepository;
import mycode.online_school_api.app.repository.CourseRepository;
import mycode.online_school_api.app.repository.EnrolmentRepository;
import mycode.online_school_api.app.repository.StudentRepository;
import mycode.online_school_api.app.view.StudentView;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootApplication
public class OnlineSchoolApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineSchoolApiApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository, BookRepository bookRepository, CourseRepository courseRepository, EnrolmentRepository enrolmentRepository){
        return args -> {




//            Optional<Student> student = studentRepository.findByEmailAndPassword("maria.popescu@email.com", "SecurePass567!");
//
//            StudentView studentView = new StudentView(enrolmentRepository,courseRepository,studentRepository);
//
//            studentView.enrollIntoCourse(student.get());

        };
    }

}
