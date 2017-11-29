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

package rodrigorar.ui.mainwindow;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JButton;

import rodrigorar.domain.services.ServicesLanguage;
import rodrigorar.utils.Constants.Labels;
import rodrigorar.ui.taskwindow.TaskWindow;
import rodrigorar.ui.SearchWindow;
import rodrigorar.ui.SettingsWindow;
import rodrigorar.ui.AbstractWindow;

public class ButtonPanel
extends
JPanel {
	private static final long serialVersionUID = -1485868804409887489L;
	private AbstractWindow _parentWindow;
    private ServicesLanguage _languageServices;


    private void configure() {
        setLayout(new GridLayout(6, 1, 0, 5));
        setMaximumSize(new Dimension(150, 3000));
    }

    private JButton newTaskButton() {
        JButton button = new JButton(_languageServices.getTranslation(Labels.NEW_TASK));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                TaskWindow window = new TaskWindow(_parentWindow);
                window.setVisible(true);
            }
        });

        return button;
    }

    private JButton searchTaskButton() {
        JButton button = new JButton(_languageServices.getTranslation(Labels.SEARCH));

        button.setMaximumSize(new Dimension(3000, 40));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                SearchWindow window = new SearchWindow(_parentWindow);
                window.setVisible(true);
            }
        });

        return button;
    }

    private JButton settingsButton() {
        JButton button = new JButton(_languageServices.getTranslation(Labels.SETTINGS));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                SettingsWindow window = new SettingsWindow(_parentWindow);
                window.setVisible(true);
            }
        });

        return button;
    }

    public ButtonPanel(AbstractWindow parentWindow) {
        _languageServices = ServicesLanguage.getInstance();
        _parentWindow = parentWindow;

        configure();

        add(newTaskButton());
        add(searchTaskButton());
        add(settingsButton());
    }

}