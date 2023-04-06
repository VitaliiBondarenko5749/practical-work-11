import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FunctionTable extends JFrame {

    private JTextField aField;
    private JTextArea resultArea;

    public FunctionTable() {
        super("Таблиця значень функції");

        // Встановлення зовнішнього вигляду застосування
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}

        // Встановлення розмірів вікна
        setSize(500, 300);

        // Додавання візуальних компонентів на форму
        Container container = getContentPane();
        JPanel inputPanel = new JPanel(new GridLayout(1, 2));
        container.add(inputPanel, BorderLayout.NORTH);
        inputPanel.add(new JLabel("A:"));
        aField = new JTextField();
        inputPanel.add(aField);
        JPanel resultPanel = new JPanel(new BorderLayout());
        container.add(resultPanel, BorderLayout.CENTER);
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        resultPanel.add(scrollPane);

        // Обробник події натискання на клавішу "Розрахувати"
        JButton calculateButton = new JButton("Розрахувати");
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculate();
            }
        });
        container.add(calculateButton, BorderLayout.SOUTH);

        // Обробник події закриття вікна
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // Відображення вікна на екрані
        setVisible(true);
    }

    // Метод розрахунку значень функції та виведення їх у вікні
    private void calculate() {
        // Отримання значення A
        double a = 0;
        try {
            a = Double.parseDouble(aField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Неправильний формат значення A!", "Помилка", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Обчислення значень функції та додавання їх у поле виведення
        resultArea.setText("");
        resultArea.append(String.format("%10s  %10s%n", "X", "Y"));
        for (double x = 0; x <= 10; x += 0.1) {
            double y = a * Math.sqrt(x) * Math.sin(a * x);
            resultArea.append(String.format("%10.2f  %10.2f%n", x, y));
        }
    }

    public static void main(String[] args) {
        new FunctionTable();
    }
}