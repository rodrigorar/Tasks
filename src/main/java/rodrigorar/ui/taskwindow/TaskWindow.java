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

package rodrigorar.ui.taskwindow;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import rodrigorar.domain.pojos.Task;
import rodrigorar.domain.services.ServicesLanguage;
import rodrigorar.ui.AbstractWindow;
import rodrigorar.utils.Constants.Labels;

public class TaskWindow
extends
AbstractWindow {
	private static final long serialVersionUID = -6298053968382452462L;
	private AbstractWindow _parentWindow;
	
    private ServicesLanguage _servicesLanguage;
    
    private Task _task;
	private TitlePanel _titlePanel;
    private DescriptionPanel _descriptionPanel;

    public TaskWindow(AbstractWindow parentWindow) {
		_titlePanel = new TitlePanel(null, true);
		_descriptionPanel = new DescriptionPanel(null, true);

		init(parentWindow);
        initUI();
    }

    public TaskWindow(AbstractWindow parentWindow, Task task) {
        init(parentWindow);
        _task = task;

        _titlePanel = new TitlePanel(task.getTitle(), false);
		_descriptionPanel = new DescriptionPanel(task.getDescription(), false);

        initUI();
    }

    private void init(AbstractWindow parentWindow) {
        _parentWindow = parentWindow;
        _servicesLanguage = ServicesLanguage.getInstance();
    }

    @Override
    public void update() {
        _parentWindow.update();
    }

    private JPanel createLayout() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(_titlePanel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(_descriptionPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(new ButtonPanel(this, _task, _titlePanel, _descriptionPanel));

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
