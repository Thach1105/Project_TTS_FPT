package TTS.example.Project.controller;

import TTS.example.Project.model.Course;
import TTS.example.Project.model.Enrollments;
import TTS.example.Project.model.Student;
import TTS.example.Project.service.CourseService;
import TTS.example.Project.service.EnrollmentService;
import TTS.example.Project.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class RegisterController {
    @Autowired
    EnrollmentService enrollmentService;
    @Autowired
    StudentService studentService;
    @Autowired
    CourseService courseService;

    @PostMapping("/register/add-course")
    public String addCourseForStudent(@RequestParam("courseId")String courseId, @RequestParam("studentId")String studentId, Model model){
        try {
            Enrollments enrollment = new Enrollments();
            Optional<Student> optional1 = studentService.getStudentById(Integer.parseInt(studentId));
            optional1.ifPresent(value -> enrollment.setStudent(value));

            Optional<Course> op2 = courseService.findById(Integer.parseInt(courseId));
            op2.ifPresent(value -> enrollment.setCourse(value));
            enrollment.setEnrollment_date(LocalDate.now());

            enrollmentService.addEnrollment(enrollment);
            return "redirect:/student/edit-id=" + optional1.get().getStudentId();
        } catch (Exception e){
            model.addAttribute("errorMessage", "Học sinh đã đăng ký môn học này");
            return "error_page";
        }
    }

    @GetMapping("/register/remove-enrollmentId={id}")
    public String deleteEnrollment(@PathVariable(name = "id")Long id){
        Optional<Enrollments> optional1 = enrollmentService.findEnrollments(id);
        Integer studentId = optional1.get().getStudent().getStudentId();
        enrollmentService.deleteEnrollment(id);
        return "redirect:/student/edit-id=" + studentId;
    }

    @GetMapping("/statistics")
    public String thongKeDangKyMon(Model model){
        List<Course> courseList = courseService.getAllCourses();
        courseList.sort((o1, o2) -> o2.getEnrollmentsList().size()-o1.getEnrollmentsList().size());
        model.addAttribute("courses", courseList);
        return "thongke";
    }
}
