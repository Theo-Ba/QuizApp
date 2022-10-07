package com.example.quizapp

import android.graphics.Color
import android.webkit.RenderProcessGoneDetail
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.core.view.isGone
import androidx.core.view.isVisible

data class Quiz(var totalQuestions: Int, val questions: List<Question>, var questionOn: Int = 0, var amountCorrect: Int = 0, var amountWrong: Int = 0) {
    public fun nextQuestion(b1: Button, b2: Button, b3: Button, b4: Button, t: TextView, qg: Group, icg: Group) {
        qg.isGone = false
        icg.isGone = true
        if(questionOn >= totalQuestions)
            finalScreen(b1, b2, b3, b4, t)
        else
            setQuestion(b1, b2, b3, b4, t)
    }
    public fun setQuestion(b1: Button, b2: Button, b3: Button, b4: Button, t: TextView) {
        b1.text = questions[questionOn].option1
        b2.text = questions[questionOn].option2
        b3.text = questions[questionOn].option3
        b4.text = questions[questionOn].option4
        t.text = questions[questionOn].question
    }
    public fun checkIfRight(optionSelected: Int, qg: Group, icg: Group, ict: TextView){
        qg.isGone = true
        icg.isGone = false
        if(optionSelected == questions[questionOn].answer) {
            amountCorrect++
            ict.setTextColor(Color.rgb(3, 89, 0))
            ict.text = "Correct!"
        }
        else {
            amountWrong++
            ict.setTextColor(Color.RED)
            ict.text = "False"
        }
        questionOn++
    }
    public fun finalScreen(b1: Button, b2: Button, b3: Button, b4: Button, t: TextView) {
        val percentCorrect = (amountCorrect*100)/totalQuestions
        t.text = "press a button to close app"
        b1.text = "Amount correct: $amountCorrect"
        b2.text = "Amount wrong: $amountWrong"
        b3.text = "Percent correct: $percentCorrect"
        b4.text = ":)"
    }
}
