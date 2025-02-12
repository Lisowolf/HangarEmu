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

package aq.waferex.hangaremulator.ui.listeners;

import aq.waferex.hangaremulator.HangarKeyCodes;
import aq.waferex.hangaremulator.HangarState;
import aq.waferex.hangaremulator.utils.KeyUtils;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

public class HangarKeyListener implements KeyListener {
    private final HashSet<Integer> pressedKeys = new HashSet<>();

    public HangarKeyListener() {
        super();
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        var awtKeyCodes = HangarKeyCodes.AWT_KEYCODES_DEFAULT;
        var midletKeyCodes = HangarState.getInputSettings().getMidletKeyCodes();
        int keyCode = KeyUtils.convertKeyCode(e.getKeyCode(), awtKeyCodes, midletKeyCodes);

        if (keyCode != 0) {
            if (pressedKeys.add(keyCode)) {
                if (Display.getDisplaySingleton().getCurrent() instanceof Canvas canvas) {
                    canvas.keyPressed(keyCode);
                }
            }
            else {
                if (Display.getDisplaySingleton().getCurrent() instanceof Canvas canvas) {
                    canvas.keyRepeated(keyCode);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        var awtKeyCodes = HangarKeyCodes.AWT_KEYCODES_DEFAULT;
        var midletKeyCodes = HangarState.getInputSettings().getMidletKeyCodes();
        int keyCode = KeyUtils.convertKeyCode(e.getKeyCode(), awtKeyCodes, midletKeyCodes);

        if (keyCode != 0) {
            pressedKeys.remove(keyCode);
            if (Display.getDisplaySingleton().getCurrent() instanceof Canvas canvas) {
                canvas.keyReleased(keyCode);
            }
        }
    }
}