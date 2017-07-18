package rodrigorar.ui;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.BorderFactory;

public class MainWindow
extends
JFrame {

    public MainWindow() {
        initUI();
    }

    public JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JButton newTask = new JButton("New Task");
        newTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("New Task");
            }
        });

        JButton searchTask = new JButton("Search");
        searchTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("Search for Task");
            }
        });

        buttonPanel.add(newTask);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(searchTask);
        buttonPanel.add(Box.createVerticalGlue());

        return buttonPanel;
    }

    public JPanel createLayout() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JList taskList = new JList();

        JScrollPane scrollPane = new JScrollPane(taskList);

        mainPanel.add(createButtonPanel());
        mainPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        mainPanel.add(scrollPane);

        return mainPanel;
    }

    public void initUI() {
        add(createLayout());
        setTitle("Tasks");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
