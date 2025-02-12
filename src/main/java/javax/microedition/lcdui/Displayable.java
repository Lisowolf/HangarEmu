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

package javax.microedition.lcdui;

import aq.waferex.hangaremulator.HangarState;

import java.util.ArrayList;

public abstract class Displayable {
    private String title;

    protected Ticker ticker;
    protected CommandListener commandListener;
    protected ArrayList<Command> commandList = new ArrayList<>();

    public ArrayList<Command> getCommands() {
        return commandList;
    }

    public CommandListener getCommandListener() {
        return commandListener;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String s) {
        title = s;
    }

    public Ticker getTicker() {
        return ticker;
    }

    public void setTicker(Ticker ticker) {
        this.ticker = ticker;
    }

    public boolean isShown() {
        return Display.getDisplaySingleton().getCurrent() == this;
    }

    public void addCommand(Command cmd) throws NullPointerException {
        if (cmd == null) {
            throw new NullPointerException();
        }
        if (!commandList.contains(cmd)) {
            commandList.add(cmd);
        }
    }

    public void removeCommand(Command cmd) {
        if (cmd != null) {
            commandList.remove(cmd);
        }
    }

    public void setCommandListener(CommandListener l) {
        commandListener = l;
    }

    public int getWidth() {
        return HangarState.getGraphicsSettings().getResolution().width;
    }

    public int getHeight() {
        return HangarState.getGraphicsSettings().getResolution().height;
    }

    public void sizeChanged(int w, int h) { }
}