/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.minecraft.adapter.cmds;

public class CommandRegistry {
    public static final String TELEPORT_CALLBACK = "org.drools.minecraft.adapter.cmds.TeleportPlayerCommand";
    public static final String CLEAR_INVENTORY_CALLBACK = "org.drools.minecraft.adapter.cmds.ClearPlayerInventoryCommand";
    public static final String NOTIFY_VIA_CHAT_CALLBACK = "org.drools.minecraft.adapter.cmds.NotifyViaChatCommand";
    public static final String RESET_FLAG_CALLBACK = "org.drools.minecraft.adapter.cmds.ResetFlagCommand";
    public static final String SET_PLAYER_HEALTH_CALLBACK = "org.drools.minecraft.adapter.cmds.SetPlayerHealthCommand";
    public static final String SET_PLAYER_PARAM_CALLBACK = "org.drools.minecraft.adapter.cmds.SetPlayerParamCommand";
    
}
