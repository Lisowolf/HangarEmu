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

package com.nokia.mid.ui;

import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import things.implementations.nokia.DirectGraphicsImplementation;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import java.awt.image.BufferedImage;

public class DirectUtils {
    public static DirectGraphics getDirectGraphics(Graphics g) {
        return new DirectGraphicsImplementation(g);
    }

    public static Image createImage(byte[] imageData, int imageOffset, int imageLength) throws NotImplementedException {
        throw new NotImplementedException("createImage");
    }

    public static Image createImage(int width, int height, int ARGBcolor) {
        var awtImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        return new Image(awtImage, true);
    }
}