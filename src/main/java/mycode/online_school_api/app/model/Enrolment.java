package mycode.online_school_api.app.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

import static jakarta.persistence.GenerationType.SEQUENCE;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Builder
@Table(name = "enrolment")
@Entity(name = "Enrolment")
public class Enrolment {

    @SequenceGenerator(
            name = "enrolment_sequence",
            sequenceName = "enrolment_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "enrolmenet_sequence"
    )
    @Column(
            name = "id"
    )
    @Id
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id",referencedColumnName = "id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="course_id",referencedColumnName = "id")
    private Course course;

    @Column(
            name = "created_at",
            nullable = false,
            columnDefinition = "DATE"
    )
    private LocalDate createdAt;

    @Override
    public boolean equals(Object o) {
        Enrolment enrolment = (Enrolment) o;
        return id == enrolment.id && course.getId() == enrolment.course.getId() && student.getId() == enrolment.student.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, student.getId(), course.getId());
    }

    @Override
    public String toString(){
        return "Enrolment{"+ "id=" + id + ", studentId=" + (student != null ? student.getId() : null) + ", courseId=" + (course!= null ? course.getId() : null) + ", createdAt=" + createdAt +'}';

    }
}
