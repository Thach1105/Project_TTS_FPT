package TTS.example.Project.repository;

import TTS.example.Project.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

}
