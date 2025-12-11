import assignment.first.model.Candidate;
import assignment.first.model.Exam;
import assignment.first.model.Question;

public class Main {

    public static void main(String[] args) {
        Exam exam1 = new Exam("Midterm Quiz", 10);
        Exam exam2 = new Exam("Midterm Quiz", 10);
        System.out.println("Exam was created: " + exam1);

        Candidate candidate = new Candidate("Timur Yesmagambetov", 0);
        candidate.setScore(85);
        System.out.println("Candidate: " + candidate);

        Question question = new Question("What is Java?",
                "Java is an object-oriented programming language");
        System.out.println("Question: " + question);

        System.out.println(exam1.getNumberOfQuestions() == exam2.getNumberOfQuestions());
    }
}
