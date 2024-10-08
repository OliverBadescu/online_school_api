package mycode.online_school_api.app.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

import static jakarta.persistence.GenerationType.SEQUENCE;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Builder
@Table(name = "book")
@Entity(name = "Book")
public class Book {

    @Id
    @SequenceGenerator(
            name="book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "book_sequence"
    )

    @Column(
            name = "id"
    )
    private int id;


    @Column(
            name = "book_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String bookName;

    @Column(
            name = "created_at",
            nullable = false,
            columnDefinition = "DATE"
    )
    private LocalDate createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id",referencedColumnName = "id")
    private Student student;


    @Override
    public boolean equals(Object o) {
        Book book = (Book) o;
        return id == book.id && bookName.equals(book.bookName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookName);
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", bookName=" + bookName + ", createdAt=" + createdAt + ", student=" + student + '}';
    }
}
