package mycode.online_school_api.app.view;


import lombok.extern.slf4j.Slf4j;
import mycode.online_school_api.app.model.Student;
import mycode.online_school_api.app.repository.StudentRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Slf4j
//@Component
public class LoginView {

    private StudentRepository studentRepository;
    private StudentView studentView;
    private Scanner scanner;

    public LoginView(StudentRepository studentRepository, StudentView studentView) {
        this.studentRepository = studentRepository;
        this.studentView = studentView;
        this.scanner = new Scanner(System.in);

        play();
    }


    private void meniu(){

        System.out.println("1. Login");
        System.out.println("2. Register");

        System.out.println("5. Exit");

    }

    public void play(){

        boolean running = true;

        while (running) {
            meniu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid input");
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

}
