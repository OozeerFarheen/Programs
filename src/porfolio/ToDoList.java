package porfolio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ToDoList extends JFrame {
    private DefaultListModel<String> tasks;
    private JList<String> taskList;
    private JTextField taskInput;
    private ArrayList<Integer> completed;

    public ToDoList() {
        setTitle("Daily To‑Do List");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tasks = new DefaultListModel<>();
        taskList = new JList<>(tasks);
        taskList.setCellRenderer(new TaskRenderer());
        completed = new ArrayList<>();

        // Input panel
        JPanel inputPanel = new JPanel();
        taskInput = new JTextField(20);
        JButton addBtn = new JButton("Add");
        inputPanel.add(taskInput);
        inputPanel.add(addBtn);

        // Buttons
        JButton toggleBtn = new JButton("Mark Done/Undone");
        JButton deleteBtn = new JButton("Delete");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(toggleBtn);
        buttonPanel.add(deleteBtn);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(taskList), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add task
        addBtn.addActionListener(e -> {
            String text = taskInput.getText().trim();
            if (!text.isEmpty()) {
                tasks.addElement(text);
                taskInput.setText("");
            }
        });

        // Toggle completion
        toggleBtn.addActionListener(e -> {
            int index = taskList.getSelectedIndex();
            if (index != -1) {
                if (completed.contains(index)) completed.remove((Integer) index);
                else completed.add(index);
                taskList.repaint();
            }
        });

        // Delete task
        deleteBtn.addActionListener(e -> {
            int index = taskList.getSelectedIndex();
            if (index != -1) {
                tasks.remove(index);
                completed.remove((Integer) index);
            }
        });
    }

    // Renderer for strike‑through
    class TaskRenderer extends DefaultListCellRenderer {
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component comp = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (completed.contains(index)) {
                setText("<html><strike>" + value.toString() + "</strike></html>");
            }
            return comp;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ToDoList().setVisible(true));
    }
}
