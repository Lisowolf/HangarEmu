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

public final class ObjectCondition implements Condition {
    private final Object limit;

    public ObjectCondition(Object limit) {
        this.limit = limit;
    }

    public final Object getLimit() {
        return limit;
    }

    public boolean isMet(double value) {
        return false;
    }

    public boolean isMet(Object value) {
        return value == limit;
    }
}