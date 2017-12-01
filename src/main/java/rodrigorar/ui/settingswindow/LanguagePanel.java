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
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.Box;

import rodrigorar.domain.services.ServicesLanguage;
import rodrigorar.utils.Constants.Labels;
import rodrigorar.ui.AbstractPanel;

public class LanguagePanel
extends
AbstractPanel {
    public static final long serialVersionUID = 1L;

    private ServicesLanguage _languageServices;

    private String _currentLanguage;

    @Override
    public void update() {
        // TODO
    }

    public String getCurrentLanguage() {
        return _currentLanguage;
    }

    @SuppressWarnings("all")
    private JComboBox<String> languageOptions() {
        JComboBox<String> languages =
                new JComboBox(_languageServices.getSupportedLanguages().toArray());

        System.out.println("Language: " + _languageServices.getActiveLanguage().getSimpleName());
        languages.setMaximumSize(new Dimension(2000, 30));
        languages.setSelectedItem(_languageServices.getActiveLanguage().getSimpleName());
        languages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("Language: " + _currentLanguage);
                _currentLanguage = String.valueOf(languages.getSelectedItem());
            }
        });

        return languages;
    }

    private JLabel languageLabel() {
        System.out.println(Labels.LANGUAGE);
        System.out.println(_languageServices.getTranslation(Labels.LANGUAGE));
        return new JLabel(_languageServices.getTranslation(Labels.LANGUAGE));
    }

    public void configure() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    public LanguagePanel() {
        _languageServices = ServicesLanguage.getInstance();

        configure();

        add(languageLabel());
        add(Box.createRigidArea(new Dimension(10, 0)));
        add(languageOptions());
    }
}
