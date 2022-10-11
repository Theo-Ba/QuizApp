package com.example.quizapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Group
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = "MainActivity"
    }

    private lateinit var bo1: Button //buttonOption1
    private lateinit var bo2: Button //buttonOption2
    private lateinit var bo3: Button //buttonOption3
    private lateinit var bo4: Button //buttonOption4
    private lateinit var tvq: TextView //textViewQuestion
    private lateinit var animeQuiz: Quiz
    private lateinit var background: ConstraintLayout
    private lateinit var hintButton: Button
    private lateinit var questionGroup: Group
    private lateinit var ifCorrectGroup: Group
    private lateinit var ifCorrectText: TextView
    private val hintText = getString(R.string.button_hint)
    private val correctText = getString(R.string.correctMessage)
    private val falseText = getString(R.string.falseMessage)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wireWidgets()
        // step 1: open the raw resource as an InputStream
        // R.raw.questions --> R is the Resources class, raw is folder,
        // questions is the file
        val inputStream = resources.openRawResource(R.raw.questions)
        // step 2: use a buffered reader on the inputstream to read the
        // the text into a string. we call it jsonString because it's the entire JSON file in a string
        val jsonString = inputStream.bufferedReader().use {
            // the last line of the use function is returned
            it.readText()
        }
        Log.d(TAG, "onCreate: $jsonString")

        // use gson to convert the jsonText into a List<Question>
        // https://medium.com/@hissain.khan/parsing-with-google-gson-library-in-android-kotlin-7920e26f5520
        // check the section called "Parsing between a Collection, List, or Array"
        // log the list of questions and see if your Question objects all appear correct
        val gson = Gson()
        // gson needs to know what kind of list you are converting to
        // typetoken tells gson it will be a List<Question>

        val type = object : TypeToken<List<Question>>() { }.type
        val questions = gson.fromJson<List<Question>>(jsonString, type)
        animeQuiz = Quiz(10, questions)
        animeQuiz.setQuestion(bo1, bo2, bo3, bo4, tvq)
        Log.d(TAG, "onCreate: $questions")
        buttons()
        customize()
    }

    private fun customize() {
        background.setBackgroundColor(Color.BLACK)
        bo1.setBackgroundColor(Color.rgb(3, 89, 0))
        bo2.setBackgroundColor(Color.rgb(3, 89, 0))
        bo3.setBackgroundColor(Color.rgb(3, 89, 0))
        bo4.setBackgroundColor(Color.rgb(3, 89, 0))
        bo1.setTextColor(Color.WHITE)
        bo2.setTextColor(Color.WHITE)
        bo3.setTextColor(Color.WHITE)
        bo4.setTextColor(Color.WHITE)
        hintButton.setBackgroundColor(Color.rgb(3, 89, 0))
        hintButton.setTextColor(Color.WHITE)
        tvq.setTextColor(Color.WHITE)
        hintButton.text = hintText
    }

    private fun buttons() {
        bo1.setOnClickListener {
            animeQuiz.checkIfRight(1, questionGroup, ifCorrectGroup, ifCorrectText, correctText, falseText)
            waitBeforeNext()
            hintButton.text = hintText
        }
        bo2.setOnClickListener {
            animeQuiz.checkIfRight(2, questionGroup, ifCorrectGroup, ifCorrectText, correctText, falseText)
            waitBeforeNext()
            hintButton.text = hintText
        }
        bo3.setOnClickListener {
            animeQuiz.checkIfRight(3, questionGroup, ifCorrectGroup, ifCorrectText, correctText, falseText)
            waitBeforeNext()
            hintButton.text = hintText
        }
        bo4.setOnClickListener {
            animeQuiz.checkIfRight(4, questionGroup, ifCorrectGroup, ifCorrectText, correctText, falseText)
            waitBeforeNext()
            hintButton.text = hintText
        }
        hintButton.setOnClickListener {
            hintButton.text = animeQuiz.questions[animeQuiz.questionOn].hint
        }
    }

    private fun waitBeforeNext() {
        object : CountDownTimer(1000, 100) {

            override fun onTick(millisUntilFinished: Long) { }

            override fun onFinish() {
                animeQuiz.nextQuestion(bo1, bo2, bo3, bo4, tvq, questionGroup, ifCorrectGroup)
            }
        }.start()
    }

    private fun wireWidgets() {
        bo1 = findViewById(R.id.button_main_option1)
        bo2 = findViewById(R.id.button_main_option2)
        bo3 = findViewById(R.id.button_main_option3)
        bo4 = findViewById(R.id.button_main_option4)
        tvq = findViewById(R.id.textView_main_question)
        background = findViewById(R.id.constraintLayount_main_background)
        hintButton = findViewById(R.id.button_main_hint)
        questionGroup = findViewById(R.id.group_main_questions)
        ifCorrectGroup = findViewById(R.id.group_main_showIfCorrect)
        ifCorrectText = findViewById(R.id.textView_main_ifCorrect)
    }
}