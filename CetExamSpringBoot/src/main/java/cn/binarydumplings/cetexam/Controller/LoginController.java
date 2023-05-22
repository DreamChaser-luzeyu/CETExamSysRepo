package cn.binarydumplings.cetexam.Controller;

import cn.binarydumplings.cetexam.Bean.ResponseData;
import cn.binarydumplings.cetexam.Bean.Student;
import cn.binarydumplings.cetexam.Bean.Teacher;
import cn.binarydumplings.cetexam.Service.StudentService;
import cn.binarydumplings.cetexam.Service.TeacherService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/LoginController")
public class LoginController {

    public static final String USER_TYPE_STUDENT = "student";
    public static final String USER_TYPE_TEACHER = "teacher";

    @Resource
    private StudentService studentService;
    @Resource
    private TeacherService teacherService;

    private Boolean authenticateStudent(String userName, String password) {
        Student student = studentService.getStudentByUserName(userName);
        if(student == null) { return false; }
        // TODO: 改为比较密码哈希值
        return student.getPassword().equals(password);
    }

    private Boolean authenticateTeacher(String userName, String password) {
        Teacher teacher = teacherService.getTeacherByUserName(userName);
        if(teacher == null) { return false; }
        // TODO: 改为比较密码哈希值
        return teacher.getPassword().equals(password);
    }

    @RequestMapping("/studentLogin")
    public ResponseData studentLogin(@RequestParam("userName") String userName,
                              @RequestParam("password") String password,
                              HttpServletRequest request) {

        if (authenticateStudent(userName, password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("userName", userName);
            session.setAttribute("userType", USER_TYPE_STUDENT);
            System.out.println("[ DEV ] Student login success, generated session id:" + session.getId());
            return ResponseData.loginSuccessFeedback;
        }

        System.out.println("[ DEV ] 4 - Student login failed");
        return ResponseData.loginInvalidUserFeedback;
    }

    @RequestMapping("/teacherLogin")
    public ResponseData teacherLogin(@RequestParam("userName") String userName,
                                     @RequestParam("password") String password,
                                     HttpServletRequest request) {

        if (authenticateTeacher(userName, password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("userName", userName);
            session.setAttribute("userType", USER_TYPE_TEACHER);
            Teacher teacher = teacherService.getTeacherByUserName(userName);
            session.setAttribute("isPrivileged", teacher.getHasPrivilege());


            System.out.println("[ DEV ] Teacher login success, generated session id:" + session.getId());
            return ResponseData.loginSuccessFeedback;
        }

        System.out.println("[ DEV ] 4 - Teacher login failed");
        return ResponseData.loginInvalidUserFeedback;
    }

    @RequestMapping("/logout")
    public ResponseData logout(HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        session.removeAttribute("userName");
        session.removeAttribute("userType");
        return ResponseData.logoutSuccessFeedback;
    }
}
