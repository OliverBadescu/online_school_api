package mycode.online_school_api.app.view;


import jakarta.persistence.Column;
import lombok.extern.slf4j.Slf4j;
import mycode.online_school_api.app.model.Course;
import mycode.online_school_api.app.model.Enrolment;
import mycode.online_school_api.app.model.Student;
import mycode.online_school_api.app.repository.CourseRepository;
import mycode.online_school_api.app.repository.EnrolmentRepository;
import mycode.online_school_api.app.repository.StudentRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

@Slf4j
@Component
public class StudentView {

    private EnrolmentRepository enrolmentRepository;
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;
    private Scanner scanner;
    private Student student = null;

    public StudentView(EnrolmentRepository enrolmentRepository, CourseRepository courseRepository, StudentRepository studentRepository) {
        this.enrolmentRepository = enrolmentRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.scanner = new Scanner(System.in);

        playLogin();
    }


    public void loginMenu(){

        System.out.println("1. Login");
        System.out.println("2. Register");

        System.out.println("5. Exit");

    }

    public void playLogin(){


        boolean running = true;
        while (running) {
            loginMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                default:
                    System.out.println("Invalid choice");

            }

        }


    }

    public void menu(){

        System.out.println("1. Show courses");
        System.out.println("2. Enroll into a course");
        System.out.println("3. Show enrollments");

        System.out.println("6. Exit");

    }

    public void play(){

        boolean running = true;
        while (running) {
            menu();
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    showCourses();
                    break;

                case 2:
                    enrollIntoCourse(this.student);
                    break;
                case 3:
                    showEnrollments(this.student);
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void login(){

        System.out.println("Email: ");
        String email = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();

        Optional<Student> student = studentRepository.findByEmailAndPassword(email,password);

        if(student.isPresent()){
            this.student = student.get();
            play();
        }else {
            System.out.println("Login failed");
        }

    }

    private void register(){

        System.out.println("First name: ");
        String firstName = scanner.nextLine();
        System.out.println("Last name: ");
        String lastName = scanner.nextLine();
        System.out.println("Email: ");
        String email = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        System.out.println("Age: ");
        int age = Integer.parseInt(scanner.nextLine());

        Student student = Student.builder()
                .firstName(firstName)
                .lastName(lastName)
                .age(age).
                password(password)
                .email(email).build();

        studentRepository.saveAndFlush(student);

    }

    private void showCourses(){

        List<Course> list = courseRepository.findAll();

        list.forEach(System.out::println);

    }

    private void enrollIntoCourse(Student student){

        System.out.println("Name:");
        String name = scanner.nextLine();
        System.out.println("Department:");
        String department = scanner.nextLine();

        Optional<Course> course = courseRepository.findByNameAndDepartment(name, department);

        if (course.isPresent()) {
            Optional<List<Enrolment>> list = enrolmentRepository.findAllByStudentId(student.getId());

            boolean alreadyEnrolled = false;

            if (list.isPresent()) {
                for (Enrolment enrolment : list.get()) {
                    if (enrolment.getCourse().getId() == course.get().getId()) {
                        System.out.println("You are already enrolled in this course");
                        alreadyEnrolled = true;
                        break;
                    }
                }
            }

            if (!alreadyEnrolled) {
                Enrolment enrolment = Enrolment.builder()
                        .student(student)
                        .course(course.get())
                        .createdAt(LocalDate.now())
                        .build();
                enrolmentRepository.saveAndFlush(enrolment);
                System.out.println("Enrolled successfully in the course");
            }
        } else {
            System.out.println("Course not found.");
        }


    }

    private void showEnrollments(Student student){


        Optional<List<Enrolment>> list = enrolmentRepository.findAllByStudentId(student.getId());
        if(list.isPresent()){
            for (Enrolment enrolment : list.get()) {
                System.out.println(enrolment);
            }
        }


    }
}
