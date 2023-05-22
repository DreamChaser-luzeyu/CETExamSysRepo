package cn.binarydumplings.cetexam.Bean;

public class ResponseData {
    public static final ResponseData GenericSuccessFeedback
            = new ResponseData(20000, "success", null);

    // 登录相关
    public static final ResponseData loginSuccessFeedback = new ResponseData(20000, "success", null);
    public static final ResponseData logoutSuccessFeedback = new ResponseData(20000, "success", null);
    public static final ResponseData loginInvalidUserFeedback = new ResponseData(20001, "invalid_user", null);

    // 注册相关
    public static final ResponseData registerSuccessFeedback
            = new ResponseData(20000, "success", null);
    public static final ResponseData registerInsufficientParamFeedback
            = new ResponseData(20001, "insufficient_param", null);
    public static final ResponseData registerDuplicatedStudentFeedback
            = new ResponseData(20002, "duplicated_student", null);
    public static final ResponseData registerUnknownErrorFeedback
            = new ResponseData(20005, "unknown_error", null);

    // 报名相关
    public static final ResponseData applyExamSuccessFeedback = new ResponseData(20000, "success", null);
    public static final ResponseData applyExamStudentNotFoundFeedback
            = new ResponseData(20001, "student_not_found", null);
    public static final ResponseData applyExamUnknownErrorFeedback
            = new ResponseData(20005, "unknown_error", null);

    // 取消报名相关
    public static final ResponseData cancelExamSuccessFeedback = new ResponseData(20000, "success", null);
    public static final ResponseData cancelExamStudentNotFoundFeedback
            = new ResponseData(20001, "student_not_found", null);
    public static final ResponseData cancelExamUnknownErrorFeedback
            = new ResponseData(20005, "unknown_error", null);

    // 考试相关
    public static final ResponseData saveAnswerSuccessFeedback
            = new ResponseData(20000, "success", null);
    public static final ResponseData saveAnswerNotStudentFeedback
            = new ResponseData(20001, "user_type_invalid", null);
    public static final ResponseData saveAnswerNotAppliedFeedback
            = new ResponseData(20002, "exam_not_applied", null);
    public static final ResponseData saveAnswerUnknownErrorFeedback
            = new ResponseData(20005, "unknown_error", null);
    public static final ResponseData scoreInquirySuccessFeedback
            = new ResponseData(20000, "success", null);
    public static final ResponseData scoreInquiryNotJudgedFeedback
            = new ResponseData(20001, "not_judged", null);
    public static final ResponseData scoreInquiryNotAttendFeedback
            = new ResponseData(20002, "not_attend", null);
    public static final ResponseData scoreInquiryUnknownErrorFeedback
            = new ResponseData(20005, "unknown_error", null);


    // 阅卷相关
    public static final ResponseData scoreChoiceQuestionSuccessFeedback
            = new ResponseData(20000, "success", null);
    public static final ResponseData scoreSubjectiveQuestionSuccessFeedback
            = new ResponseData(20000, "success", null);
    public static final ResponseData scoreQuestionAnswerNotFoundFeedback
            = new ResponseData(20002, "answer_not_found", null);
    public static final ResponseData scoreSubjectiveQuestionUnknownErrorFeedback
            = new ResponseData(20005, "unknown_error", null);

    private Integer respCode;
    private String feedback;
    private String message;

    public Integer getRespCode() {
        return respCode;
    }

    public String getFeedback() {
        return feedback;
    }

    public String getMessage() {
        return message;
    }

    public void setRespCode(Integer respCode) {
        this.respCode = respCode;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseData(Integer respCode, String feedback, String message) {
        this.respCode = respCode;
        this.feedback = feedback;
        this.message = message;
    }
}
