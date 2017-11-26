/******************************************************************************
* Copyright 2017 Rodrigo Ramos Rosa
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*******************************************************************************/

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
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.BorderFactory;

import rodrigorar.domain.pojos.Task;
import rodrigorar.domain.services.ServicesLanguage;
import rodrigorar.domain.services.ServicesFactory;
import rodrigorar.domain.interfaces.IOperationsFacade;
import rodrigorar.domain.exceptions.InvalidTitleException;
import rodrigorar.utils.Constants.Labels;
import rodrigorar.utils.UIUtils;
import rodrigorar.ui.AbstractWindow;

public class TaskWindow
extends
AbstractWindow {
    private AbstractWindow _parentWindow;
    private IOperationsFacade _operations;
    private ServicesLanguage _servicesLanguage;
    private Task _task;
    private JScrollPane _title;
    private JScrollPane _description;

    public TaskWindow(AbstractWindow parentWindow) {
        init(parentWindow);
        initUI();
    }

    public TaskWindow(AbstractWindow parentWindow, Task task) {
        init(parentWindow);
        _task = task;

        JTextArea title =
            UIUtils.getInnerComponent(
                JTextArea.class, _title
            );
        title.setText(_task.getTitle());
        title.setEnabled(false);
        title.setDisabledTextColor(Color.BLACK);

        JTextArea description =
            UIUtils.getInnerComponent(
                JTextArea.class, _description
            );
        description.setText(_task.getDescription());
        description.setEnabled(false);
        description.setDisabledTextColor(Color.BLACK);

        initUI();
    }

    private void init(AbstractWindow parentWindow) {
        _parentWindow = parentWindow;
        _title = UIUtils.<JTextArea>buildScrollable(new JTextArea(), 2000, 20);
        _description = UIUtils.<JTextArea>buildScrollable(new JTextArea(), 2000, 500);

        ServicesFactory factory = new ServicesFactory();
        _operations = factory.getOperations();
        _servicesLanguage = ServicesLanguage.getInstance();
    }

    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));

        JLabel titleLabel = new JLabel(_servicesLanguage.getTranslation(Labels.TITLE));

        JTextArea title =
            UIUtils.getInnerComponent(
                JTextArea.class,
                _title
            );
        title.setLineWrap(true);
        title.setWrapStyleWord(true);
        title.setMaximumSize(new Dimension(20, 20));

        titlePanel.add(titleLabel);
        titlePanel.add(Box.createRigidArea(new Dimension(59, 0)));
        titlePanel.add(_title);

        return titlePanel;
    }

    private JPanel createDescriptionPanel() {
        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.X_AXIS));

        JLabel descriptionLabel =
            new JLabel(_servicesLanguage.getTranslation(Labels.DESCRIPTION));

        JTextArea description =
            UIUtils.getInnerComponent(
                JTextArea.class,
                _description
            );
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setRows(3);
        description.setMaximumSize(new Dimension(2000, 60));

        descriptionPanel.add(descriptionLabel);
        descriptionPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        descriptionPanel.add(_description);

        return descriptionPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        JButton save = new JButton(_servicesLanguage.getTranslation(Labels.SAVE));
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    JTextArea title =
                        UIUtils.getInnerComponent(
                            JTextArea.class,
                            _title
                        );
                    JTextArea description =
                        UIUtils.getInnerComponent(
                            JTextArea.class,
                            _description
                        );

                    if (_task == null) {
                        _task = _operations.createTask(
                            title.getText().trim(),description.getText().trim()
                        );
                    } else {
                        _task.setTitle(title.getText().trim());
                        _task.setDescription(description.getText().trim());
                    }
                } catch (InvalidTitleException exception) {
                    exception.printStackTrace();
                }
                _operations.save();
                _parentWindow.update();
                dispose();
            }
        });

        JButton edit = new JButton(_servicesLanguage.getTranslation(Labels.EDIT));
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JTextArea title =
                    UIUtils.getInnerComponent(
                        JTextArea.class,
                        _title
                    );
                JTextArea description =
                    UIUtils.getInnerComponent(
                        JTextArea.class,
                        _description
                    );
                if (title.isEnabled() && description.isEnabled()) {
                    title.setEnabled(false);
                    description.setEnabled(false);
                } else {
                    title.setEnabled(true);
                    description.setEnabled(true);
                }
            }
        });

        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(save);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPanel.add(edit);

        return buttonPanel;
    }

    @Override
    public void update() {
        // Empty Method
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
        setTitle(_servicesLanguage.getTranslation(Labels.TASK));
        setSize(600, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
