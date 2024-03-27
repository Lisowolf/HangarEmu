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

package javax.microedition.sensor;

public final class SensorManager {
    public static void addSensorListener(SensorListener listener, SensorInfo info) {
        // TODO: write method logic
    }

    public static void addSensorListener(SensorListener listener, String quantity) {
        // TODO: write method logic
    }

    public static SensorInfo[] findSensors(String quantity, String contextType) {
        // TODO: write method logic
        return new SensorInfo[]{};
    }

    public static SensorInfo[] findSensors(String url) {
        if (url == null) {
            throw new NullPointerException();
        }
        // TODO: write method logic
        return new SensorInfo[]{};
    }

    public static void removeSensorListener(SensorListener listener) {
        if (listener == null) {
            throw new NullPointerException();
        }
        // TODO: write method logic
    }
}