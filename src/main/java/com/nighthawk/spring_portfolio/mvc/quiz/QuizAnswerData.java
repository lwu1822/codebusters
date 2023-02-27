package com.nighthawk.spring_portfolio.mvc.quiz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizAnswerData {

    String quizNumber;
    String questionNumber;
    String answer;
    String email;



}
