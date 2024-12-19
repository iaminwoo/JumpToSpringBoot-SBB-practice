package com.mysite.sbb.global;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.user.SiteUser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

public class HttpMethodUtil {

    // 삭제, 수정 권한 체크 메서드
    public static void checkPermission(Object object, Principal principal, httpMethodType httpMethodType) {
        SiteUser author;

        if(object.getClass() == Question.class) {
            Question questionObject = (Question) object;
            author = questionObject.getAuthor();
        } else if(object.getClass() == Answer.class){
            Answer answerObject = (Answer) object;
            author = answerObject.getAuthor();
        } else {
            throw new IllegalArgumentException("지원하지 않는 객체 타입입니다.");
        }

        if(!author.getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "%s 권한이 없습니다.".formatted(httpMethodType.getMsg()));
        }
    }

    // 삭제인지 수정인지 enum으로 처리
    @Getter
    @RequiredArgsConstructor
    public enum httpMethodType {
        delete("삭제"), modify("수정");

        private final String msg;
    }
}
