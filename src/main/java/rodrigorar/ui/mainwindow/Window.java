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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;

import rodrigorar.domain.interfaces.IOperationsFacade;
import rodrigorar.domain.services.ServicesFactory;
import rodrigorar.domain.services.ServicesLanguage;
import rodrigorar.ui.AbstractWindow;
import rodrigorar.utils.Constants.Labels;

public class Window
extends
AbstractWindow {
	private static final long serialVersionUID = 5368744912178878243L;
	private AbstractWindow _instance;
    private ServicesLanguage _servicesLanguage = ServicesLanguage.getInstance();
    private IOperationsFacade _operations;
    private JList<String> _list;

    public Window() {
        _list = new JList<String>(new DefaultListModel<String>());
        _instance = this;

        _operations = ServicesFactory.getOperations();
        _servicesLanguage = ServicesLanguage.getInstance();

        initUI();
    }

    @Override
    public void update() {
        updateList();
    }

    public JList<String> updateList() {
        DefaultListModel<String> model = (DefaultListModel<String>)_list.getModel();
        List<String> list = _operations.getTaskNames("");

        model.clear();

        for (String task : list) {
            model.addElement(task);
        }

        return _list;
    }

    private JPanel createLayout() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new ButtonPanel(_instance));
        panel.add(Box.createRigidArea(new Dimension(10, 0)));
        panel.add(new ListsPanel(_instance, updateList()));

        return panel;
    }

    private void windowEvents() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                _operations.save();
            }
        });
    }

    private void initUI() {
        add(createLayout());
        windowEvents();
        setTitle(_servicesLanguage.getTranslation(Labels.TASK));
        setSize(800, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
