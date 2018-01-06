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
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Box;

import rodrigorar.utils.Constants.Labels;
import rodrigorar.domain.services.ServicesFactory;
import rodrigorar.domain.services.ServicesLanguage;
import rodrigorar.ui.AbstractWindow;
import rodrigorar.ui.settingswindow.DataPanel;
import rodrigorar.ui.settingswindow.LanguagePanel;
import rodrigorar.ui.settingswindow.ButtonPanel;

public class SettingsWindow
extends
AbstractWindow {
	private static final long serialVersionUID = 5040382923060248409L;
	private AbstractWindow _parentWindow;

	private ServicesLanguage _servicesLanguage;

	private DataPanel _dataPanel;
	private LanguagePanel _languagePanel;
	private ButtonPanel _buttonPanel;

    public SettingsWindow(AbstractWindow parentWindow) {
		_parentWindow = parentWindow;

        _servicesLanguage = ServicesFactory.getInstance().getLanguageServices();

        initUI();
    }

    @Override
    public void update() {
        _parentWindow.update();
    }

    private JPanel buildWindow() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		_dataPanel = new DataPanel();
		_languagePanel = new LanguagePanel();
		_buttonPanel = new ButtonPanel(this,_languagePanel,_dataPanel);

        panel.add(_dataPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(_languagePanel);
        panel.add(Box.createVerticalGlue());
        panel.add(_buttonPanel);

        return panel;
    }

    private void initUI() {
        add(buildWindow());
        setTitle(_servicesLanguage.getTranslation(Labels.SETTINGS));
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
