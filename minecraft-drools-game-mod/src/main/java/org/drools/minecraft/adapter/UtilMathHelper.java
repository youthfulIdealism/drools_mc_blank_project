/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.minecraft.adapter;

import org.drools.game.horserace.model.Checkpoint;
import org.drools.game.horserace.model.Location;

/**
 *
 * @author Samuel
 */
public class UtilMathHelper {

    /**
     * helper method. Determines if a player is mathematically
     * within a zone.
     *
     * @param player
     * @param checkpoint
     *
     * @return
     */
    public static boolean playerWithinCheckpoint( Location playerLoc, Checkpoint checkpoint ) {
        Location roomLowerLoc = checkpoint.getLowerBound();
        Location roomUpperLoc = checkpoint.getUpperBound();
        boolean xWithin = within( playerLoc.getX(), roomLowerLoc.getX(), roomUpperLoc.getX() );
        boolean yWithin = within( playerLoc.getY(), roomLowerLoc.getY(), roomUpperLoc.getY() );
        boolean zWithin = within( playerLoc.getZ(), roomLowerLoc.getZ(), roomUpperLoc.getZ() );
        return xWithin && yWithin && zWithin;
    }

    /**
     * helper method. Determines if a number is within bounds.
     *
     * @param number
     * @param first
     * @param second
     *
     * @return
     */
    private static boolean within( int number, int first, int second ) {
        int min = Math.min( first, second );
        int max = Math.max( first, second );
        return number >= min && number <= max;
    }

}
