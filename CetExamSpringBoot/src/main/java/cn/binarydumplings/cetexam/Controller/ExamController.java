package cn.binarydumplings.cetexam.Controller;

import cn.binarydumplings.cetexam.Bean.Answer;
import cn.binarydumplings.cetexam.Bean.ResponseData;
import cn.binarydumplings.cetexam.Bean.Student;
import cn.binarydumplings.cetexam.Service.AnswerService;
import cn.binarydumplings.cetexam.Service.StudentService;
import cn.binarydumplings.cetexam.Service.UserSessionUtils;
import com.alibaba.fastjson.JSONObject;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ExamController")
public class ExamController {

    @Resource
    private AnswerService answerService;
    @Resource
    private StudentService studentService;
    @RequestMapping("/answerChoiceQuestion")
    public ResponseData answerChoiceQuestion(@RequestParam("choiceQuestionAnswer") String choiceQuestionAnswer,
                                             HttpServletRequest request) {
        if(!UserSessionUtils.isStudentLoggedIn(request)) {
            return ResponseData.saveAnswerNotStudentFeedback;
        }

        Student student = studentService.getStudentByUserName(UserSessionUtils.getUserName(request));
        if(Student.APPLIED.equals(student.getHasApplied())) {
            String stuUserName = UserSessionUtils.getUserName(request);
            Answer answer = new Answer();
            answer.setStuUserName(stuUserName);
            answer.setChoiceQuestionAnswer(choiceQuestionAnswer);
            Integer feedback = answerService.answerChoiceQuestion(answer);
            if(AnswerService.ANSWER_SAVE_SUCCESS.equals(feedback)) {
                return ResponseData.saveAnswerSuccessFeedback;
            }
            else {
                return ResponseData.saveAnswerUnknownErrorFeedback;
            }
        }
        else {
            return ResponseData.saveAnswerNotAppliedFeedback;
        }
    }

    @RequestMapping("/answerSubjectiveQuestion")
    public ResponseData answerSubjectiveQuestion(@RequestParam("subjectiveQuestionAnswer") String subjectiveQuestionAnswer,
                                             HttpServletRequest request) {
        if(!UserSessionUtils.isStudentLoggedIn(request)) {
            return ResponseData.saveAnswerNotStudentFeedback;
        }

        Student student = studentService.getStudentByUserName(UserSessionUtils.getUserName(request));
        if(Student.APPLIED.equals(student.getHasApplied())) {
            String stuUserName = UserSessionUtils.getUserName(request);
            Answer answer = new Answer();
            answer.setStuUserName(stuUserName);
            answer.setSubjectiveQuestionAnswer(subjectiveQuestionAnswer);
            Integer feedback = answerService.answerSubjectiveQuestion(answer);
            if(AnswerService.ANSWER_SAVE_SUCCESS.equals(feedback)) {
                return ResponseData.saveAnswerSuccessFeedback;
            }
            else {
                return ResponseData.saveAnswerUnknownErrorFeedback;
            }
        }
        else {
            return ResponseData.saveAnswerNotAppliedFeedback;
        }
    }

    @RequestMapping("/scoreInquiry")
    public ResponseData choiceQuestionScoreInquiry(HttpServletRequest request){
        return ResponseData.scoreInquiryUnknownErrorFeedback;
    }

    @RequestMapping("/judgeChoiceQuestion")
    public ResponseData judgeChoiceQuestion(@RequestParam("stuUserName") String stuUserName,
                                            HttpServletRequest request){
        // 查询Answer对象
        Answer answer = answerService.getAnswerByStuUserName(stuUserName);
        // 检查answer是否有效
        if(answer == null) {
            return ResponseData.scoreQuestionAnswerNotFoundFeedback;
        }
        // 填入分数
        answer.setChoiceQuestionGrade(AnswerService.calculateChoiceQuestionGrade(answer.getChoiceQuestionAnswer()));
        // 更新选择题分数
        Integer feedback = answerService.judgeChoiceQuestion(answer);

        if(AnswerService.JUDGE_SAVE_SUCCESS.equals(feedback)) {
            ResponseData responseData = ResponseData.scoreChoiceQuestionSuccessFeedback;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("choice_question_grade", answer.getChoiceQuestionGrade());
            responseData.setMessage(jsonObject.toJSONString());
            return responseData;
        }
        return ResponseData.scoreSubjectiveQuestionUnknownErrorFeedback;
    }

    @RequestMapping("/judgeSubjectiveQuestion")
    public ResponseData judgeSubjectiveQuestion(@RequestParam("stuUserName") String stuUserName,
                                                @RequestParam("subjectiveQuestionGrade") String subjectiveQuestionGradeStr,
                                                HttpServletRequest request){
        // 解析分数字符串
        Integer subjectiveQuestionGrade = Integer.parseInt(subjectiveQuestionGradeStr);
        // 查询Answer对象
        Answer answer = answerService.getAnswerByStuUserName(stuUserName);
        // 检查answer是否有效
        if(answer == null) {
            return ResponseData.scoreQuestionAnswerNotFoundFeedback;
        }
        // 填入分数
        answer.setSubjectiveQuestionGrade(subjectiveQuestionGrade);
        // 更新主观题分数
        Integer feedback = answerService.judgeSubjectiveQuestion(answer);
        if(AnswerService.JUDGE_SAVE_SUCCESS.equals(feedback)) {
            return ResponseData.scoreSubjectiveQuestionSuccessFeedback;
        }

        return ResponseData.scoreSubjectiveQuestionUnknownErrorFeedback;
    }

    @RequestMapping("/getAllAnswers")
    public ResponseData getAllAnswers() {
        ResponseData responseData = ResponseData.GenericSuccessFeedback;
        List<Answer> answers = answerService.getAllAnswers();
        responseData.setMessage(JSONObject.toJSONString(answers));
        return responseData;
    }

    @RequestMapping("/getAnswerByStuUserName")
    public ResponseData getAnswerByStuUserName(@RequestParam("stuUserName") String stuUserName) {
        ResponseData responseData = ResponseData.GenericSuccessFeedback;
        Answer answer = answerService.getAnswerByStuUserName(stuUserName);
        responseData.setMessage(JSONObject.toJSONString(answer));
        return responseData;
    }

//    public static Integer gradeChoiceQuestionAnswer(String correctAnswer, String stuAnswer) {
//        return 100;
//    }

}
