package com.mysite.sbb.answer;

import com.mysite.sbb.DataNotFoundException;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    public void createAnswer(String content, Question question, SiteUser author) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setQuestion(question);
        answer.setAuthor(author);
        answer.setCreateDate(LocalDateTime.now());
        this.answerRepository.save(answer);
    }

    public Answer getAnswer(Integer id) {
        Optional<Answer> answerOptional = this.answerRepository.findById(id);
        if(answerOptional.isPresent()) {
            return answerOptional.get();
        } else {
            throw new DataNotFoundException("answer not found");
        }
    }

    public void modifyAnswer(Answer answer, String content) {
        answer.setContent(content);
        answer.setModifyDate(LocalDateTime.now());
        this.answerRepository.save(answer);
    }

    public void deleteAnswer(Answer answer) {
        this.answerRepository.delete(answer);
    }
}
