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

import java.io.File;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JFileChooser;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.BorderFactory;

import rodrigorar.domain.pojos.AppConfigurations;
import rodrigorar.domain.services.ServicesLanguage;
import rodrigorar.utils.Constants.Labels;
import rodrigorar.ui.AbstractPanel;

public class DataPanel
extends
AbstractPanel {
    public static final long serialVersionUID = 1L;
    private AppConfigurations _configs;
    private ServicesLanguage _languageServices;
    private String _dataDirectory;

    @Override
    public void update() {
        // TODO
    }

    public String getDataDirectory() {
        return _dataDirectory;
    }

    private void openFileChooser(JTextField directory) {
        JFrame frame = new JFrame(_languageServices.getTranslation(Labels.FILE_BROWSER));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JFileChooser fileChooser = new JFileChooser(_configs.getDataDirectory());
        frame.getContentPane().add(fileChooser, BorderLayout.CENTER);

        fileChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JFileChooser theFileChooser = (JFileChooser) event.getSource();

                String command = event.getActionCommand();
                if (command.equals(JFileChooser.APPROVE_SELECTION)) {
                    File selectedFile = theFileChooser.getSelectedFile();
                    directory.setText(selectedFile.getParent() + "/" + selectedFile.getName());
                    _dataDirectory = directory.getText().trim();
                }

                frame.dispose();
            }
        });

        frame.pack();
        frame.setVisible(true);
    }

    private JButton fileChooser(JTextField directory) {
        JButton fileChooser =
            new JButton(_languageServices.getTranslation(Labels.CHOOSE_FILE));

        fileChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                openFileChooser(directory);
            }
        });

        return fileChooser;
    }

    private JTextField directory() {
        JTextField directory = new JTextField();

        directory.setText(_configs.getDataDirectory());
        directory.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        directory.setMaximumSize(new Dimension(2000, 25));

        return directory;
    }

    private void configure() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    public DataPanel() {
        _configs = AppConfigurations.getInstance();
        _languageServices = ServicesLanguage.getInstance();

        configure();

        JTextField directory = directory();
        JButton fileChooser = fileChooser(directory);

        add(directory);
        add(Box.createRigidArea(new Dimension(10, 0)));
        add(fileChooser);
    }
}
