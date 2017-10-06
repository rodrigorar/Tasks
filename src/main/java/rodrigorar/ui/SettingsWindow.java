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

package rodrigorar.ui;

import java.io.File;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFileChooser;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Box;

import rodrigorar.utils.Constants.Labels;
import rodrigorar.entities.AppConfigurations;

public class SettingsWindow
extends
JFrame {
    private MainWindow _parentWindow;
    private JTextField _directory;

    public SettingsWindow(MainWindow parentWindow) {
        _parentWindow = parentWindow;
        initUI();
    }

    private void openFileChooser() {
        JFrame frame = new JFrame(Labels.FILE_BROWSER);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JFileChooser fileChooser = new JFileChooser(".");
        frame.getContentPane().add(fileChooser, BorderLayout.CENTER);

        fileChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JFileChooser theFileChooser = (JFileChooser) event.getSource();

                String command = event.getActionCommand();
                if (command.equals(JFileChooser.APPROVE_SELECTION)) {
                    File selectedFile = theFileChooser.getSelectedFile();
                    _directory.setText(selectedFile.getParent() + "/" + selectedFile.getName());
                }

                frame.dispose();
            }
        });

        frame.pack();
        frame.setVisible(true);
    }

    private JPanel buildDataPanel() {
        AppConfigurations configurations = AppConfigurations.getInstance();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        _directory = new JTextField();
        _directory.setText(configurations.getDataDirectory());
        _directory.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        _directory.setMaximumSize(new Dimension(2000, 25));

        JButton fileChooser = new JButton(Labels.CHOOSE_FILE);
        fileChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                openFileChooser();
            }
        });

        panel.add(_directory);
        panel.add(Box.createRigidArea(new Dimension(10, 0)));
        panel.add(fileChooser);

        return panel;
    }

    private JPanel buildBottomPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        JButton save = new JButton(Labels.SAVE);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                AppConfigurations configurations = AppConfigurations.getInstance();
                String dataDirectory = _directory.getText().trim();
                configurations.setDataDirectory(dataDirectory);
                dispose();
            }
        });

        JButton cancel = new JButton(Labels.CANCEL);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                dispose();
            }
        });

        panel.add(Box.createHorizontalGlue());
        panel.add(save);
        panel.add(Box.createRigidArea(new Dimension(10, 0)));
        panel.add(cancel);

        return panel;
    }

    private JPanel buildWindow() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(buildDataPanel());
        panel.add(Box.createVerticalGlue());
        panel.add(buildBottomPanel());

        return panel;
    }

    private void initUI() {
        add(buildWindow());
        setTitle(Labels.SETTINGS);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
