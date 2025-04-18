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
        setTitle("Угадай результат");
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
        JButton checkButton = new JButton("Проверить");
        JButton nextButton = new JButton("Следующий вопрос");

        inputPanel.add(new JLabel("Ответ:"));
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
            feedbackLabel.setText("❌ Неправильно. Правильный ответ: " + currentQuestion.answer);
            feedbackLabel.setForeground(Color.RED);
        }
    }

    private List<Question> generateQuestions() {
        List<Question> list = new ArrayList<>();

        // Тернарные
        list.add(new Question("int x = 10, y = 5;\nint result = (x > y) ? x : y;\nЧему равен result?", "10"));
        list.add(new Question("int a = 3;\nString msg = (a % 2 == 0) ? \"even\" : \"odd\";\nЧему равен msg?", "odd"));
        list.add(new Question("int score = 70;\nString grade = (score >= 60) ? \"Pass\" : \"Fail\";\nЧему равен grade?", "Pass"));

        // Циклы
        list.add(new Question("int sum = 0;\nfor (int i = 1; i <= 3; i++) {\n    sum += i;\n}\nЧему равен sum?", "6"));
        list.add(new Question("int count = 0;\nfor (int i = 5; i < 10; i++) {\n    count++;\n}\nЧему равен count?", "5"));
        list.add(new Question("int i = 0;\nwhile (i < 3) {\n    i++;\n}\nЧему равен i?", "3"));

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
