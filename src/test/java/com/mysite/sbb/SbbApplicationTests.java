package com.mysite.sbb;

import com.mysite.sbb.Question.Question;
import com.mysite.sbb.Question.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SbbApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;

	@Test
	void testJpa1() {
		Question question1 = new Question();
		question1.setSubject("sbb가 무엇인가요?");
		question1.setContent("sbb에 대해 알고 싶습니다.");
		question1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(question1);

		Question question2 = new Question();
		question2.setSubject("스프링부트 모델 질문입니다.");
		question2.setContent("id는 자동으로 생성되나요?");
		question2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(question2);
	}

	@Test
	void testJpa2() {
		List<Question> all = this.questionRepository.findAll();
		assertEquals(2, all.size());

		Question question = all.get(0);
		assertEquals("sbb가 무엇인가요?", question.getSubject());
	}

}
