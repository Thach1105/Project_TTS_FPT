package TTS.example.Project.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Scope;

import java.util.Comparator;
import java.util.List;

@Entity
@Table(name = "courses")
@Data

public class Course{
    @OneToMany(mappedBy = "course", orphanRemoval = true, cascade = CascadeType.ALL)
    List<Enrollments> enrollmentsList;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;

    @Column(name = "course_code", nullable = false, unique = true)
    private String courseCode;

    @Column(name = "course_name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name ="course_credit", nullable = false)
    private int credit;

}
