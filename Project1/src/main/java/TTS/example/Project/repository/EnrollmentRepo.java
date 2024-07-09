package TTS.example.Project.repository;

import TTS.example.Project.model.Course;
import TTS.example.Project.model.Enrollments;
import TTS.example.Project.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepo extends JpaRepository<Enrollments, Long> {
    boolean existsByStudentAndCourse(Student student, Course course);


}
