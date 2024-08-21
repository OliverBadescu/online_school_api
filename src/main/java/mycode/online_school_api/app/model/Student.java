package mycode.online_school_api.app.model;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.jmx.export.annotation.ManagedResource;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import static jakarta.persistence.GenerationType.SEQUENCE;

@AllArgsConstructor
@ToString
@NoArgsConstructor
@Data
@Getter
@Setter
@Builder
@Table(name = "student")
@Entity(name = "Student")
public class Student {

    @Id
    @SequenceGenerator(
            name="student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "student_sequence"
    )

    @Column(
            name = "id"
    )
    private int id;

    @Column(
            name = "first_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String lastName;


    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String email;

    @Column(
            name = "age",
            nullable = false,
            columnDefinition = "INT"
    )
    private int age;

    @OneToMany(mappedBy ="student",fetch = FetchType.LAZY,cascade = CascadeType.ALL ,orphanRemoval = true)
    @Builder.Default
        @ToString.Exclude
    private Set<Book> books= new HashSet<>();


    public void addBook(Book book){
        this.books.add(book);
        book.setStudent(this);
    }
    public void deleteBook(Book book){
        this.books.remove(book);

        book.setStudent(null);
    }
}
