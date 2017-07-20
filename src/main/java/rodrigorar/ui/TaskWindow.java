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
import rodrigorar.utils.Labels;

public class TaskWindow
extends
JFrame {
    private MainWindow _parentWindow;
    private EntityManager _manager;
    private Task _task;
    private JTextArea _title;
    private JTextArea _description;

    public TaskWindow(MainWindow parentWindow) {
        init(parentWindow);
        initUI();
    }

    public TaskWindow(MainWindow parentWindow, Task task) {
        init(parentWindow);
        _task = task;
        _title.setText(_task.getTitle());
        _description.setText(_task.getDescription());
        _title.disable();
        _description.disable();
        initUI();
    }

    private void init(MainWindow parentWindow) {
        _parentWindow = parentWindow;
        _title = new JTextArea();
        _description = new JTextArea();
        _manager = EntityManager.getInstance();
    }

    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));

        JLabel titleLabel = new JLabel(Labels.TITLE);

        _title.setLineWrap(true);
        _title.setWrapStyleWord(true);
        _title.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        _title.setMaximumSize(new Dimension(2000, 20));

        titlePanel.add(titleLabel);
        titlePanel.add(Box.createRigidArea(new Dimension(10, 0)));
        titlePanel.add(_title);

        return titlePanel;
    }

    private JPanel createDescriptionPanel() {
        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.X_AXIS));

        JLabel descriptionLabel = new JLabel(Labels.DESCRIPTION);

        _description.setLineWrap(true);
        _description.setWrapStyleWord(true);
        _description.setRows(3);
        _description.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        _description.setMaximumSize(new Dimension(2000, 60));

        descriptionPanel.add(descriptionLabel);
        descriptionPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        descriptionPanel.add(_description);

        return descriptionPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        JButton save = new JButton(Labels.SAVE);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    if (_task == null) {
                        _task = _manager.newTask(_title.getText().trim(), _description.getText().trim());
                    } else {
                        _task.setTitle(_title.getText().trim());
                        _task.setDescription(_description.getText().trim());
                    }
                } catch (InvalidTitleException exception) {
                    exception.printStackTrace();
                }
                _manager.save();
                _parentWindow.updateList();
                dispose();
            }
        });

        JButton edit = new JButton(Labels.EDIT);
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

        return buttonPanel;
    }

    private JPanel createLayout() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(createTitlePanel());
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(createDescriptionPanel());
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(createButtonPanel());

        return panel;
    }

    private void initUI() {
        add(createLayout());
        setTitle(Labels.TASK);
        setSize(500, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
