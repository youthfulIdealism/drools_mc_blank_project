package com.example.droolsinterface;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class InitClient
{

    public static void preInit(FMLPreInitializationEvent event)
    {
        final int METADATA_APPARENTLY = 0;
        ModelResourceLocation keyResourceLocation = new ModelResourceLocation("examplemod:key", "inventory");
        ModelLoader.setCustomModelResourceLocation(InitCommon.itemKey, METADATA_APPARENTLY, keyResourceLocation);
        
        ModelResourceLocation rulerRoomResourceLocation = new ModelResourceLocation("examplemod:ruler_room", "inventory");
        ModelLoader.setCustomModelResourceLocation(InitCommon.itemRulerRoom, METADATA_APPARENTLY, rulerRoomResourceLocation);
        
    }

    public static void init(FMLInitializationEvent event)
    {

    }

    public static void postInit(FMLPostInitializationEvent event)
    {

    }
}
