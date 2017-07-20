package rodrigorar.ui;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.BorderFactory;

import rodrigorar.entities.Task;
import rodrigorar.entities.TaskList;
import rodrigorar.entities.EntityManager;


public class MainWindow
extends
JFrame {
    private MainWindow _instance;
    private JList _list;

    public MainWindow() {
        _list = new JList(new DefaultListModel());
        _instance = this;
        initUI();
    }

    public JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton newTask = new JButton("New Task");
        newTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                TaskWindow window = new TaskWindow(_instance);
                window.setVisible(true);
            }
        });

        JButton searchTask = new JButton("Search");
        searchTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("Search for Task");
            }
        });

        panel.add(newTask);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(searchTask);
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    public JList updateList() {
        DefaultListModel<String> model = (DefaultListModel<String>)_list.getModel();
        EntityManager manager = EntityManager.getInstance();
        TaskList list = manager.getTaskList();

        model.clear();

        for (Task task : list.getAllTasks()) {
            model.addElement(task.getTitle());
        }

        return _list;
    }

    public JPanel createListPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        JScrollPane scrollPane = new JScrollPane(updateList());

        panel.add(scrollPane);

        return panel;
    }

    public JPanel createLayout() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(createButtonPanel());
        panel.add(Box.createRigidArea(new Dimension(10, 0)));
        panel.add(createListPanel());

        return panel;
    }

    public void initUI() {
        add(createLayout());
        setTitle("Tasks");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
