package mycode.online_school_api.app.view;


import mycode.online_school_api.app.model.Book;
import mycode.online_school_api.app.model.Course;
import mycode.online_school_api.app.model.Enrolment;
import mycode.online_school_api.app.model.Student;
import mycode.online_school_api.app.repository.BookRepository;
import mycode.online_school_api.app.repository.CourseRepository;
import mycode.online_school_api.app.repository.EnrolmentRepository;
import mycode.online_school_api.app.repository.StudentRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

//@Component
public class View {

    private StudentRepository studentRepository;
    private BookRepository bookRepository;
    private CourseRepository courseRepository;
    private EnrolmentRepository enrolmentRepository;
    private Scanner scanner;

    public View(StudentRepository studentRepository, BookRepository bookRepository, CourseRepository courseRepository, EnrolmentRepository enrolmentRepository){
        this.studentRepository =studentRepository;
        this.bookRepository = bookRepository;
        this.courseRepository = courseRepository;
        this.enrolmentRepository = enrolmentRepository;
        this.scanner = new Scanner(System.in);
        play();
    }


    private void meniu(){

        System.out.println("Students:");
        System.out.println("1. Add student");
        System.out.println("2. Delete student");
        System.out.println("3. Update Student");
        System.out.println("4. Show students");
        System.out.println("\n");
        System.out.println("Books:");
        System.out.println("5. Show a students books");
        System.out.println("6. Add a new book to a student");
        System.out.println("7. Delete a students book");

        System.out.println("8. Show student with most books");
        System.out.println("9. Show book that most students have");

        System.out.println("Courses:");
        System.out.println("10. Show courses");
        System.out.println("11. Add a course");
        System.out.println("12. Delete a course");




    }

    private void play(){

        boolean running = true;

        while (running) {
            meniu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    deleteStudent();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    showStudents();
                    break;
                case 5:
                    showStudentBook();
                    break;
                case 6:
                    addBook();
                    break;
                case 7:
                    deleteStudentsBook();
                    break;
                case 8:
                    studentWithMostBooks();
                    break;
                case 9:
                    bookThatMostStudentsHave();
                    break;
                case 10:
                    showCourses();
                    break;
                case 11:
                    addCourse();
                    break;
                case 12:
                    deleteCourse();
                    break;
                default:
                    System.out.println("Wrong input");
            }

        }

    }

    private void showStudents(){

        List<Student> list = studentRepository.findAll();

        list.forEach(System.out::println);

    }

    private void addStudent(){

        System.out.println("First name: ");
        String firstName = scanner.nextLine();
        System.out.println("Last name: ");
        String lastName = scanner.nextLine();
        System.out.println("Email: ");
        String email = scanner.nextLine();
        System.out.println("Age: ");
        int age = Integer.parseInt(scanner.nextLine());

        Student student = Student.builder()
                .firstName(firstName)
                .lastName(lastName)
                .age(age)
                .email(email).build();

        studentRepository.saveAndFlush(student);
    }

    private void deleteStudent(){

        System.out.println("First name: ");
        String firstName = scanner.nextLine();
        System.out.println("Last name: ");
        String lastName = scanner.nextLine();

        Optional<Student> student = studentRepository.findByFirstNameAndLastName(firstName, lastName);

        student.ifPresent(value -> studentRepository.delete(value));



    }

    private void updateStudent() {

        System.out.println("First name: ");
        String firstName = scanner.nextLine();
        System.out.println("Last name: ");
        String lastName = scanner.nextLine();

        Optional<Student> student = studentRepository.findByFirstNameAndLastName(firstName, lastName);

        if (student.isPresent()) {

            boolean running = true;

            while (running) {
                System.out.println("1. First name");
                System.out.println("2. Last name");
                System.out.println("3. Email");
                System.out.println("4. Age");
                System.out.println("5. Stop");

                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice){
                    case 1:
                        System.out.println("New first name:");
                        String firstName1 = scanner.nextLine();
                        student.get().setFirstName(firstName1);
                        break;
                    case 2:
                        System.out.println("New first name:");
                        String lastName1 = scanner.nextLine();
                        student.get().setFirstName(lastName1);
                        break;
                    case 3:
                        System.out.println("New email:");
                        String email = scanner.nextLine();
                        student.get().setFirstName(email);
                        break;
                    case 4:
                        System.out.println("New age:");
                        int age = Integer.parseInt(scanner.nextLine());
                        student.get().setAge(age);
                        break;
                    case 5:
                        running = false;
                        break;
                }
            }

        }

        studentRepository.saveAndFlush(student.get());
    }

    private void addBook(){

        System.out.println("First name: ");
        String firstName = scanner.nextLine();
        System.out.println("Last name: ");
        String lastName = scanner.nextLine();
        Optional<Student> student = studentRepository.findByFirstNameAndLastName(firstName, lastName);

        if(student.isPresent()){
            System.out.println("Add the book: ");
            System.out.println("Name: ");
            String name = scanner.nextLine();
            Book book = Book.builder().bookName(name).createdAt(LocalDate.now()).build();
            student.get().addBook(book);
            studentRepository.saveAndFlush(student.get());
        }

    }

    private void showStudentBook(){

        System.out.println("First name: ");
        String firstName = scanner.nextLine();
        System.out.println("Last name: ");
        String lastName = scanner.nextLine();

        Optional<Student> student = studentRepository.findByFirstNameAndLastName(firstName, lastName);

        student.ifPresent(student1 -> System.out.println(student1.getBooks()));

    }

    private void deleteStudentsBook(){

        System.out.println("First name: ");
        String firstName = scanner.nextLine();
        System.out.println("Last name: ");
        String lastName = scanner.nextLine();

        Optional<Student> student = studentRepository.findByFirstNameAndLastName(firstName, lastName);



        if(student.isPresent()){
            Set<Book> list = student.get().getBooks();
            System.out.println("Book name: ");
            String name = scanner.nextLine();
            Optional<Book> book = bookRepository.findByBookName(name);

            if(list.contains(book.get())){
                student.get().deleteBook(book.get());
            }
        }

        studentRepository.saveAndFlush(student.get());




    }

    private void studentWithMostBooks(){

        Optional<List<Student>> studentWithMostBooks = studentRepository.findStudentsWithMostBooks();



        System.out.println(studentWithMostBooks.get().get(0));

    }

    private void bookThatMostStudentsHave(){


        Optional<List<Object[]>> list = bookRepository.bookThatMostStudentsHave();

        list.ifPresent(objects -> System.out.println(Arrays.toString(objects.get(0))));

    }

    private void showCourses(){

        List<Course> list = courseRepository.findAll();

        list.forEach(System.out::println);

    }

    private void addCourse(){
        System.out.println("Name:");
        String name = scanner.nextLine();
        System.out.println("Department:");
        String department = scanner.nextLine();

        Course course = Course.builder().name(name).department(department).build();

        courseRepository.saveAndFlush(course);
    }

    private void deleteCourse(){

        System.out.println("Name:");
        String name = scanner.nextLine();
        System.out.println("Department:");
        String department = scanner.nextLine();

        Optional<Course> course = courseRepository.findByNameAndDepartment(name, department);

        boolean enrolled = false;

        List<Enrolment> list = enrolmentRepository.findAll();

        if(course.isPresent()) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getCourse().getId() == course.get().getId()) {

                    enrolled = true;
                }
            }

            if(!enrolled){
                courseRepository.delete(course.get());
            }else{
                System.out.println("Course has students currently enrolled");
            }
        }


    }
}
