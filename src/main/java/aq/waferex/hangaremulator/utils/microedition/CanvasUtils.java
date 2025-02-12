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

package aq.waferex.hangaremulator.utils.microedition;

import com.nokia.mid.ui.FullCanvas;

import javax.microedition.lcdui.Canvas;

public final class CanvasUtils {
    public static int keyCodeToGameAction(int keyCode) {
        return switch (keyCode) {
            case Canvas.KEY_NUM1, FullCanvas.KEY_SOFTKEY1 -> Canvas.GAME_A;
            case Canvas.KEY_NUM2, FullCanvas.KEY_UP_ARROW -> Canvas.UP;
            case Canvas.KEY_NUM3, FullCanvas.KEY_SOFTKEY2 -> Canvas.GAME_B;
            case Canvas.KEY_NUM4, FullCanvas.KEY_LEFT_ARROW -> Canvas.LEFT;
            case Canvas.KEY_NUM5, FullCanvas.KEY_SOFTKEY3 -> Canvas.FIRE;
            case Canvas.KEY_NUM6, FullCanvas.KEY_RIGHT_ARROW -> Canvas.RIGHT;
            case Canvas.KEY_NUM7 -> Canvas.GAME_C;
            case Canvas.KEY_NUM8, FullCanvas.KEY_DOWN_ARROW -> Canvas.DOWN;
            case Canvas.KEY_NUM9 -> Canvas.GAME_D;
            default -> keyCode;
        };
    }

    public static int gameActionToKeyCode(int gameAction) {
        return switch (gameAction) {
            case Canvas.GAME_A -> FullCanvas.KEY_SOFTKEY1;
            case Canvas.GAME_B -> FullCanvas.KEY_SOFTKEY2;
            case Canvas.UP -> FullCanvas.KEY_UP_ARROW;
            case Canvas.LEFT -> FullCanvas.KEY_LEFT_ARROW;
            case Canvas.FIRE -> FullCanvas.KEY_SOFTKEY3;
            case Canvas.RIGHT -> FullCanvas.KEY_RIGHT_ARROW;
            case Canvas.DOWN -> FullCanvas.KEY_DOWN_ARROW;
            default -> gameAction;
        };
    }
}