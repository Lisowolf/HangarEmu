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

import javax.microedition.lcdui.Displayable;
import javax.swing.*;
import java.awt.*;

public class HangarViewportCommands extends JScrollPane {
    public HangarViewportCommands(Displayable displayable) {
        super(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        var panel = new JPanel(new GridBagLayout());
        var commands = displayable.getCommands();
        var constraints = new GridBagConstraints();

        panel.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 2));
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        constraints.ipady = 12;
        constraints.insets.set(4, 2, 4, 2);

        for (int i = 0; i < commands.size(); i++) {
            var command = commands.get(i);
            var button = new JButton(commands.get(i).getLabel());

            button.addActionListener(e -> displayable.getCommandListener().commandAction(command, displayable));
            constraints.gridx = i;
            panel.add(button, constraints);
        }
        this.setViewportView(panel);
    }
}