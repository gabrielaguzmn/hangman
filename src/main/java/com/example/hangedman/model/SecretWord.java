package com.example.hangedman.model;

public class SecretWord {
    private String word;
    private String [] arrayWord;

    public SecretWord(String word){
        this.word = word;
        this.arrayWord = word.split("");
    }

    public String getWord() {
        return word;
    }

    public String[] getArrayWord() {
        return arrayWord;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setArrayWord(String[] arrayWord) {
        this.arrayWord = arrayWord;
    }
}

