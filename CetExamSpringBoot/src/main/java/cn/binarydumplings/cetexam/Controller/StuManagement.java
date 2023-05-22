package cn.binarydumplings.cetexam.Controller;

import cn.binarydumplings.cetexam.Bean.ResponseData;
import cn.binarydumplings.cetexam.Bean.Student;
import cn.binarydumplings.cetexam.Service.StudentService;
import cn.binarydumplings.cetexam.Service.UserSessionUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/StuManagement")
public class StuManagement {

    @Resource
    private StudentService studentService;

    @RequestMapping("/applyExam")
    public ResponseData applyExam(HttpServletRequest request) {
        // 1 - 查找学生
        Student student = studentService.getStudentByUserName(UserSessionUtils.getUserName(request));
        if(student == null) {
            return ResponseData.applyExamStudentNotFoundFeedback;
        }
        // 2 - 将查找到的学生Bean修改为已经报名
        student.setHasApplied(Student.APPLIED);
        // 3 - 用修改过的学生Bean更新数据库
        Integer feedback = studentService.updateStudent(student);
        // 4 - 检查是否更新成功，并给出反馈
        if(feedback.equals(StudentService.UPDATE_SUCCESS)) {
            return ResponseData.applyExamSuccessFeedback;
        }
        if(feedback.equals(StudentService.UPDATE_STUDENT_NOT_FOUND)) {
            return ResponseData.applyExamStudentNotFoundFeedback;
        }
        return ResponseData.applyExamUnknownErrorFeedback;
    }

    @RequestMapping("/cancelExam")
    public ResponseData cancelExam(HttpServletRequest request) {
        // 1 - 查找学生
        Student student = studentService.getStudentByUserName(UserSessionUtils.getUserName(request));
        if(student == null) {
            return ResponseData.cancelExamStudentNotFoundFeedback;
        }
        // 2 - 将查找到的学生Bean修改为已经报名
        student.setHasApplied(Student.NOT_APPLIED);
        // 3 - 用修改过的学生Bean更新数据库
        Integer feedback = studentService.updateStudent(student);
        // 4 - 检查是否更新成功，并给出反馈
        if(feedback.equals(StudentService.UPDATE_SUCCESS)) {
            return ResponseData.cancelExamSuccessFeedback;
        }
        if(feedback.equals(StudentService.UPDATE_STUDENT_NOT_FOUND)) {
            return ResponseData.cancelExamStudentNotFoundFeedback;
        }
        return ResponseData.cancelExamUnknownErrorFeedback;
    }

    @RequestMapping("/registerStudent")
    public ResponseData registerStudent(@RequestParam("userName") String userName,
                                        @RequestParam("password") String password,
                                        @RequestParam("realName") String realName) {
        // 1 - 封装学生类bean对象
        Student student = new Student(userName, realName, password, Student.NOT_APPLIED);
        // TODO: 更改为存储密码哈希
        // 2 - 将学生对象插入数据表
        Integer feedback = studentService.addStudent(student);
        // 3 - 检查是否插入成功，并给出反馈
        if(feedback.equals(StudentService.INSERT_SUCCESS)) {
            return ResponseData.registerSuccessFeedback;
        }
        if(feedback.equals(StudentService.INSERT_DUPLICATED_KEY)) {
            return ResponseData.registerDuplicatedStudentFeedback;
        }
        return ResponseData.registerUnknownErrorFeedback;
    }

}
