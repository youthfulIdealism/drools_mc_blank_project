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
package org.drools.minecraft.adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.drools.game.core.api.GameSession;
import org.drools.game.core.*;
import org.drools.game.core.api.GameConfiguration;
import org.drools.game.core.api.Command;
import org.drools.game.core.api.Context;
import org.drools.game.core.api.PlayerConfiguration;
import org.drools.game.model.api.Player;
import org.drools.game.model.impl.base.BasePlayerImpl;
import org.kie.api.runtime.rule.FactHandle;

public class NewAdapter
{

    private static final NewAdapter INSTANCE = new NewAdapter();

    private int throttle = 0;
    private final int maxThrottle = 20;

    private GameSession game;

    public NewAdapter()
    {
        game = new GameSessionImpl();
        game.setExecutor(new CommandExecutorImpl());
        game.setMessageService(new GameMessageServiceImpl());
        game.setCallbackService(new GameCallbackServiceImpl());
        
        //
        //TODO: register your commands here/
        //
        CommandRegistry.set("SOME_COMMAND", "org.drools.minecraft.adapter.cmds.SomeCommand");
        bootstrapWorld();

    }

    private void bootstrapWorld()
    {
        List initFacts = new ArrayList();
        
        //
        //TODO: set up your world here
        //
        
        GameConfiguration config = new BaseGameConfigurationImpl(initFacts, "");
        game.bootstrap(config);
    }

    public static NewAdapter getInstance()
    {
        return INSTANCE;
    }

    private void update(World world) throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        //
        //TODO: Update your model here...
        //
        
        for (String player : game.getPlayers())
        {
            //
            //...and here.
            //
            
        }
        dealWithCallbacks(world);
    }

    /**
     * Execute any updates that occur when the game ticks.
     *
     * @param event
     */
    @SubscribeEvent
    public void onServerTick(TickEvent.WorldTickEvent event) throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        if (!event.world.isRemote)
        {
            if (event.phase == TickEvent.WorldTickEvent.Phase.START)
            {
                return;
            }

            throttle++;
            if (throttle >= maxThrottle)
            {
                throttle = 0;

                //for simplicity's sake, this locks the adapter into only working
                //in the default dimension. Rules will not work in the nether or end.
                //We should change this at some point.
                if (event.world.provider.getDimension() == 0)
                {
                    update(event.world);
                }
            }
        }
    }

    /**
     * Set up player session, inventory, etc.
     *
     * @param event
     */
    @SubscribeEvent
    public void onPlayerJoin(EntityJoinWorldEvent event)
    {
        if (!event.getWorld().isRemote)
        {
            if (event.getEntity() instanceof EntityPlayer)
            {
                PlayerConfiguration playerConfig = new BasePlayerConfigurationImpl(null);
                String name = event.getEntity().getDisplayName().getUnformattedText();
                Player player = new BasePlayerImpl(name);
                game.join(player, playerConfig);
            }
        }
    }

    /**
     * When the player dies, remove him from any occupied rooms.
     *
     * @param event
     */
    @SubscribeEvent
    public void onPlayerDie(LivingDeathEvent event)
    {
        if (!event.getEntity().worldObj.isRemote)
        {
            if (event.getEntity() instanceof EntityPlayer)
            {
                String name = event.getEntity().getDisplayName().getUnformattedText();
                game.drop(game.getPlayerByName(name));
            }
        }
    }

    private void dealWithCallbacks(World world)
    {
        Context callbackCtx = new ContextImpl();
        callbackCtx.getData().put("world", world);
        Queue<Command> callbacks = game.getCallbacks();
        while (callbacks.peek() != null)
        {
            Command cmd = callbacks.poll();
            cmd.execute(callbackCtx);
        }
    }
}
