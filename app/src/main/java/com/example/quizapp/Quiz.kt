package com.example.quizapp

import android.widget.Button
import android.widget.TextView

data class Quiz(var totalQuestions: Int, val questions: List<Question>, var questionOn: Int = 0, var amountCorrect: Int = 0, var amountWrong: Int = 0) {
    public fun nextQuestion() {
        if(totalQuestions > questionOn)
            questionOn++
    }
    public fun setQuestion(b1: Button, b2: Button, b3: Button, b4: Button, t: TextView) {
        b1.text = questions[questionOn].option1
        b2.text = questions[questionOn].option2
        b3.text = questions[questionOn].option3
        b4.text = questions[questionOn].option4
        t.text = questions[questionOn].question
    }
    public fun checkIfRight(optionSelected: Int){
        if(optionSelected == questions[questionOn].answer)
            amountCorrect++
        else
            amountWrong++
        totalQuestions++
    }
}
