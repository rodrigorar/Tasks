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
import rodrigorar.entities.EntityManager;

public class SearchWindow
extends
JFrame {
    private MainWindow _parentWindow;
    private JTextArea _searchBox;

    public SearchWindow(MainWindow parentWindow) {
        _parentWindow = parentWindow;
        _searchBox = new JTextArea();
        initUI();
    }

    public JPanel createLayout() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        _searchBox.setLineWrap(true);
        _searchBox.setWrapStyleWord(true);
        _searchBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        _searchBox.setMaximumSize(new Dimension(2000, 20));

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));

        searchPanel.add(_searchBox);

        JButton search = new JButton(Labels.SEARCH);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("Search for Task");
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(search);

        panel.add(searchPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(buttonPanel);

        return panel;
    }

    public void initUI() {
        add(createLayout());
        setTitle(Labels.SEARCH);
        setSize(300, 100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
