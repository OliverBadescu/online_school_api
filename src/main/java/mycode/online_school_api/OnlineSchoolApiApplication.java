package mycode.online_school_api;

import mycode.online_school_api.app.model.Book;
import mycode.online_school_api.app.model.Student;
import mycode.online_school_api.app.repository.BookRepository;
import mycode.online_school_api.app.repository.StudentRepository;
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
    CommandLineRunner commandLineRunner(StudentRepository studentRepository, BookRepository bookRepository){
        return args -> {

            Optional<Student> student = studentRepository.findByFirstNameAndLastName("Maria", "Popescu");






        };
    }

}
