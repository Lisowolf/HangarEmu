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

import things.HangarState;
import things.enums.ScalingModes;
import things.ui.listeners.HangarMouseListener;
import things.utils.HangarCanvasUtils;
import things.utils.microedition.ImageUtils;

import javax.microedition.lcdui.Canvas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class HangarCanvasWrapper extends JPanel {
    private final Canvas canvas;
    private BufferedImage buffer;
    private final Point bufferPosition = new Point(0, 0);
    private double bufferScaleFactor = 1.0;
    private Dimension bufferScale = HangarState.getProfileManager().getCurrent().getResolution();
    private Runnable callSerially;
    private Timer serialCallTimer = new Timer();

    public HangarCanvasWrapper(Canvas canvas) {
        super();
        this.canvas = canvas;

        var profile = HangarState.getProfileManager().getCurrent();
        var mouseListener = new HangarMouseListener(this);
        var resolution = profile.getResolution();

        this.setBuffer(ImageUtils.createCompatibleImage(resolution.width, resolution.height));
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if (profile.getScalingMode() == ScalingModes.ChangeResolution) {
                    profile.setResolution(HangarCanvasWrapper.this.getSize());
                    HangarCanvasUtils.fitBufferToResolution(HangarCanvasWrapper.this, HangarCanvasWrapper.this.getSize());
                }
                HangarCanvasWrapper.this.updateBufferTransformations();
            }
        });
        this.refreshSerialCallTimer();
    }

    public BufferedImage getBuffer() {
        return buffer;
    }

    public void setBuffer(BufferedImage buffer) {
        this.buffer = buffer;
    }

    public Point getBufferPosition() {
        return bufferPosition;
    }

    public double getBufferScaleFactor() {
        return bufferScaleFactor;
    }

    public void setCallSerially(Runnable runnable) {
        this.callSerially = runnable;
    }

    public void updateBufferTransformations() {
        bufferScaleFactor = HangarCanvasUtils.getBufferScaleFactor(this, buffer);

        int newWidth = (int) (buffer.getWidth() * bufferScaleFactor);
        int newHeight = (int) (buffer.getHeight() * bufferScaleFactor);
        bufferScale = new Dimension(newWidth, newHeight);

        bufferPosition.x = getWidth() / 2 - bufferScale.width / 2;
        bufferPosition.y = getHeight() / 2 - bufferScale.height / 2;
        this.repaint();
    }

    public void refreshSerialCallTimer() {
        serialCallTimer.cancel();
        serialCallTimer.purge();
        serialCallTimer = new Timer();

        var frameRateInMilliseconds = HangarState.frameRateInMilliseconds();
        if (frameRateInMilliseconds >= 0) {
            serialCallTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (callSerially != null) {
                        callSerially.run();
                    }
                }
            }, 0, frameRateInMilliseconds);
        }
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (buffer != null) {
            var graphicsWithHints = HangarState.applyRenderingHints(buffer.getGraphics());
            var profile = HangarState.getProfileManager().getCurrent();

            if (profile.getCanvasClearing()) {
                graphicsWithHints.clearRect(0, 0, buffer.getWidth(), buffer.getHeight());
            }
            canvas.paint(new javax.microedition.lcdui.Graphics(graphicsWithHints, buffer));
            graphics.drawImage(buffer, bufferPosition.x, bufferPosition.y, bufferScale.width, bufferScale.height, null);
        }
    }
}