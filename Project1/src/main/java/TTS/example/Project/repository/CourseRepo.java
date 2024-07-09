package TTS.example.Project.repository;

import TTS.example.Project.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.util.List;

@Repository
public interface CourseRepo extends JpaRepository<Course, Integer> {
}
