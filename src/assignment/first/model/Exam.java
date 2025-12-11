package assignment.first.model;

public final class Exam {
    private final String title;
    private final int numberOfQuestions;

    public Exam(String title, int numberOfQuestions) {
        validateTitle(title);
        validateNumberOfQuestions(numberOfQuestions);
        
        this.title = title;
        this.numberOfQuestions = numberOfQuestions;
    }

    public String getTitle() {
        return title;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    private void validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Exam title cant be null or empty");
        }
    }

    private void validateNumberOfQuestions(int numberOfQuestions) {
        if (numberOfQuestions <= 0) {
            throw new IllegalArgumentException("Number of questions must be a positive number");
        }
    }
}

