package TTS.example.Project.model;


import jakarta.annotation.PreDestroy;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Entity
@Table(name="students")
@Data
public class Student {
    @OneToMany(mappedBy = "student", orphanRemoval = true, cascade = CascadeType.ALL)
    List<Enrollments> enrollmentsList;

    /*@OneToOne(mappedBy = "student")
    private Account account;*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;

    @Column(name = "student_code", nullable = false, unique = true)
    private String studentCode;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="dob", nullable = false)
    private LocalDate dob;

    @Column(name="address", nullable = false)
    private String address;

    @Column(name="email", nullable = false, unique = true)
    private String email;

}
