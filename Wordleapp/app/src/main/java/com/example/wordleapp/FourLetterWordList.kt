package com.example.wordleapp

object FourLetterWordList {
    private val words = listOf("TREE" , "FISH" , "WIND", "MOON", "GAME")

    fun getRandomFourLetterWord(): String {
        return words.random()
    }
}
  val word = FourLetterWordList.getRandomFourLetterWord()