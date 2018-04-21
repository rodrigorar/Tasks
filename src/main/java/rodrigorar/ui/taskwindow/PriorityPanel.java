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
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.Box;
import javax.swing.BoxLayout;

import rodrigorar.domain.services.priority.FactoryServicesPriority;
import rodrigorar.domain.services.priority.ServiceGetPriorityList;
import rodrigorar.domain.services.priority.ServiceGetPriority;
import rodrigorar.domain.pojos.Priority;
import rodrigorar.ui.AbstractPanel;
import rodrigorar.ui.templates.FormPanelTemplate;

// TODO: The constants in this class need to be translated.
public class PriorityPanel
extends AbstractPanel
implements FormPanelTemplate {
    private FactoryServicesPriority _factoryServicesPriority;

    private Priority _priority;

    private JComboBox _priorityCombo;

    public JLabel createNameLabel() {
        return new JLabel("Priority");
    }

    public JComboBox createPriorityComboBox(Priority priority, Boolean editable) {
        // XXX: This is only for testing, it should come from a specific service.
        String[] priorities = new String[] {"High", "Medium", "Low"};

        _priorityCombo = new JComboBox(priorities);
        _priorityCombo.setMaximumSize(new Dimension(100, 30));
        _priorityCombo.setEnabled(editable);

        _priorityCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                // TODO: Get the new priority object from the service layer.
                // TODO: Set the new priority has the current value
                System.out.println("Priority: " + String.valueOf(_priorityCombo.getSelectedItem()));
            }
        });

        return _priorityCombo;
    }

    @Override
    public void update() {
        // TODO: Not implemented
    }

    @Override
    public void enableEditing() {
        _priorityCombo.setEnabled(true);
    }

    @Override
    public void disableEditing() {
        _priorityCombo.setEnabled(false);
    }

    public String getPriorityId() {
        return _priority.getId();
    }

    public Priority getPriority() {
        return _priority;
    }

    @Override
    public void configure() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        _factoryServicesPriority = FactoryServicesPriority.getInstance();
    }

    // TODO: Refactor this mess
    public PriorityPanel(String priorityId, Boolean editable) {
        configure();

        add(createNameLabel());
        add(Box.createRigidArea(new Dimension(39, 0)));
        add(createPriorityComboBox(_priority, editable));
        add(Box.createHorizontalGlue());
    }
}
