package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TernaryLoopQuizGUI extends JFrame {
    private final JTextArea questionArea;
    private final JTextField answerField;
    private final JLabel feedbackLabel;
    private final List<Question> questionPool;
    private Question currentQuestion;

    public TernaryLoopQuizGUI() {
        setTitle("Вгадай результат");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 300);
        setLayout(new BorderLayout());

        questionArea = new JTextArea();
        questionArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        questionArea.setEditable(false);
        add(new JScrollPane(questionArea), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        answerField = new JTextField(10);
        JButton checkButton = new JButton("Перевірити");
        JButton nextButton = new JButton("Наступне питання");

        inputPanel.add(new JLabel("Відповідь:"));
        inputPanel.add(answerField);
        inputPanel.add(checkButton);
        inputPanel.add(nextButton);
        add(inputPanel, BorderLayout.SOUTH);

        feedbackLabel = new JLabel(" ", SwingConstants.CENTER);
        feedbackLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(feedbackLabel, BorderLayout.NORTH);

        questionPool = generateQuestions();

        checkButton.addActionListener(this::checkAnswer);
        nextButton.addActionListener(e -> showNewQuestion());

        showNewQuestion();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void showNewQuestion() {
        Random random = new Random();
        currentQuestion = questionPool.get(random.nextInt(questionPool.size()));
        questionArea.setText(currentQuestion.text);
        answerField.setText("");
        feedbackLabel.setText(" ");
    }

    private void checkAnswer(ActionEvent e) {
        String userAnswer = answerField.getText().trim();
        if (userAnswer.equals(currentQuestion.answer)) {
            feedbackLabel.setText("✅ Правильно!");
            feedbackLabel.setForeground(new Color(0, 128, 0));
        } else {
            feedbackLabel.setText("❌ Неправильно. Правильна відповідь: " + currentQuestion.answer);
            feedbackLabel.setForeground(Color.RED);
        }
    }

    private List<Question> generateQuestions() {
        List<Question> list = new ArrayList<>();

        // Тернарные
        list.add(new Question("int x = 10, y = 5;\nint result = (x > y) ? x : y;\nЧому дорівнює result?", "10"));
        list.add(new Question("int a = 3;\nString msg = (a % 2 == 0) ? \"even\" : \"odd\";\nЧому дорівнює msg?", "odd"));
        list.add(new Question("int score = 70;\nString grade = (score >= 60) ? \"Pass\" : \"Fail\";\nЧому дорівнює grade?", "Pass"));
        list.add(new Question("int x = 7, y = 15;\nint result = (x > y) ? x : y;\nЧому дорівнює result?", "15"));
        list.add(new Question("int a = 5, b = 10;\nint max = (a > b) ? a : b;\nЧому дорівнює max?", "10"));

        list.add(new Question("int x = 4;\nString result = (x % 2 == 0) ? ((x > 0) ? \"even and positive\" : \"even and negative\") : \"odd\";\nЧому дорівнює result?", "even and positive"));

        list.add(new Question("int a = -3;\nString sign = (a >= 0) ? \"positive\" : ((a < -10) ? \"very negative\" : \"negative\");\nЧому дорівнює sign?", "negative"));

        list.add(new Question("int age = 20;\nString category = (age < 13) ? \"child\" : (age < 20) ? \"teen\" : \"adult\";\nЧому дорівнює category?", "adult"));

        list.add(new Question("int temp = 0;\nString status = (temp > 0) ? \"above zero\" : (temp < 0) ? \"below zero\" : \"exactly zero\";\nЧому дорівнює status?", "exactly zero"));

        list.add(new Question("boolean hasKey = true;\nboolean doorLocked = false;\nString action = (hasKey && !doorLocked) ? \"open\" : \"wait\";\nЧому дорівнює action?", "open"));
        // Циклы
        list.add(new Question("int sum = 0;\nfor (int i = 1; i <= 3; i++) {\n    sum += i;\n}\nЧому дорівнює sum?", "6"));
        list.add(new Question("int count = 0;\nfor (int i = 5; i < 10; i++) {\n    count++;\n}\nЧому дорівнює count?", "5"));
        list.add(new Question("int i = 0;\nwhile (i < 3) {\n    i++;\n}\nЧому дорівнює i?", "3"));

        return list;
    }

    private static class Question {
        String text;
        String answer;

        public Question(String text, String answer) {
            this.text = text;
            this.answer = answer;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TernaryLoopQuizGUI::new);
    }
}
