package rodrigorar.ui;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.BorderFactory;

import rodrigorar.entities.Task;
import rodrigorar.entities.EntityManager;
import rodrigorar.entities.exceptions.InvalidTitleException;

public class TaskWindow
extends
JFrame {
    private MainWindow _parentWindow;
    private Task _task;
    private JTextArea _title;
    private JTextArea _description;

    public TaskWindow(MainWindow parentWindow) {
        try {
            _parentWindow = parentWindow;
            _task = new Task("Title");
            _title = new JTextArea();
            _description = new JTextArea();
            initUI();
        } catch (InvalidTitleException exception) {
            exception.printStackTrace();
        }
    }

    public TaskWindow(MainWindow parentWindow, Task task) {
        _parentWindow = parentWindow;
        _task = task;
        _title = new JTextArea();
        _title.disable();
        _description = new JTextArea();
        _title.disable();
        initUI();
    }

    public JPanel createLayout() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));

        JLabel titleLabel = new JLabel("Title:");

        _title.setLineWrap(true);
        _title.setWrapStyleWord(true);
        _title.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        _title.setMaximumSize(new Dimension(2000, 20));

        titlePanel.add(titleLabel);
        titlePanel.add(Box.createRigidArea(new Dimension(10, 0)));
        titlePanel.add(_title);

        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.X_AXIS));

        JLabel descriptionLabel = new JLabel("Description:");

        _description.setLineWrap(true);
        _description.setWrapStyleWord(true);
        _description.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        _description.setMaximumSize(new Dimension(2000, 60));

        descriptionPanel.add(descriptionLabel);
        descriptionPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        descriptionPanel.add(_description);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        JButton save = new JButton("Save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println(_title.getText().trim());
                System.out.println(_description.getText().trim());
            }
        });

        JButton edit = new JButton("Edit");
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (_title.isEnabled() && _description.isEnabled()) {
                    _title.disable();
                    _description.disable();
                } else {
                    _title.enable();
                    _description.enable();
                }
            }
        });

        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(save);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPanel.add(edit);

        panel.add(titlePanel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(descriptionPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(buttonPanel);

        return panel;
    }

    public void initUI() {
        add(createLayout());
        setTitle("Task");
        setSize(500, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
