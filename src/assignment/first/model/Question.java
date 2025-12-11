package assignment.first.model;

public class Question {
    private String questionText;
    private String answer;

    public Question(String questionText, String answer) {
        validateQuestionText(questionText);
        validateAnswer(answer);
        
        this.questionText = questionText;
        this.answer = answer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getAnswer() {
        return answer;
    }

    public void setQuestionText(String questionText) {
        validateQuestionText(questionText);
        this.questionText = questionText;
    }

    public void setAnswer(String answer) {
        validateAnswer(answer);
        this.answer = answer;
    }

    private void validateQuestionText(String questionText) {
        if (questionText == null || questionText.trim().isEmpty()) {
            throw new IllegalArgumentException("Question text cant be null or empty");
        }
    }

    private void validateAnswer(String answer) {
        if (answer == null || answer.trim().isEmpty()) {
            throw new IllegalArgumentException("Answer cant be null or empty");
        }
    }
}

