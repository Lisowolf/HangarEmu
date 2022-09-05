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

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

public class HangarSF2Chooser extends JFileChooser {
    public HangarSF2Chooser() {
        super();

        this.setAcceptAllFileFilterUsed(false);
        this.addChoosableFileFilter(new FileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.isFile()) {
                    var filename = file.getName().toLowerCase();
                    return filename.endsWith(".sf2");
                }
                else return file.isDirectory();
            }

            @Override
            public String getDescription() {
                return "Soundbank (*.sf2)";
            }
        });
    }
}