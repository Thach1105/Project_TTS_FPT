package TTS.example.Project.controller;


import TTS.example.Project.model.Course;
import TTS.example.Project.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping("/listCourses")
    public String listCourses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "monhoc";
    }
    @PostMapping("/add")
    public String addCourse(@ModelAttribute Course course, Model model) {
        try {
            courseService.addAndUpdate(course);
            return "redirect:/course/listCourses";
        } catch (Exception e){
            model.addAttribute("errorMessage", "Thêm môn học mới không thành công!!!");
            return "error_page";
        }
    }
    @GetMapping("/edit-id={id}")
    public String editCourse(@PathVariable(name = "id") Integer id, Model model) {
        Optional<Course> optional = courseService.findById(id);
        optional.ifPresent(course -> model.addAttribute("course", course));
        return "chitietmonhoc";
    }

    @PostMapping("/edit-id={id}")
    public String editCourse(@PathVariable(name = "id")Integer id,@ModelAttribute Course course, Model model) {
        try {
            Optional<Course> optional = courseService.findById(id);
            Course existingCourse = optional.get();
            existingCourse.setCourseCode(course.getCourseCode());
            existingCourse.setName(course.getName());
            existingCourse.setDescription(course.getDescription());
            existingCourse.setCredit(course.getCredit());
            courseService.addAndUpdate(existingCourse);
            return "redirect:/course/listCourses";
        } catch (Exception e){
            model.addAttribute("errorMessage", "Thông tin cập nhật trùng với môn học khác khác");
            return "error_page";
        }
    }

    @GetMapping("/delete-id={id}")
    public String deleteCourse(@PathVariable(name = "id") Integer id) {
        Optional<Course> optional = courseService.findById(id);
       optional.ifPresent(course -> courseService.delete(course));
       return "redirect:/course/listCourses";
    }

}
