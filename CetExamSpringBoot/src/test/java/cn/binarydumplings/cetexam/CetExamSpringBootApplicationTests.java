package cn.binarydumplings.cetexam;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class CetExamSpringBootApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void jsonObjectTest() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("choice_question_grade", 20);
		System.out.println(jsonObject.toJSONString());
	}


	public static Integer calculateChoiceQuestionGrade(String choiceQuestionAnswer) {
		JSONObject standardAnswerJSONObj = new JSONObject();
		standardAnswerJSONObj.put("1", "A");
		standardAnswerJSONObj.put("2", "B");
		standardAnswerJSONObj.put("3", "C");
		standardAnswerJSONObj.put("4", "D");


		JSONObject stuAnswerJSONObject = JSONObject.parseObject(choiceQuestionAnswer);


		Integer choiceQuestionGrade = 0;

		for(Map.Entry<String, Object> e : standardAnswerJSONObj.entrySet()) {
			String questionNum = e.getKey();
			String questionStandardAnswer = (String) e.getValue();
			String questionStuAnswer = (String) stuAnswerJSONObject.get(questionNum);

			if(questionStandardAnswer.equals(questionStuAnswer)) {
				choiceQuestionGrade ++;
			}
		}

		return choiceQuestionGrade;
	}

	@Test
	void calScoreTest() {


		JSONObject stuAnswerJSONObject = new JSONObject();
		stuAnswerJSONObject.put("1", "A");
		stuAnswerJSONObject.put("2", "B");
		stuAnswerJSONObject.put("3", "C");
		stuAnswerJSONObject.put("4", "D");

		System.out.println(stuAnswerJSONObject.toJSONString());

		System.out.println(calculateChoiceQuestionGrade(stuAnswerJSONObject.toJSONString()));

	}

}
