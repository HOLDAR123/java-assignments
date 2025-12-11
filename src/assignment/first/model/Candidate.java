package assignment.first.model;

public class Candidate {
    private final String name;
    private int score;

    public Candidate(String name, int score) {
        validateName(name);
        validateScore(score);
        
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        validateScore(score);
        this.score = score;
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Candidate name cant be null or empty");
        }
    }

    private void validateScore(int score) {
        if (score < 0) {
            throw new IllegalArgumentException("Score cant be negative");
        }
    }
}

