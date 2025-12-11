import assignment.first.model.Candidate;
import assignment.first.model.Exam;
import assignment.first.model.Question;

public class Main {

    public static void main(String[] args) {
        Exam exam1 = new Exam("Midterm Quiz", 10);
        Exam exam2 = new Exam("Midterm Quiz", 10);
        System.out.println("Exam-1 was created: " + exam1);
        System.out.println("Exam-2 was created: " + exam2);

        Candidate candidate1 = new Candidate("Timur Yesmagambetov", 0);
        Candidate candidate2 = new Candidate("Timur Yesmagambetov", 0);
        candidate1.setScore(85);
        System.out.println("Candidate-1: " + candidate1);
        System.out.println("Candidate-2: " + candidate2);

        Question question1 = new Question("What is Java?",
                "Java is an object-oriented programming language");
        Question question2 = new Question("What is C++?",
                "C++ is a programming language");
        System.out.println("Question-1: " + question1);
        System.out.println("Question-2: " + question2);

        System.out.println(exam1.getNumberOfQuestions() == exam2.getNumberOfQuestions() ? "Numbers of questions are equal" : "No Numbers of questions arent equal");
        System.out.println(exam1.getTitle().equals(exam2.getTitle()) ? "Exams titles are equal" : "No Exams titles arent equal");
        System.out.println(candidate1.getName().equals(candidate2.getName()) ? "Candidates are equal" : "No Candidates arent equal");
        System.out.println(question1.getQuestionText().equals(question2.getQuestionText()) ? "Questions are equal" : "No Questions arent equal");
    }
}
