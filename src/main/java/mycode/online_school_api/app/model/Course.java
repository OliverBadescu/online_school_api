package mycode.online_school_api.app.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static jakarta.persistence.GenerationType.SEQUENCE;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@ToString
@Builder
@Table(name = "course")
@Entity(name = "Course")
public class Course {

    @Id
    @SequenceGenerator(
            name="course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "course_sequence"
    )

    @Column(
            name = "id"
    )
    private int id;


    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;

    @Column(
            name = "department",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String department;


    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
        @ToString.Exclude
    private Set<Enrolment> enrolments = new HashSet<>();
    public void addEnrolment(Enrolment enrolment){
        this.enrolments.add(enrolment);
        enrolment.setCourse(this);
    }

    public void deleteEnrolment(Enrolment enrolment){
        this.enrolments.remove(enrolment);
        enrolment.setCourse(null);
    }

    @Override
    public boolean equals(Object o) {
        Course course = (Course) o;
        return id == course.id && name.equals(course.name) && department.equals(course.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, department);


    }
}
