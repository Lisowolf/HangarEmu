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

package things.ui;

import things.ui.input.HangarKeyListener;
import things.ui.components.HangarLabel;
import things.ui.components.HangarMenuBar;
import things.ui.components.HangarPanel;

import javax.swing.*;
import java.awt.*;

public class HangarFrame extends JFrame {
    private static HangarFrame instance;
    private HangarPanel hangarPanel;
    private HangarLabel hangarLabel;

    private HangarFrame() {
        this.setTitle("Hangar Emulator");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setJMenuBar(new HangarMenuBar());
    }

    public static HangarFrame getInstance() {
        if (instance == null) {
            instance = new HangarFrame();
        }
        return instance;
    }

    public HangarLabel getHangarLabel() {
        return hangarLabel;
    }

    public void setHangarLabel(HangarLabel hangarLabel) {
        this.hangarLabel = hangarLabel;
        this.add(hangarLabel);
        this.pack();
        this.revalidate();
    }

    public HangarPanel getHangarPanel() {
        return hangarPanel;
    }

    public void setHangarPanel(HangarPanel hangarPanel) {
        this.hangarPanel = hangarPanel;
        if (hangarLabel != null) {
            hangarPanel.setPreferredSize(hangarLabel.getSize());
            this.remove(hangarLabel);
        }
        else {
            hangarPanel.setPreferredSize(new Dimension(360, 360));
        }

        this.addKeyListener(new HangarKeyListener(hangarPanel));
        this.setJMenuBar(new HangarMenuBar());
        this.add(hangarPanel);
        this.pack();
        this.revalidate();
    }
}