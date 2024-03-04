/*
 * Copyright 2023-2024 Wafer EX
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

#version 460 core
layout (location = 0) in vec2 position;
layout (location = 1) in vec2 uv;
layout (location = 2) in vec4 color;
layout (location = 3) in float isIgnoreSprite;

uniform mat4 projectionMatrix;

out vec2 UV;
out vec4 Color;
out float IsIgnoreSprite;

void main() {
    UV = uv;
    Color = color;
    IsIgnoreSprite = isIgnoreSprite;

    gl_Position = projectionMatrix * vec4(position.x, position.y, 0.0, 1.0);
}