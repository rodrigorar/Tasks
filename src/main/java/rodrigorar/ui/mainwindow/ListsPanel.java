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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

import rodrigorar.domain.pojos.Task;
import rodrigorar.domain.services.ServicesOperations;
import rodrigorar.domain.services.ServicesFactory;
import rodrigorar.domain.services.ServicesLanguage;
import rodrigorar.ui.AbstractWindow;
import rodrigorar.ui.AbstractPanel;
import rodrigorar.ui.taskwindow.TaskWindow;
import rodrigorar.utils.Constants.Labels;

public class ListsPanel
extends
AbstractPanel {
	private static final long serialVersionUID = 3248650729009387031L;
	public static final int CLICKS = 2;
    private AbstractWindow _parentWindow;
    private ServicesOperations _operations;
    private ServicesLanguage _languageServices;
    private JList<String> _tasks;

	@Override
	public void update() {
		// TODO
	}

    private void configure() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    private JScrollPane taskList() {
        _tasks.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent event) {
                int index = _tasks.locationToIndex(event.getPoint());
                // TODO: Remove this task object from here, this class shouldn't know about this.
                Task task = _operations.findTask((String)_tasks.getModel().getElementAt(index));

                if (event.getButton() == MouseEvent.BUTTON1
                    && event.getClickCount() == CLICKS) {
                    TaskWindow window = new TaskWindow(_parentWindow, task);
                    window.setVisible(true);
                } else if (event.getButton() == MouseEvent.BUTTON3) {
                    JPopupMenu menu = new JPopupMenu();
                    JMenuItem deleteItem =
                        new JMenuItem(_languageServices.getTranslation(Labels.DELETE));
                    deleteItem.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent event) {
                            int[] selectedTasks = _tasks.getSelectedIndices();

                            for (int iter = 0; iter < selectedTasks.length; iter++) {
                            	String taskId =
                            			(String)_tasks.getModel().getElementAt(selectedTasks[iter]);
                            	_operations.deleteTask(_operations.findTask(taskId));
                            }

                            _parentWindow.update();
                        }
                    });

                    menu.add(deleteItem);
                    menu.show(_tasks, event.getPoint().x, event.getPoint().y);
                }
            }

            @Override public void mouseExited(MouseEvent event) {}

            @Override public void mouseEntered(MouseEvent event) {}

            @Override public void mouseReleased(MouseEvent event) {}

            @Override public void mousePressed(MouseEvent event) {}
        });

        return new JScrollPane(_tasks);
    }

    public ListsPanel(AbstractWindow parentWindow, JList<String> tasks) {
        _parentWindow = parentWindow;
        _tasks = tasks;

        _operations = ServicesFactory.getInstance().getOperations();
        _languageServices = ServicesFactory.getInstance().getLanguageServices();

        configure();

        add(taskList());
    }
}
