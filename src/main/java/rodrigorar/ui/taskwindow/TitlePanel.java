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
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.Box;
import javax.swing.BoxLayout;

import rodrigorar.domain.services.ServicesFactory;
import rodrigorar.domain.services.ServicesLanguage;
import rodrigorar.utils.Constants.Labels;
import rodrigorar.ui.AbstractPanel;

public class TitlePanel
extends
AbstractPanel {
    public static final long serialVersionUID = 1L;

    private ServicesLanguage _languageServices;

    private JTextArea _title;

    @Override
    public void update() {
        // TODO
    }

    private JLabel titleLabel() {
        return new JLabel(_languageServices.getTranslation(Labels.TITLE));
    }

    private JScrollPane title(String text, boolean editable) {
        _title = new JTextArea();

        _title.setLineWrap(true);
        _title.setWrapStyleWord(true);
        _title.setEnabled(editable);
        _title.setDisabledTextColor(Color.BLACK);

        if (text != null && !text.equals("")) {
            _title.setText(text);
        }

        JScrollPane scrollPane = new JScrollPane(_title);
        scrollPane.setMaximumSize(new Dimension(2000, 25));

        return scrollPane;
    }

    public String getText() {
        return _title.getText().trim();
    }

    public void enableEditing() {
        _title.setEnabled(true);
    }

    public void disableEditing() {
        _title.setEnabled(false);
    }

    public boolean isEditable() {
        return _title.isEnabled();
    }

    private void configure() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    public TitlePanel(String text, boolean editable) {
        _languageServices = ServicesFactory.getInstance().getLanguageServices();

        configure();

        add(titleLabel());
        add(Box.createRigidArea(new Dimension(59, 0)));
        add(title(text, editable));
    }

}
