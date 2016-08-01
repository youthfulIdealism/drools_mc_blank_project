package com.example.droolsinterface;

import com.example.examplemod.items.ItemKey;
import com.example.examplemod.items.ItemRulerRoom;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.drools.minecraft.adapter.NewAdapter;

public class InitCommon
{
    public static ItemKey itemKey;
    public static ItemRulerRoom itemRulerRoom;
    
    
    
    public static void preInit(FMLPreInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register( NewAdapter.getInstance() );
        
        itemKey = (ItemKey)(new ItemKey().setUnlocalizedName("key"));
        GameRegistry.registerItem(itemKey, "key");
        
        itemRulerRoom = (ItemRulerRoom)(new ItemRulerRoom().setUnlocalizedName("ruler_room"));
        GameRegistry.registerItem(itemRulerRoom, "ruler_room");
        
        //Autoload the world in the dev environment.
        Path runPath = Paths.get("");
        String intoModDirectory = runPath.toAbsolutePath().toString().replace("\\run", "\\worlds");
        runPath = Paths.get("saves\\");
        
        File woldFolder = new File(intoModDirectory);
        
        if(woldFolder.exists())
        {
            try
            {
                for(File worldFile : woldFolder.listFiles())
                {
                    recursiveCopy(worldFile.getAbsolutePath(), runPath.toAbsolutePath().toString() + "\\" + worldFile.getName());
                }
            }catch(FileAlreadyExistsException aaex)
            {
                System.out.println("Initialization error: unable to copy worlds due to FileAlreadyExiststException.");
                System.out.println("You can probably ignore this error..");
            }
            catch (IOException ex)
            {
                Logger.getLogger(InitCommon.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public static void recursiveCopy(String origin, String destination) throws IOException
    {
        Path originPath = Paths.get(origin);
        Path destinationPath = Paths.get(destination);
        
        Files.copy(originPath, destinationPath);
        File originFile = originPath.toFile();
        if(originFile.isDirectory())
        {
            for(File worldFile : originFile.listFiles())
            {
                recursiveCopy(origin + "\\" + worldFile.getName(), destination  + "\\" +  worldFile.getName());
            }
        }
    }

    public static void init(FMLInitializationEvent event)
    {
        
    }

    public static void postInit(FMLPostInitializationEvent event)
    {
        
    }
}
