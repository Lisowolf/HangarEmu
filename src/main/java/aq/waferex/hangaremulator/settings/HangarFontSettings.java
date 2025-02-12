/*
 * Copyright 2024 Wafer EX
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

package aq.waferex.hangaremulator.settings;

public class HangarFontSettings {
    private int smallSize = 12;
    private int mediumSize = 14;
    private int largeSize = 16;

    public HangarFontSettings() { }

    public int getSmallSize() {
        return smallSize;
    }

    public void setSmallSize(int size) {
        smallSize = size;
    }

    public int getMediumSize() {
        return mediumSize;
    }

    public void setMediumSize(int size) {
        mediumSize = size;
    }

    public int getLargeSize() {
        return largeSize;
    }

    public void setLargeSize(int size) {
        largeSize = size;
    }
}