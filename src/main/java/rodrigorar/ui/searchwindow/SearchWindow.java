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

package rodrigorar.ui.searchwindow;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import rodrigorar.domain.interfaces.IOperationsFacade;
import rodrigorar.domain.pojos.Task;
import rodrigorar.domain.services.ServicesFactory;
import rodrigorar.domain.services.ServicesLanguage;
import rodrigorar.utils.Constants.Labels;
import rodrigorar.ui.AbstractWindow;
import rodrigorar.ui.taskwindow.TaskWindow;

public class SearchWindow
extends
AbstractWindow {
	private static final long serialVersionUID = -6557029694151213025L;
	private AbstractWindow _parentWindow;
    private ServicesLanguage _servicesLanguage;
    private JTextArea _searchBox;
    private IOperationsFacade _operations;

    public SearchWindow(AbstractWindow parentWindow) {
        _parentWindow = parentWindow;

        _servicesLanguage = ServicesLanguage.getInstance();
        _operations = ServicesFactory.getOperations();

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

        JButton searchButton = new JButton(_servicesLanguage.getTranslation(Labels.SEARCH));
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Task task = _operations.findTask(_searchBox.getText());
                TaskWindow window = new TaskWindow(_parentWindow, task);
                window.setVisible(true);
            }
        });

        panel.add(Box.createHorizontalGlue());
        panel.add(searchButton);

        return panel;
    }

    @Override
    public void update() {
        // Empty Method
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
        setTitle(_servicesLanguage.getTranslation(Labels.SEARCH));
        setSize(300, 110);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
