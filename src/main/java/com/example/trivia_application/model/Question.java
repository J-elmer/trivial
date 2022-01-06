package com.example.trivia_application.model;

import org.apache.commons.text.StringEscapeUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Object which just contains the category of the question, the question itself and the answer possibilities without making
 * a distinction between correct and incorrect. Also, the answers are shuffled to make sure the correct answer isn't the first
 * option. Finally, the encoded html characters are decoded using the apache libraray StringEscapeUtils.
 */
public class Question {

    private final int questionId;
    private final String category;
    private final String question;
    private final List<String> answers;

    public Question(int questionId, String category, String question, String correctAnswer, String[] incorrectAnswers) {
        this.questionId = questionId;
        this.category = StringEscapeUtils.unescapeHtml4(category);
        this.question = StringEscapeUtils.unescapeHtml4(question);
        this.answers = new ArrayList<String>() {
            {
                add(StringEscapeUtils.unescapeHtml4(correctAnswer));
                for (String incorrectAnswer: incorrectAnswers)
                    add(StringEscapeUtils.unescapeHtml4(incorrectAnswer));
            }
        };
        Collections.shuffle(this.answers);
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getCategory() {
        return category;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", category='" + category + '\'' +
                ", question='" + question + '\'' +
                ", answers=" + answers +
                '}';
    }
}
