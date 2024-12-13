package com.mysite.sbb.Question;

import com.mysite.sbb.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> getList() {
        return this.questionRepository.findAll();
    }

    public Question getQuestion(Integer id) {
        Optional<Question> questionOptional = this.questionRepository.findById(id);
        if(questionOptional.isPresent()) {
            return questionOptional.get();
        } else {
            throw new DataNotFoundException("question not found");
        }

    }
}
