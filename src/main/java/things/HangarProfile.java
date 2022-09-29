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

package things;

import things.enums.KeyboardTypes;
import things.enums.ScalingModes;
import things.ui.components.HangarGamePanel;
import things.ui.components.HangarMainPanel;
import things.ui.frames.HangarMainFrame;
import things.ui.listeners.HangarKeyListener;
import things.utils.HangarGamePanelUtils;

import java.awt.*;

public class HangarProfile {
    private KeyboardTypes keyboardType = KeyboardTypes.Nokia;
    private ScalingModes scalingMode = ScalingModes.None;
    private Dimension resolution = new Dimension(240, 320);
    private int frameRate = 60;
    private boolean canvasClearing = false;
    private boolean antiAliasing = false;
    private boolean windowResizing = false;

    public KeyboardTypes getKeyboardType() {
        return keyboardType;
    }

    public void setKeyboardType(KeyboardTypes keyboardType) {
        this.keyboardType = keyboardType;
        var gamePanel = HangarMainFrame.getInstance().getGamePanel();
        var keyListeners = gamePanel.getKeyListeners();

        if (keyListeners.length > 0) {
            for (var keyListener : keyListeners) {
                if (keyListener instanceof HangarKeyListener hangarKeyListener) {
                    hangarKeyListener.getPressedKeys().clear();
                }
            }
        }
    }

    public ScalingModes getScalingMode() {
        return scalingMode;
    }

    public void setScalingMode(ScalingModes scalingMode) {
        this.scalingMode = scalingMode;

        var container = HangarMainFrame.getInstance().getContentPane();
        for (var component : container.getComponents()) {
            if (component instanceof HangarGamePanel || component instanceof HangarMainPanel) {
                if (scalingMode == ScalingModes.ChangeResolution) {
                    resolution = component.getSize();
                }
                if (component instanceof HangarGamePanel gamePanel) {
                    HangarGamePanelUtils.fitBufferToResolution(gamePanel, resolution);
                }
            }
        }
    }

    public Dimension getResolution() {
        return resolution;
    }

    public void setResolution(Dimension resolution) {
        this.resolution = resolution;
        HangarGamePanelUtils.fitBufferToResolution(HangarMainFrame.getInstance().getGamePanel(), resolution);
    }

    public int getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(int frameRate) {
        this.frameRate = frameRate;
        var container = HangarMainFrame.getInstance().getContentPane();
        if (container.getComponent(0) instanceof HangarGamePanel gamePanel) {
            gamePanel.refreshSerialCallTimer();
        }
    }

    public boolean getCanvasClearing() {
        return canvasClearing;
    }

    public void setCanvasClearing(boolean canvasClearing) {
        this.canvasClearing = canvasClearing;
    }

    public boolean getAntiAliasing() {
        return antiAliasing;
    }

    public void setAntiAliasing(boolean antiAliasing) {
        this.antiAliasing = antiAliasing;
    }

    public boolean getWindowResizing() {
        return windowResizing;
    }

    public void setWindowResizing(boolean windowResizing) {
        this.windowResizing = windowResizing;
        HangarMainFrame.getInstance().setResizable(windowResizing);
    }
}