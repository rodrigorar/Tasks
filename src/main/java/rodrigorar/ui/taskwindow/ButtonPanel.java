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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.Box;

import rodrigorar.domain.services.ServicesLanguage;
import rodrigorar.domain.services.ServicesFactory;
import rodrigorar.domain.interfaces.IOperationsFacade;
import rodrigorar.domain.pojos.Task;
import rodrigorar.domain.exceptions.InvalidTitleException;
import rodrigorar.utils.Constants.Labels;
import rodrigorar.ui.AbstractWindow;
import rodrigorar.ui.AbstractPanel;
import rodrigorar.ui.taskwindow.TitlePanel;
import rodrigorar.ui.taskwindow.DescriptionPanel;

public class ButtonPanel
extends
AbstractPanel {
    public static final long serialVersionUID = 1L;
    private AbstractWindow _parentWindow;
    private ServicesLanguage _languageServices;
    private IOperationsFacade _operations;
    private TitlePanel _titlePanel;
    private DescriptionPanel _descriptionPanel;
    private Task _task;

    @Override
    public void update() {
        // TODO
    }

    private JButton save() {
        JButton button = new JButton(_languageServices.getTranslation(Labels.SAVE));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    if (_task == null) {
                        _task = _operations.createTask(
                            _titlePanel.getText(), _descriptionPanel.getText().trim()
                        );
                    } else {
                        _task.setTitle(_titlePanel.getText());
                        _task.setDescription(_descriptionPanel.getText().trim());
                    }
                } catch (InvalidTitleException exception) {
                    exception.printStackTrace();
                }
                _operations.save();
                _parentWindow.update();
                _parentWindow.dispose();
            }
        });

        return button;
    }

    public JButton edit() {
        JButton button = new JButton(_languageServices.getTranslation(Labels.EDIT));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (_titlePanel.isEditable() && _descriptionPanel.isEditable()) {
                    _titlePanel.disableEditing();
                    _descriptionPanel.disableEditing();
                } else {
                    _titlePanel.enableEditing();
                    _descriptionPanel.enableEditing();
                }
            }
        });

        return button;
    }

    private void configure() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    public ButtonPanel(
        AbstractWindow parentWindow,
        Task task,
        TitlePanel titlePanel,
        DescriptionPanel descriptionPanel) {

        _parentWindow = parentWindow;
        _languageServices = ServicesLanguage.getInstance();
        _operations = ServicesFactory.getOperations();
        _titlePanel = titlePanel;
        _descriptionPanel = descriptionPanel;

        configure();

        add(Box.createHorizontalGlue());
        add(save());
        add(Box.createRigidArea(new Dimension(10, 0)));
        add(edit());
    }
}
