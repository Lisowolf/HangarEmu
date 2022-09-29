/*
 * Copyright 2022 Kirill Lomakin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package things.ui.components;

import things.HangarAudio;
import things.HangarState;
import things.MIDletLoader;
import things.enums.KeyboardTypes;
import things.enums.ScalingModes;
import things.ui.dialogs.HangarJarChooser;
import things.ui.dialogs.HangarSf2Chooser;
import things.ui.frames.HangarMainFrame;
import things.utils.HangarGamePanelUtils;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class HangarMenuBar extends JMenuBar {
    // TODO: refactor this class
    public HangarMenuBar() {
        addMIDletMenu();
        addOptionsMenu();
        addHelpMenu();
    }

    private void addMIDletMenu() {
        var midletMenu = new JMenu("MIDlet");
        var loadMenuItem = new JMenuItem("Load MIDlet");
        var restartMenuItem = new JMenuItem("Restart");
        var pauseMenuItem = new JMenuItem("Call pauseApp()");
        var exitMenuItem = new JMenuItem("Exit");

        loadMenuItem.addActionListener(e -> {
            var fileChooser = new HangarJarChooser();
            fileChooser.showDialog(null, "Select MIDlet");

            SwingUtilities.invokeLater(() -> {
                var selectedFile = fileChooser.getSelectedFile();
                if (selectedFile != null) {
                    if (!MIDletLoader.isLoaded()) {
                        MIDletLoader.loadMIDlet(fileChooser.getSelectedFile().getAbsolutePath());
                        MIDletLoader.startLoadedMIDlet();
                    }
                    else {
                        HangarState.restartApp(fileChooser.getSelectedFile().getAbsolutePath());
                    }
                }
            });
        });
        restartMenuItem.addActionListener(event -> HangarState.restartApp(MIDletLoader.getLastLoadedPath()));
        pauseMenuItem.addActionListener(event -> {
            var lastLoaded = MIDletLoader.getLastLoaded();
            if (lastLoaded != null) {
                lastLoaded.pauseApp();
            }
        });
        exitMenuItem.addActionListener(event -> System.exit(0));

        midletMenu.add(loadMenuItem);
        midletMenu.add(new JSeparator());
        midletMenu.add(pauseMenuItem);
        midletMenu.add(restartMenuItem);
        midletMenu.add(new JSeparator());
        midletMenu.add(exitMenuItem);
        this.add(midletMenu);
    }

    private void addOptionsMenu() {
        var optionsMenu = new JMenu("Options");
        var canvasClearingCheckBox = new JCheckBoxMenuItem("Canvas clearing", HangarState.getConfiguration().getCanvasClearing());
        var antiAliasingCheckBox = new JCheckBoxMenuItem("Anti-aliasing", HangarState.getConfiguration().getAntiAliasing());
        var frameRatePopupMenu = new JMenu("Frame rate");
        var scalingModePopupMenu = new JMenu("Scaling mode");
        var resolutionPopupMenu = new JMenu("Resolution");
        var loadSoundbankItem = new JMenuItem("Load soundbank");
        var clearSoundBankItem = new JMenuItem("Clear soundbank");
        var allowResizingCheckBox = new JCheckBoxMenuItem("Allow window resizing", false);
        var keyboardPopupMenu = new JMenu("Keyboard");

        var radio15FPS = new JRadioButtonMenuItem("15 FPS", HangarState.getConfiguration().getFrameRate() == 15);
        var radio30FPS = new JRadioButtonMenuItem("30 FPS", HangarState.getConfiguration().getFrameRate() == 30);
        var radio60FPS = new JRadioButtonMenuItem("60 FPS", HangarState.getConfiguration().getFrameRate() == 60);
        var radioUnlimitedFPS = new JRadioButtonMenuItem("Unlimited", HangarState.getConfiguration().getFrameRate() == -1);
        var frameRateRadioGroup = new ButtonGroup();
        frameRateRadioGroup.add(radio15FPS);
        frameRateRadioGroup.add(radio30FPS);
        frameRateRadioGroup.add(radio60FPS);
        frameRateRadioGroup.add(radioUnlimitedFPS);

        var radioScalingModeNone = new JRadioButtonMenuItem("None", HangarState.getConfiguration().getScalingMode() == ScalingModes.None);
        var radioScalingModeContain = new JRadioButtonMenuItem("Contain", HangarState.getConfiguration().getScalingMode() == ScalingModes.Contain);
        var radioScalingModeChangeResolution = new JRadioButtonMenuItem("Change resolution", HangarState.getConfiguration().getScalingMode() == ScalingModes.ChangeResolution);
        var scalingModeRadioGroup = new ButtonGroup();
        scalingModeRadioGroup.add(radioScalingModeNone);
        scalingModeRadioGroup.add(radioScalingModeContain);
        scalingModeRadioGroup.add(radioScalingModeChangeResolution);

        var radioResolution128x128 = new JRadioButtonMenuItem("128x128", false);
        var radioResolution128x160 = new JRadioButtonMenuItem("128x160", false);
        var radioResolution176x220 = new JRadioButtonMenuItem("176x220", false);
        var radioResolution240x320 = new JRadioButtonMenuItem("240x320", true);
        var resolutionRadioGroup = new ButtonGroup();
        resolutionRadioGroup.add(radioResolution128x128);
        resolutionRadioGroup.add(radioResolution128x160);
        resolutionRadioGroup.add(radioResolution176x220);
        resolutionRadioGroup.add(radioResolution240x320);
        if (HangarState.getConfiguration().getScalingMode() == ScalingModes.ChangeResolution) {
            resolutionRadioGroup.clearSelection();
            resolutionPopupMenu.setEnabled(false);
        }

        var radioDefaultKeyboard = new JRadioButtonMenuItem("Default", HangarState.getConfiguration().getKeyboardType() == KeyboardTypes.Default);
        var radioNokiaKeyboard = new JRadioButtonMenuItem("Nokia", HangarState.getConfiguration().getKeyboardType() == KeyboardTypes.Nokia);
        var keyboardRadioGroup = new ButtonGroup();
        keyboardRadioGroup.add(radioDefaultKeyboard);
        keyboardRadioGroup.add(radioNokiaKeyboard);

        canvasClearingCheckBox.addItemListener(e -> HangarState.getConfiguration().setCanvasClearing(!HangarState.getConfiguration().getCanvasClearing()));
        antiAliasingCheckBox.addItemListener(e -> HangarState.getConfiguration().setAntiAliasing(!HangarState.getConfiguration().getAntiAliasing()));

        radio15FPS.addItemListener(e -> HangarState.getConfiguration().setFrameRate(15));
        radio30FPS.addItemListener(e -> HangarState.getConfiguration().setFrameRate(30));
        radio60FPS.addItemListener(e -> HangarState.getConfiguration().setFrameRate(60));
        radioUnlimitedFPS.addItemListener(e -> HangarState.getConfiguration().setFrameRate(-1));

        radioScalingModeNone.addItemListener(e -> {
            HangarState.getConfiguration().setScalingMode(ScalingModes.None);
            resolutionPopupMenu.setEnabled(true);
        });
        radioScalingModeContain.addItemListener(e -> {
            HangarState.getConfiguration().setScalingMode(ScalingModes.Contain);
            resolutionPopupMenu.setEnabled(true);
        });
        radioScalingModeChangeResolution.addItemListener(e -> {
            HangarState.getConfiguration().setScalingMode(ScalingModes.ChangeResolution);
            resolutionRadioGroup.clearSelection();
            resolutionPopupMenu.setEnabled(false);
        });

        radioResolution128x128.addItemListener(e -> {
            if (radioResolution128x128.isSelected()) {
                HangarGamePanelUtils.fitBufferToNewResolution(HangarMainFrame.getInstance().getGamePanel(), new Dimension(128, 128));
            }
        });
        radioResolution128x160.addItemListener(e -> {
            if (radioResolution128x160.isSelected()) {
                HangarGamePanelUtils.fitBufferToNewResolution(HangarMainFrame.getInstance().getGamePanel(), new Dimension(128, 160));
            }
        });
        radioResolution176x220.addItemListener(e -> {
            if (radioResolution176x220.isSelected()) {
                HangarGamePanelUtils.fitBufferToNewResolution(HangarMainFrame.getInstance().getGamePanel(), new Dimension(176, 220));
            }
        });
        radioResolution240x320.addItemListener(e -> {
            if (radioResolution240x320.isSelected()) {
                HangarGamePanelUtils.fitBufferToNewResolution(HangarMainFrame.getInstance().getGamePanel(), new Dimension(240, 320));
            }
        });

        allowResizingCheckBox.addItemListener(e -> HangarState.getConfiguration().setWindowResizing(allowResizingCheckBox.getState()));

        loadSoundbankItem.addActionListener(e -> {
            var fileChooser = new HangarSf2Chooser();
            fileChooser.showDialog(null, "Select soundbank");

            SwingUtilities.invokeLater(() -> {
                var selectedFile = fileChooser.getSelectedFile();
                if (selectedFile != null) {
                    HangarAudio.loadSoundbank(selectedFile);
                }
            });
        });
        clearSoundBankItem.addActionListener(e -> HangarAudio.setSoundbank(null));

        radioDefaultKeyboard.addItemListener(e -> {
            if (radioDefaultKeyboard.isSelected()) {
                HangarState.getConfiguration().setKeyboardType(KeyboardTypes.Default);
            }
        });
        radioNokiaKeyboard.addItemListener(e -> {
            if (radioNokiaKeyboard.isSelected()) {
                HangarState.getConfiguration().setKeyboardType(KeyboardTypes.Nokia);
            }
        });

        keyboardPopupMenu.add(radioDefaultKeyboard);
        keyboardPopupMenu.add(radioNokiaKeyboard);
        frameRatePopupMenu.add(radio15FPS);
        frameRatePopupMenu.add(radio30FPS);
        frameRatePopupMenu.add(radio60FPS);
        frameRatePopupMenu.add(radioUnlimitedFPS);
        scalingModePopupMenu.add(radioScalingModeNone);
        scalingModePopupMenu.add(radioScalingModeContain);
        scalingModePopupMenu.add(radioScalingModeChangeResolution);
        resolutionPopupMenu.add(radioResolution128x128);
        resolutionPopupMenu.add(radioResolution128x160);
        resolutionPopupMenu.add(radioResolution176x220);
        resolutionPopupMenu.add(radioResolution240x320);

        optionsMenu.add(canvasClearingCheckBox);
        optionsMenu.add(antiAliasingCheckBox);
        optionsMenu.add(frameRatePopupMenu);
        optionsMenu.add(scalingModePopupMenu);
        optionsMenu.add(resolutionPopupMenu);
        optionsMenu.add(new JSeparator());
        optionsMenu.add(loadSoundbankItem);
        optionsMenu.add(clearSoundBankItem);
        optionsMenu.add(new JSeparator());
        optionsMenu.add(allowResizingCheckBox);
        optionsMenu.add(new JSeparator());
        optionsMenu.add(keyboardPopupMenu);
        this.add(optionsMenu);
    }

    private void addHelpMenu() {
        var helpMenu = new JMenu("Help");
        var githubLinkMenuItem = new JMenuItem("GitHub page");
        var showAboutMenuItem = new JMenuItem("About");

        githubLinkMenuItem.addActionListener(event -> {
            try {
                var githubUri = new URL(System.getProperty("hangaremulator.github")).toURI();
                Desktop.getDesktop().browse(githubUri);
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        showAboutMenuItem.addActionListener(event -> JOptionPane.showMessageDialog(HangarMainFrame.getInstance(),
                String.format("""
                        Hangar Emulator
                        Version: %s
                        Author: %s""",
                        System.getProperty("hangaremulator.version"),
                        System.getProperty("hangaremulator.author")),
                "About",
                JOptionPane.PLAIN_MESSAGE));

        helpMenu.add(githubLinkMenuItem);
        helpMenu.add(showAboutMenuItem);
        this.add(helpMenu);
    }
}