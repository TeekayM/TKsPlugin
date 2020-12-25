package com.tinykitty.tksplugin.events;

import org.bukkit.Material;

import java.util.ArrayList;

public class Lists {
    public static ArrayList<Material> getPoisonousBlocks() {
        ArrayList poisonousBlocks = new ArrayList();
        poisonousBlocks.add(Material.GRASS);
        poisonousBlocks.add(Material.TALL_GRASS);
        poisonousBlocks.add(Material.FERN);
        poisonousBlocks.add(Material.LARGE_FERN);

        return poisonousBlocks;
    }
}
