package rodrigorar.ui;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.BorderFactory;

import rodrigorar.utils.Labels;
import rodrigorar.entities.Task;
import rodrigorar.entities.EntityManager;

public class SearchWindow
extends
JFrame {
    private MainWindow _parentWindow;
    private JTextArea _searchBox;
    private EntityManager _entityManager;

    public SearchWindow(MainWindow parentWindow) {
        _parentWindow = parentWindow;
        _entityManager = EntityManager.getInstance();
        _searchBox = new JTextArea();
        initUI();
    }

    private JPanel createSearchArea() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        _searchBox.setLineWrap(true);
        _searchBox.setWrapStyleWord(true);
        _searchBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        _searchBox.setMaximumSize(new Dimension(2000, 20));

        panel.add(_searchBox);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        JButton searchButton = new JButton(Labels.SEARCH);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Task task = _entityManager.getTask(_searchBox.getText());
                TaskWindow window = new TaskWindow(_parentWindow, task);
                window.setVisible(true);
            }
        });

        panel.add(Box.createHorizontalGlue());
        panel.add(searchButton);

        return panel;
    }

    private JPanel createLayout() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(createSearchArea());
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(createButtonPanel());

        return panel;
    }

    private void initUI() {
        add(createLayout());
        setTitle(Labels.SEARCH);
        setSize(300, 100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
