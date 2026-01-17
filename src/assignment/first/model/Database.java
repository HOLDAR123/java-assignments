package assignment.first.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static Database database;
    private Connection connection;

    private static final String URL = "jdbc:postgresql://localhost:5432/assignment";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    public static Database getInstance() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }

    private Database() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to database");
            createTables();
        } catch (SQLException e) {
            System.err.println("Error connecting to database:");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    private void createTables() {
        try {
            String createCandidatesTable = "CREATE TABLE IF NOT EXISTS candidates (" +
                    "id SERIAL PRIMARY KEY, " +
                    "name VARCHAR(255) NOT NULL, " +
                    "score INTEGER NOT NULL)";
            
            String createExamsTable = "CREATE TABLE IF NOT EXISTS exams (" +
                    "id SERIAL PRIMARY KEY, " +
                    "title VARCHAR(255) NOT NULL, " +
                    "number_of_questions INTEGER NOT NULL)";
            
            String createQuestionsTable = "CREATE TABLE IF NOT EXISTS questions (" +
                    "id SERIAL PRIMARY KEY, " +
                    "question_text TEXT NOT NULL, " +
                    "answer TEXT NOT NULL)";

            try (Statement stmt = connection.createStatement()) {
                stmt.execute(createCandidatesTable);
                stmt.execute(createExamsTable);
                stmt.execute(createQuestionsTable);
            }
        } catch (SQLException e) {
            System.err.println("Error creating tables:");
            e.printStackTrace();
        }
    }

    public void saveCandidate(Candidate candidate) {
        String sql = "INSERT INTO candidates (name, score) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, candidate.getName());
            pstmt.setInt(2, candidate.getScore());
            pstmt.executeUpdate();
            
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    candidate.id = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error saving candidate:");
            e.printStackTrace();
        }
    }

    public void saveExam(Exam exam) {
        String sql = "INSERT INTO exams (title, number_of_questions) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, exam.getTitle());
            pstmt.setInt(2, exam.getNumberOfQuestions());
            pstmt.executeUpdate();
            
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    exam.id = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error saving exam:");
            e.printStackTrace();
        }
    }

    public void saveQuestion(Question question) {
        String sql = "INSERT INTO questions (question_text, answer) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, question.getQuestionText());
            pstmt.setString(2, question.getAnswer());
            pstmt.executeUpdate();
            
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    question.id = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error saving question:");
            e.printStackTrace();
        }
    }

    public List<Candidate> loadAllCandidates() {
        List<Candidate> candidates = new ArrayList<>();
        String sql = "SELECT * FROM candidates";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Candidate candidate = new Candidate(rs.getString("name"), rs.getInt("score"));
                candidate.id = rs.getInt("id");
                candidates.add(candidate);
            }
        } catch (SQLException e) {
            System.err.println("Error loading candidates:");
            e.printStackTrace();
        }
        return candidates;
    }

    public List<Exam> loadAllExams() {
        List<Exam> exams = new ArrayList<>();
        String sql = "SELECT * FROM exams";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Exam exam = new Exam(rs.getString("title"), rs.getInt("number_of_questions"));
                exam.id = rs.getInt("id");
                exams.add(exam);
            }
        } catch (SQLException e) {
            System.err.println("Error loading exams:");
            e.printStackTrace();
        }
        return exams;
    }

    public List<Question> loadAllQuestions() {
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM questions";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Question question = new Question(rs.getString("question_text"), rs.getString("answer"));
                question.id = rs.getInt("id");
                questions.add(question);
            }
        } catch (SQLException e) {
            System.err.println("Error loading questions:");
            e.printStackTrace();
        }
        return questions;
    }

    public void updateCandidate(Candidate candidate) {
        String sql = "UPDATE candidates SET name = ?, score = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, candidate.getName());
            pstmt.setInt(2, candidate.getScore());
            pstmt.setInt(3, candidate.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating candidate:");
            e.printStackTrace();
        }
    }

    public void updateExam(Exam exam) {
        String sql = "UPDATE exams SET title = ?, number_of_questions = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, exam.getTitle());
            pstmt.setInt(2, exam.getNumberOfQuestions());
            pstmt.setInt(3, exam.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating exam:");
            e.printStackTrace();
        }
    }

    public void updateQuestion(Question question) {
        String sql = "UPDATE questions SET question_text = ?, answer = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, question.getQuestionText());
            pstmt.setString(2, question.getAnswer());
            pstmt.setInt(3, question.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating question:");
            e.printStackTrace();
        }
    }

    public void deleteCandidate(int id) {
        String sql = "DELETE FROM candidates WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting candidate:");
            e.printStackTrace();
        }
    }

    public void deleteExam(int id) {
        String sql = "DELETE FROM exams WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting exam:");
            e.printStackTrace();
        }
    }

    public void deleteQuestion(int id) {
        String sql = "DELETE FROM questions WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting question:");
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed");
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection:");
            e.printStackTrace();
        }
    }
}
