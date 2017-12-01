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

package rodrigorar.ui.settingswindow;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.Box;

import rodrigorar.domain.services.ServicesLanguage;
import rodrigorar.domain.pojos.AppConfigurations;
import rodrigorar.ui.AbstractWindow;
import rodrigorar.ui.settingswindow.LanguagePanel;
import rodrigorar.ui.settingswindow.DataPanel;
import rodrigorar.utils.Constants.Labels;

public class ButtonPanel
extends
JPanel {
    public static final long serialVersionUID = 1L;
    private AbstractWindow _parentWindow;
    private ServicesLanguage _languageServices;
    private AppConfigurations _configs;

    private JButton saveButton(
        LanguagePanel languagePanel,
        DataPanel dataPanel) {

        JButton button = new JButton(_languageServices.getTranslation(Labels.SAVE));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (dataPanel.getDataDirectory() != null) {
                    _configs.setDataDirectory(dataPanel.getDataDirectory());
                }

                if (languagePanel.getCurrentLanguage() != null) {
                    _configs.setLanguage(languagePanel.getCurrentLanguage());
                    System.out.println("Language: " + languagePanel.getCurrentLanguage());
                    _languageServices.setActiveLanguage(languagePanel.getCurrentLanguage());
                }

                _parentWindow.update();
                _parentWindow.dispose();
            }
        });

        return button;
    }

    public void configure() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    public ButtonPanel(
        AbstractWindow parentWindow,
        LanguagePanel languagePanel,
        DataPanel dataPanel) {

        _parentWindow = parentWindow;
        _languageServices = ServicesLanguage.getInstance();
        _configs = AppConfigurations.getInstance();

        configure();

        JButton cancel = new JButton(_languageServices.getTranslation(Labels.CANCEL));
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                _parentWindow.dispose();
            }
        });

        add(Box.createHorizontalGlue());
        add(saveButton(languagePanel, dataPanel));
        add(Box.createRigidArea(new Dimension(10, 0)));
        add(cancel);
    }
}
