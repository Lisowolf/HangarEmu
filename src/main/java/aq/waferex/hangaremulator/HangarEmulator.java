/*
 * Copyright 2022-2024 Wafer EX
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

package aq.waferex.hangaremulator;

import aq.waferex.hangaremulator.utils.ArgsUtils;
import com.formdev.flatlaf.FlatDarkLaf;
import aq.waferex.hangaremulator.ui.frames.HangarMainFrame;
import aq.waferex.hangaremulator.utils.SystemUtils;

public class HangarEmulator {
    public static void main(String[] args) {
        try {
            System.setProperty("sun.java2d.opengl", "true");
            FlatDarkLaf.setup();

            HangarState.setProperties(SystemUtils.getAppProperties());

            var mainFrame = new HangarMainFrame();
            HangarState.setMainFrame(mainFrame);

            ArgsUtils.initSettingsFromArgs(args);

            mainFrame.setVisible(true);
            mainFrame.setLocationRelativeTo(null);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}