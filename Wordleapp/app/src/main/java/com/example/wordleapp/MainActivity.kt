package com.example.wordleapp


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.view.Gravity


class MainActivity : AppCompatActivity() {

    private lateinit var targetWord: String
    private var guessCount = 0
    private val maxGuesses = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val wordInput = findViewById<EditText>(R.id.wordInput)
        val submitbtn =  findViewById<Button>(R.id.submitbtn)
        val resetbtn  =  findViewById<Button>(R.id.resetbtn)
        val wordcontainer =  findViewById<LinearLayout>(R.id.wordcontainer)

        startNewGame(wordcontainer, submitbtn, resetbtn, wordInput)

        submitbtn.setOnClickListener{
            val guess = wordInput.text.toString().uppercase()

            if (guess.length !=4){
                Toast.makeText(this,"Enter a 4 letter Word", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            guessCount++
            val guessRow = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER
                setPadding(0, 10, 0, 10)
            }

            for (i in 0 until 4) {
                val letter = guess[i]
                val letterView = TextView(this).apply {
                    text = letter.toString()
                    textSize = 30f
                    width = 80
                    height = 80
                    gravity = Gravity.CENTER
                    // Set the background to the box you created
                    setBackgroundResource(R.drawable.letter_box)
                }

                // Set margins to add space between the letters
                val layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                layoutParams.setMargins(4, 0, 4, 0)
                letterView.layoutParams = layoutParams

                guessRow.addView(letterView)
            }

            wordcontainer.addView(guessRow)


            if (guess == targetWord || guessCount >= maxGuesses){
                Toast.makeText(this, "The word was: $targetWord", Toast.LENGTH_LONG).show()
                submitbtn.isEnabled = false
                resetbtn.visibility = Button.VISIBLE
            }

            wordInput.text.clear()
        }

        resetbtn.setOnClickListener{
            startNewGame(wordcontainer, submitbtn, resetbtn, wordInput)
        }

    }
    private fun startNewGame(wordcontainer: LinearLayout, submitbtn: Button, resetbtn: Button, wordInput: EditText ){
        targetWord = FourLetterWordList.getRandomFourLetterWord()
        guessCount = 0
        wordcontainer.removeAllViews()
        submitbtn.isEnabled = true
        resetbtn.visibility = Button.GONE
        wordInput.text.clear()
    }
}