package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ToDoList extends JFrame implements ActionListener {

    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskInputField;
    private JButton addButton, deleteButton, clearButton, markDoneButton;

    public ToDoList() {
        setTitle("To-Do List");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // North: Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout(5, 5));

        taskInputField = new JTextField();
        addButton = new JButton("Add Task");
        addButton.addActionListener(this);

        inputPanel.add(taskInputField, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);

        // Center: Task List
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        JScrollPane scrollPane = new JScrollPane(taskList);

        // South: Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 10, 10));

        markDoneButton = new JButton("Mark as Done");
        deleteButton = new JButton("Delete Task");
        clearButton = new JButton("Clear All");

        markDoneButton.addActionListener(this);
        deleteButton.addActionListener(this);
        clearButton.addActionListener(this);

        buttonPanel.add(markDoneButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        // Add to Frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = taskInputField.getText().trim();

        if (e.getSource() == addButton) {
            if (!input.isEmpty()) {
                taskListModel.addElement(input);
                taskInputField.setText("");
            }
        } else if (e.getSource() == deleteButton) {
            int index = taskList.getSelectedIndex();
            if (index != -1) {
                taskListModel.remove(index);
            }
        } else if (e.getSource() == markDoneButton) {
            int index = taskList.getSelectedIndex();
            if (index != -1) {
                String task = taskListModel.get(index);
                if (!task.startsWith("✔ ")) {
                    taskListModel.set(index, "✔ " + task);
                }
            }
        } else if (e.getSource() == clearButton) {
            int confirm = JOptionPane.showConfirmDialog(this, "Clear all tasks?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                taskListModel.clear();
            }
        }
    }

    public static void main(String[] args) {
        new ToDoList();
    }
}
