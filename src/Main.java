import assignment.first.model.Candidate;
import assignment.first.model.DataPool;
import assignment.first.model.Entity;
import assignment.first.model.Exam;
import assignment.first.model.Question;

import java.util.*;

public class Main {

        public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);

                DataPool<Candidate> candidatePool = new DataPool<>();
                DataPool<Exam> examPool = new DataPool<>();
                DataPool<Question> questionPool = new DataPool<>();

                System.out.println("Enter number of candidates:");
                int candidateCount = scanner.nextInt();
                scanner.nextLine();

                for (int i = 0; i < candidateCount; i++) {
                        System.out.println("Enter candidate name " + (i + 1) + ":");
                        String name = scanner.nextLine();
                        System.out.println("Enter candidate score " + (i + 1) + ":");
                        int score = scanner.nextInt();
                        scanner.nextLine();
                        candidatePool.add(new Candidate(name, score));
                }

                System.out.println("Enter number of exams:");
                int examCount = scanner.nextInt();
                scanner.nextLine();

                for (int i = 0; i < examCount; i++) {
                        System.out.println("Enter exam title " + (i + 1) + ":");
                        String title = scanner.nextLine();
                        System.out.println("Enter number of questions:");
                        int questions = scanner.nextInt();
                        scanner.nextLine();
                        examPool.add(new Exam(title, questions));
                }

                System.out.println("Enter number of questions:");
                int questionCount = scanner.nextInt();
                scanner.nextLine();

                for (int i = 0; i < questionCount; i++) {
                        System.out.println("Enter question text " + (i + 1) + ":");
                        String questionText = scanner.nextLine();
                        System.out.println("Enter answer:");
                        String answer = scanner.nextLine();
                        questionPool.add(new Question(questionText, answer));
                }

                System.out.println("=== All Candidates ===");
                candidatePool.getAll().forEach(System.out::println);

                System.out.println("=== Filtered Candidates (score >= 70) ===");
                candidatePool.filter(c -> c.getScore() >= 70).forEach(System.out::println);

                System.out.println("=== Search Candidate by Name ===");
                System.out.println("Enter name to search:");
                String searchName = scanner.nextLine();
                candidatePool.search(c -> c.getName().equals(searchName))
                                .ifPresentOrElse(
                                                System.out::println,
                                                () -> System.out.println("Candidate not found"));

                System.out.println("=== Sorted Candidates by Score (descending) ===");
                candidatePool.getSorted((c1, c2) -> Integer.compare(c2.getScore(), c1.getScore()))
                                .forEach(System.out::println);

                System.out.println("=== All Exams ===");
                examPool.getAll().forEach(System.out::println);

                System.out.println("=== Filtered Exams (questions >= 10) ===");
                examPool.filter(e -> e.getNumberOfQuestions() >= 10).forEach(System.out::println);

                System.out.println("=== Sorted Exams by Title ===");
                examPool.getSorted(Comparator.comparing(Exam::getTitle)).forEach(System.out::println);

                System.out.println("=== All Questions ===");
                questionPool.getAll().forEach(System.out::println);

                System.out.println("=== Search Questions by Keyword ===");
                System.out.println("Enter keyword:");
                String keyword = scanner.nextLine();
                questionPool.searchAll(q -> q.getQuestionText().toLowerCase().contains(keyword.toLowerCase()))
                                .forEach(System.out::println);

                Candidate c1 = new Candidate("Test", 100);
                Candidate c2 = new Candidate("Test", 100);
                System.out.println("c1.equals(c2): " + c1.equals(c2));
                System.out.println("c1.hashCode() == c2.hashCode(): " + (c1.hashCode() == c2.hashCode()));

                Exam e1 = new Exam("Test Exam", 20);
                Exam e2 = new Exam("Test Exam", 20);
                System.out.println("e1.equals(e2): " + e1.equals(e2));
                System.out.println("e1.hashCode() == e2.hashCode(): " + (e1.hashCode() == e2.hashCode()));

                List<Entity> entities = new ArrayList<>();
                entities.add(new Candidate("Polymorph Candidate", 90));
                entities.add(new Exam("Polymorph Exam", 15));
                entities.add(new Question("Polymorph Question?", "Polymorph Answer"));

                entities.forEach(entity -> System.out
                                .println(entity.getDisplayName() + " (ID: " + entity.getId() + ")"));

                scanner.close();
        }
}
