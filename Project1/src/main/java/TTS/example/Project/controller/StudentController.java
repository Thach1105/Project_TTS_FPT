package TTS.example.Project.controller;

import TTS.example.Project.model.Course;
import TTS.example.Project.model.Enrollments;
import TTS.example.Project.model.Student;
import TTS.example.Project.service.CourseService;
import TTS.example.Project.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
//@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @Autowired
    CourseService courseService;

    @GetMapping("/listStudent")
    public String listStudent(Model model){
        model.addAttribute("students", studentService.getAllStudent());
        return "sinhvien";
    }

    @PostMapping("/add")
    public String addStudent(@ModelAttribute Student student, Model model){
        try {
            studentService.addAndUpdate(student);
            return "redirect:listStudent";
        } catch (Exception e){
            model.addAttribute("errorMessage", "Thông tin bị trùng với sinh khác, Không thể thêm mới thànhg công!!!");
            return "error_page";
        }
    }

    @GetMapping("/delete-id={id}")
    public String deleteStudent(@PathVariable(name = "id")Integer id){
        Optional<Student> optional = studentService.getStudentById(id);
        optional.ifPresent(value -> studentService.delete(value));
        return "redirect:listStudent";
    }
    @GetMapping("/edit-id={id}")
    public String editInfoStudent(@PathVariable(name ="id")Integer id, Model model){
        Optional<Student> optional = studentService.getStudentById(id);
        optional.ifPresent(value -> model.addAttribute("student", value));

        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("allCourses", courses);
        return "chitietsinhvien";
    }
    @PostMapping("/edit-id={id}")
    public String updateInfoStudent(@PathVariable(name = "id")Integer id, @ModelAttribute Student updateStudent, Model model){
        try {
            Optional<Student> optional = studentService.getStudentById(id);

            Student existingStudent = optional.get();
            existingStudent.setStudentCode(updateStudent.getStudentCode());
            existingStudent.setName(updateStudent.getName());
            existingStudent.setDob(updateStudent.getDob());
            existingStudent.setAddress(updateStudent.getAddress());
            existingStudent.setEmail(updateStudent.getEmail());

            studentService.addAndUpdate(existingStudent);
            return "redirect:listStudent";
        } catch (Exception e){
            model.addAttribute("errorMessage", "Thông tin cập nhật trùng với sinh viên khác");
            return "error_page";
        }
    }


}

@Controller
class DefaulController{
    @GetMapping("/")
    public String index(){
        return "index";
    }
}

