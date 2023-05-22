package cn.binarydumplings.cetexam.Service;

import cn.binarydumplings.cetexam.Bean.Student;
import cn.binarydumplings.cetexam.Mapper.StudentMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    public static final Integer INSERT_SUCCESS = 0;
    public static final Integer INSERT_DUPLICATED_KEY = 1;
    public static final Integer INSERT_UNKNOWN_ERROR = 2;

    public static final Integer UPDATE_SUCCESS = 0;
    public static final Integer UPDATE_STUDENT_NOT_FOUND = 1;
    public static final Integer UPDATE_UNKNOWN_ERROR = 2;

    @Resource
    private StudentMapper studentMapper;

    public Student getStudentByUserName(String userName) {
        return studentMapper.selectOne(new QueryWrapper<Student>().eq("user_name", userName));
    }

    public Integer updateStudent(Student student) {
        int modifiedRows = studentMapper.update(student, new UpdateWrapper<Student>()
                .eq("user_name", student.getUserName()));
        if (modifiedRows == 0) {
            return UPDATE_STUDENT_NOT_FOUND;
        }
        if (modifiedRows == 1) {
            return UPDATE_SUCCESS;
        }
        return UPDATE_UNKNOWN_ERROR;
    }

    public Integer addStudent(Student student) {
        Student stu = studentMapper.selectOne(new QueryWrapper<Student>().eq("user_name", student.getUserName()));
        if(stu != null) {
            return INSERT_DUPLICATED_KEY;
        }
        int modifiedRows = studentMapper.insert(student);
        if (modifiedRows == 1) {
            return INSERT_SUCCESS;
        }
        return INSERT_UNKNOWN_ERROR;
    }
}
