package cn.binarydumplings.cetexam.Service;

import cn.binarydumplings.cetexam.Bean.Teacher;
import cn.binarydumplings.cetexam.Mapper.TeacherMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    public static final Integer INSERT_SUCCESS = 0;
    public static final Integer INSERT_DUPLICATED_KEY = 1;
    public static final Integer INSERT_UNKNOWN_ERROR = 2;

    public static final Integer UPDATE_SUCCESS = 0;
    public static final Integer UPDATE_TEACHER_NOT_FOUND = 1;
    public static final Integer UPDATE_UNKNOWN_ERROR = 2;

    @Resource
    private TeacherMapper teacherMapper;

    public Teacher getTeacherByUserName(String userName) {
        return teacherMapper.selectOne(new QueryWrapper<Teacher>().eq("user_name", userName));
    }

    public Integer updateTeacher(Teacher teacher) {
        int modifiedRows = teacherMapper.update(teacher, new UpdateWrapper<Teacher>()
                .eq("user_name", teacher.getUserName()));
        if (modifiedRows == 0) {
            return UPDATE_TEACHER_NOT_FOUND;
        }
        if (modifiedRows == 1) {
            return UPDATE_SUCCESS;
        }
        return UPDATE_UNKNOWN_ERROR;
    }

    public Integer addTeacher(Teacher teacher) {
        Teacher tc = teacherMapper.selectOne(new QueryWrapper<Teacher>().eq("user_name", teacher.getUserName()));
        if(tc != null) {
            return INSERT_DUPLICATED_KEY;
        }
        int modifiedRows = teacherMapper.insert(teacher);
        if (modifiedRows == 1) {
            return INSERT_SUCCESS;
        }
        return INSERT_UNKNOWN_ERROR;
    }
}
