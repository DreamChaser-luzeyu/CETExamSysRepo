package cn.binarydumplings.cetexam.Service;

import cn.binarydumplings.cetexam.Bean.Answer;
import cn.binarydumplings.cetexam.Mapper.AnswerMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {

    public static final Integer INSERT_SUCCESS = 0;
    public static final Integer INSERT_DUPLICATED_KEY = 1;
    public static final Integer INSERT_UNKNOWN_ERROR = 2;

    public static final Integer UPDATE_SUCCESS = 0;
    public static final Integer UPDATE_ANSWER_NOT_FOUND = 1;
    public static final Integer UPDATE_UNKNOWN_ERROR = 2;

    public static final Integer ANSWER_SAVE_SUCCESS = 0;
    public static final Integer ANSWER_SAVE_FAIL = 1;

    public static final Integer JUDGE_SAVE_SUCCESS = 0;
    public static final Integer JUDGE_SAVE_FAIL = 1;

    @Resource
    private AnswerMapper answerMapper;

    public Answer getAnswerByStuUserName(String stuUserName) {
        return answerMapper.selectOne(new QueryWrapper<Answer>().eq("stu_user_name", stuUserName));
    }

    public Integer addAnswer(Answer answer) {
        Answer answ = answerMapper.selectOne(new QueryWrapper<Answer>().eq("stu_user_name", answer.getStuUserName()));
        if(answ != null) {
            return INSERT_DUPLICATED_KEY;
        }
        int modifiedRows = answerMapper.insert(answer);
        if (modifiedRows == 1) {
            return INSERT_SUCCESS;
        }
        return INSERT_UNKNOWN_ERROR;
    }

    public Integer answerChoiceQuestion(Answer answer) {
        Answer answ = getAnswerByStuUserName(answer.getStuUserName());
        if(answ == null) {
            Integer feedback = addAnswer(answer);
            if(INSERT_SUCCESS.equals(feedback)) {
                return ANSWER_SAVE_SUCCESS;
            }
            else {
                return ANSWER_SAVE_FAIL;
            }
        }
        else {
            int modifiedRows = answerMapper.update(null, new UpdateWrapper<Answer>()
                    .eq("stu_user_name", answer.getStuUserName())
                    .set("choice_question_answer", answer.getChoiceQuestionAnswer()));
            if(modifiedRows == 1) {
                return ANSWER_SAVE_SUCCESS;
            }
            else {
                return ANSWER_SAVE_FAIL;
            }
        }
    }

    public Integer answerSubjectiveQuestion(Answer answer) {
        Answer answ = getAnswerByStuUserName(answer.getStuUserName());
        if(answ == null) {
            Integer feedback = addAnswer(answer);
            if(INSERT_SUCCESS.equals(feedback)) {
                return ANSWER_SAVE_SUCCESS;
            }
            else {
                return ANSWER_SAVE_FAIL;
            }
        }
        else {
            int modifiedRows = answerMapper.update(null, new UpdateWrapper<Answer>()
                    .eq("stu_user_name", answer.getStuUserName())
                    .set("subjective_question_answer", answer.getSubjectiveQuestionAnswer()));
            if(modifiedRows == 1) {
                return ANSWER_SAVE_SUCCESS;
            }
            else {
                return ANSWER_SAVE_FAIL;
            }
        }
    }

    public List<Answer> getAllAnswers() {
        return answerMapper.selectList(new QueryWrapper<Answer>().select());
    }


    public Integer judgeChoiceQuestion(Answer answer) {
        Answer answ = getAnswerByStuUserName(answer.getStuUserName());
        if(answ == null) {
            Integer feedback = addAnswer(answer);
            if(INSERT_SUCCESS.equals(feedback)) {
                return ANSWER_SAVE_SUCCESS;
            }
            else {
                return ANSWER_SAVE_FAIL;
            }
        }
        else {
            int modifiedRows = answerMapper.update(null, new UpdateWrapper<Answer>()
                    .eq("stu_user_name", answer.getStuUserName())
                    .set("choice_question_grade", answer.getChoiceQuestionGrade()));
            if(modifiedRows == 1) {
                return ANSWER_SAVE_SUCCESS;
            }
            else {
                return ANSWER_SAVE_FAIL;
            }
        }
    }

    public Integer judgeSubjectiveQuestion(Answer answer) {
        Answer answ = getAnswerByStuUserName(answer.getStuUserName());
        if(answ == null) {
            Integer feedback = addAnswer(answer);
            if(INSERT_SUCCESS.equals(feedback)) {
                return ANSWER_SAVE_SUCCESS;
            }
            else {
                return ANSWER_SAVE_FAIL;
            }
        }
        else {
            int modifiedRows = answerMapper.update(null, new UpdateWrapper<Answer>()
                    .eq("stu_user_name", answer.getStuUserName())
                    .set("subjective_question_grade", answer.getSubjectiveQuestionGrade()));
            if(modifiedRows == 1) {
                return ANSWER_SAVE_SUCCESS;
            }
            else {
                return ANSWER_SAVE_FAIL;
            }
        }
    }

}
