package cn.binarydumplings.cetexam.Bean;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("answer")
public class Answer {

    public static String choiceQuestionCorrectAnswer = "ABC";

    private String stuUserName;
    private String choiceQuestionAnswer;
    private String subjectiveQuestionAnswer;
    private Integer choiceQuestionGrade;
    private Integer subjectiveQuestionGrade;

    public String getStuUserName() {
        return stuUserName;
    }

    public void setStuUserName(String stuUserName) {
        this.stuUserName = stuUserName;
    }

    public String getChoiceQuestionAnswer() {
        return choiceQuestionAnswer;
    }

    public void setChoiceQuestionAnswer(String choiceQuestionAnswer) {
        this.choiceQuestionAnswer = choiceQuestionAnswer;
    }

    public String getSubjectiveQuestionAnswer() {
        return subjectiveQuestionAnswer;
    }

    public void setSubjectiveQuestionAnswer(String subjectiveQuestionAnswer) {
        this.subjectiveQuestionAnswer = subjectiveQuestionAnswer;
    }

    public Integer getChoiceQuestionGrade() {
        return choiceQuestionGrade;
    }

    public void setChoiceQuestionGrade(Integer choiceQuestionGrade) {
        this.choiceQuestionGrade = choiceQuestionGrade;
    }

    public Integer getSubjectiveQuestionGrade() {
        return subjectiveQuestionGrade;
    }

    public void setSubjectiveQuestionGrade(Integer subjectiveQuestionGrade) {
        this.subjectiveQuestionGrade = subjectiveQuestionGrade;
    }
}
