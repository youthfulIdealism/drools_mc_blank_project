/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.examplemod.items;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

/**
 *
 * @author Samuel
 */
public class ItemRulerRoom extends Item {

    public static final String outString = "..\\";
    private BlockPos workingPos;
    
    public ItemRulerRoom() {
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTabs.MISC);
        workingPos = null;
    }
    
    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(worldIn.isRemote)
        {
            if(workingPos != null)
            {
                System.out.println("Ruler writing: " + workingPos + " to: " + pos);
                BufferedWriter roomWriter = null;
                try
                {
                    roomWriter = new BufferedWriter(new FileWriter(outString + "roomdefs.txt", true));
                    roomWriter.write("Room " + stack.getDisplayName().toLowerCase().replaceAll(" ", "") + " = new Room(" + workingPos.getX() + ", " + workingPos.getY() + ", " + workingPos.getZ() + ", " + pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + ", \"" + stack.getDisplayName() + "\");");
                    roomWriter.write("\n");
                    roomWriter.write("insert(" + stack.getDisplayName().toLowerCase().replaceAll(" ", "") + ");");
                    roomWriter.write("\n");
                    roomWriter.flush();
                    playerIn.addChatComponentMessage(new TextComponentString("Defined room " + stack.getDisplayName()));
                } catch (IOException ex)
                {
                    Logger.getLogger(ItemRulerRoom.class.getName()).log(Level.SEVERE, null, ex);
                } finally
                {
                    try
                    {
                        roomWriter.close();
                    } catch (IOException ex){}
                }
                workingPos = null;
            }else
            {
                playerIn.addChatComponentMessage(new TextComponentString("Started defining room " + stack.getDisplayName()));
            }
        }
        
        
        return EnumActionResult.SUCCESS;
    }
    
    
}
