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
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.Box;

import rodrigorar.domain.services.ServicesLanguage;
import rodrigorar.utils.Constants.Labels;

public class DescriptionPanel
extends
JPanel {
    public static final long serialVersionUID = 1L;
    private ServicesLanguage _languageServices;
    private JTextArea _description;

    private JLabel descriptionLabel() {
        return new JLabel(_languageServices.getTranslation(Labels.DESCRIPTION));
    }

    private JScrollPane description(String text, boolean editable) {
        _description = new JTextArea();
        _description.setLineWrap(true);
        _description.setWrapStyleWord(true);
        _description.setRows(7);
        _description.setEnabled(editable);
        _description.setMaximumSize(new Dimension(2000, 140));
        _description.setDisabledTextColor(Color.BLACK);

        if (text != null && !text.equals("")) {
            _description.setText(text);
        }

        JScrollPane scrollPane = new JScrollPane(_description);
        scrollPane.setMaximumSize(new Dimension(2000, 140));

        return scrollPane;
    }

    public String getText() {
        return _description.getText().trim();
    }

    public void enableEditing() {
        _description.setEnabled(true);
    }

    public void disableEditing() {
        _description.setEnabled(false);
    }

    public boolean isEditable() {
        return _description.isEnabled();
    }

    private void configure() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    public DescriptionPanel(String text, boolean editable) {
        _languageServices = ServicesLanguage.getInstance();

        configure();

        add(descriptionLabel());
        add(Box.createRigidArea(new Dimension(10, 0)));
        add(description(text, editable));
    }
}
