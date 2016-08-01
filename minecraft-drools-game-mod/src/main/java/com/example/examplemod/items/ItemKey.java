/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.examplemod.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 *
 * @author Samuel
 */
public class ItemKey extends Item {

    public ItemKey() {
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTabs.MISC);
    }
}
