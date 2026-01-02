package assignment.first.model;

public class Exam extends Entity {
    private String title;
    private int numberOfQuestions;

    public Exam(String title, int numberOfQuestions) {
        validateTitle(title);
        validateNumberOfQuestions(numberOfQuestions);

        this.title = title;
        this.numberOfQuestions = numberOfQuestions;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        validateTitle(title);
        this.title = title;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        validateNumberOfQuestions(numberOfQuestions);
        this.numberOfQuestions = numberOfQuestions;
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

    @Override
    public String getDisplayName() {
        return title;
    }

    @Override
    public String toString() {
        return "Exam{id=" + id + ", title='" + title + "', numberOfQuestions=" + numberOfQuestions + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Exam exam = (Exam) obj;
        return numberOfQuestions == exam.numberOfQuestions && title.equals(exam.title);
    }

    @Override
    public int hashCode() {
        return title.hashCode() * 31 + numberOfQuestions;
    }
}
