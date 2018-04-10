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

// TODO: The constants in this class need to be translated.
public class PriorityPanel
extends
AbstractPanel {
    private Priority _priority;
    private ServiceGetPriorityList _serviceGetPriorityList;
    private ServiceGetPriority _serviceGetPriority;

    public PriorityPanel() {
        FactoryServicesPriority factory = FactoryServicesPriority.getInstance();
        _serviceGetPriorityList = factory.getServiceGetPriorityList();
        
        // TODO: There will be a problem were no priority will be set 
        // when this constructor is called instead of a 0 value priority.
    }
    
    // TODO: Refactor this mess
    public PriorityPanel(String priorityId) {
        this();
        FactoryServicesPriority factory = FactoryServicesPriority.getInstance();
        _serviceGetPriority = factory.getServiceGetPriority(priorityId);
        _serviceGetPriority.execute();
        _priority = _serviceGetPriority.getResult();

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JLabel priorityLabel = new JLabel("Priority");
        
        // XXX: This is only for testing, it should come from a specific service.
        String[] priorities = new String[] {"High", "Medium", "Low"};
        JComboBox priorityList = new JComboBox(priorities);
        priorityList.setMaximumSize(new Dimension(100, 30));
        priorityList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("Priority: " + String.valueOf(priorityList.getSelectedItem()));
            }
        });

        add(priorityLabel);
        add(Box.createRigidArea(new Dimension(30, 0)));
        add(priorityList);
        add(Box.createHorizontalGlue());
    }

    @Override
    public void update() {
        // TODO: Not implemented
    }

    public void enableEditing() {
        // TODO: Not implemented.
    }

    public void disableEditing() {
        // TODO: Not implemented.
    }

    public String getPriorityId() {
        return _priority.getId();
    }
}
