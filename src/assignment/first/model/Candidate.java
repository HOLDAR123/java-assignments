package assignment.first.model;

public class Candidate extends Entity {
    private String name;
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

    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {

        }
    }

    private void validateScore(int score) {
        if (score < 0) {
            throw new IllegalArgumentException("Score cant be negative");
        }
    }



    @Override
    public String getDisplayName() {
        return name;
    }

    @Override
    public String toString() {
        return "Candidate{id=" + id + ", name='" + name + "', score=" + score + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Candidate candidate = (Candidate) obj;
        return score == candidate.score && name.equals(candidate.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode() * 31 + score;
    }
}
