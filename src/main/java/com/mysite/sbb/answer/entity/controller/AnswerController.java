package com.mysite.sbb.answer.entity.controller;

import com.mysite.sbb.answer.entity.service.AnswerService;
import com.mysite.sbb.question.entity.Question;
import com.mysite.sbb.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
    private final AnswerService answerService;
    private final QuestionService questionService;

    @PostMapping("/create/{id}")
    public String createAnswer(@PathVariable("id") Integer questionId, @RequestParam("content") String content) {
        Question question = this.questionService.getQuestion(questionId);
        this.answerService.createAnswer(question, content);
        return "redirect:/question/detail/%s".formatted(questionId);
    }

}
