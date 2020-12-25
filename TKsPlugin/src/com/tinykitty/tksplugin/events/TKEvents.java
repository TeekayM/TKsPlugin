package com.tinykitty.tksplugin.events;

import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.EntityBlockFormEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class TKEvents implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        // Sends a message to the player that joined
        Player player = event.getPlayer();
        player.sendTitle((ChatColor.LIGHT_PURPLE + "Welcome to "), (ChatColor.RED + "HELL"), 0, 100, 20);
        player.playSound(player.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, SoundCategory.AMBIENT, 0.1f, 0.1f);
        player.playSound(player.getLocation(), Sound.ENTITY_WITHER_DEATH, SoundCategory.AMBIENT, 0.1f, 0.1f);
        player.sendMessage((ChatColor.RED + "Welcome to HELL!\n") + (
                ChatColor.LIGHT_PURPLE + "Now with MORE things" +
                ChatColor.LIGHT_PURPLE + "\n- Ferns and tall grass are poisonous too now;" +
                ChatColor.LIGHT_PURPLE + "\n- Replaced salmons with tropical fish;" +
                ChatColor.LIGHT_PURPLE + "\n- Made sounds quieter;"
        ));

    }

    @EventHandler
    public void  playerConsumeItem(PlayerItemConsumeEvent event) {

        // When a player consumes an item, a random number between 1 and 20 is generated. If the number is smaller than the player's hit points, is sets the player's health to that random number.
        Player player = event.getPlayer();
        double a = Math.random() * 19 + 1;
        if (a < player.getHealth()) {
            player.setHealth(a);
        }

    }

    @EventHandler
    public void entityDeathEvent(EntityDeathEvent event) {

        // Fires when an entity dies.
        if (event.getEntityType() == EntityType.CREEPER) {
            event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.PRIMED_TNT);
        }

        // When the ender dragon dies, it spawns 7 tht. I'm too lazy to make a for loop.
        if (event.getEntityType() == EntityType.ENDER_DRAGON) {

            event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.PRIMED_TNT);
            event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.PRIMED_TNT);
            event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.PRIMED_TNT);
            event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.PRIMED_TNT);
            event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.PRIMED_TNT);
            event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.PRIMED_TNT);
            event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.PRIMED_TNT);

        }

        // If the entity isn't a tropical fish, generate a small explosion
        if (event.getEntityType() != EntityType.TROPICAL_FISH) {
            event.getEntity().getWorld().createExplosion(event.getEntity().getLocation(), 1.0f);
        }

    }

    @EventHandler
    public void playerDropItemEvent(PlayerDropItemEvent event) {

        // When a player drops an item, it has an 80% chance of failing.
        double a = Math.random() * 49 + 1;
        if (a < 40) {
            Player player = event.getPlayer();
            player.playSound(player.getLocation(), Sound.ENTITY_WITHER_HURT, SoundCategory.AMBIENT, 0.1f,0.1f);
            player.sendTitle((ChatColor.RED + "unlucky"), (ChatColor.RED + "try again"), 1, 1, 1);
            event.setCancelled(true);
        }

    }

    @EventHandler
    public void playerEnterBed(PlayerBedEnterEvent event) {

        // When a player enters a bed, summon tnt to make sure that the bed gets destroyed.
        event.getPlayer().getWorld().spawnEntity(event.getPlayer().getLocation(), EntityType.PRIMED_TNT);
        Player player = event.getPlayer();
        player.playSound(player.getLocation(), Sound.ENTITY_WITHER_HURT, SoundCategory.AMBIENT, 0.1f,0.1f);
        player.sendTitle((ChatColor.RED + "no"), null, 1, 1, 1);

    }

    @EventHandler
    public void entityDamageEvent(EntityDamageEvent event) {

        // Makes spiders invincible
        if (event.getEntityType() == EntityType.SPIDER) {
            event.setCancelled(true);
        }

    }

    @EventHandler
    public void entitySpawn(EntitySpawnEvent event) {

        // When a skeleton spawns, create a new bow, add power 5 to it, then equip it to the skeleton's main hand.
        if (event.getEntityType() == EntityType.SKELETON) {
            Skeleton skeleton = (Skeleton) event.getEntity();
            ItemStack pBow = new ItemStack(Material.BOW);
            pBow.addEnchantment(Enchantment.ARROW_DAMAGE, 5);
            skeleton.getEquipment().setItemInMainHand(pBow);
        }

        // When a zombie spawns, give him a diamond chestplate and a diamond sword.
        if (event.getEntityType() == EntityType.ZOMBIE) {
            Zombie zombie = (Zombie) event.getEntity();
            zombie.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
            zombie.getEquipment().setItemInMainHand(new ItemStack(Material.DIAMOND_SWORD));
        }

    }

    @EventHandler
    public void blockBreakEvent(BlockBreakEvent event) throws InterruptedException {

        // If a player breaks a block, and it contains "LOG", it has a 80% chance of failing.
        if (event.getBlock().getType().toString().contains("LOG")) {
            double a = Math.random() * 49 + 1;
            if (a < 40) {
                Player player = event.getPlayer();
                player.playSound(player.getLocation(), Sound.ENTITY_WITHER_HURT, SoundCategory.AMBIENT, 0.1f,0.1f);
                player.sendTitle((ChatColor.RED + "unlucky"), (ChatColor.RED + "try again"), 1, 1, 1);
                event.setCancelled(true);
            }
        }

        // If a player breaks a block, and it's netherrack, it has a 80% chance of failing.
        if (event.getBlock().getType() == Material.NETHERRACK) {
            double a = Math.random() * 49 + 1;
            if (a < 40) {
                Player player = event.getPlayer();
                player.playSound(player.getLocation(), Sound.ENTITY_WITHER_HURT, SoundCategory.AMBIENT, 0.1f,0.1f);
                player.sendTitle((ChatColor.RED + "unlucky"), (ChatColor.RED + "try again"), 1, 1, 1);
                event.setCancelled(true);
            }
        }

        // If a player breaks a block, and it's coal ore, it has a 80% chance of failing.
        if (event.getBlock().getType() == Material.COAL_ORE) {
            double a = Math.random() * 49 + 1;
            if (a < 40) {
                Player player = event.getPlayer();
                player.playSound(player.getLocation(), Sound.ENTITY_WITHER_HURT, SoundCategory.AMBIENT, 0.1f,0.1f);
                player.sendTitle((ChatColor.RED + "unlucky"), (ChatColor.RED + "try again"), 1, 1, 1);
                event.setCancelled(true);
            }
        }

        // If a player breaks a block, and if it's a poisonous block, it has a 80% chance of failing.
        if ((event.getBlock().getType() == Material.GRASS) || (event.getBlock().getType() == Material.TALL_GRASS)) {
            Player player = event.getPlayer();
            player.playSound(player.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 0.1f, 0.1f);
            event.getBlock().getWorld().spawnParticle(Particle.TOTEM, event.getBlock().getLocation(), 100);
            Entity bee1 = event.getBlock().getWorld().spawnEntity(event.getBlock().getLocation(), EntityType.BEE);
            bee1.getWorld().createExplosion(bee1.getLocation(), 4.0f);
        }
    }

    @EventHandler
    public void playerMoveEvent(PlayerMoveEvent event) {

        // If player is on Nether Bricks, break the block underneath
        Player player = event.getPlayer();
        if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.NETHER_BRICKS) {
            player.getLocation().getBlock().getRelative(BlockFace.DOWN).breakNaturally();
        }

        // 0.5% chance of the player tripping
        double a = Math.random() * 100;
        if (a < 0.1) {
            player.setHealth(player.getHealth() - 2);
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 255));
            player.sendTitle((ChatColor.RED + "-"), (ChatColor.RED + "you tripped"), 5, 10, 5);
        }

        // If player is on a poisonous block, poison player | Configurable in Lists class
        if (Lists.getPoisonousBlocks().contains(event.getPlayer().getLocation().getBlock().getType())) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20, 255));
        }

        // If player is on a cornflower, levitate
        if (player.getLocation().getBlock().getType() == Material.CORNFLOWER) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 100, 2));
        }
    }

    @EventHandler
    public void blockPlaceEvent(BlockPlaceEvent event) {

        // If the player places a block containing "BED", summon primed tnt
        if (event.getBlock().getType().toString().contains("BED")) {
            event.getBlock().getWorld().spawnEntity(event.getBlock().getLocation(), EntityType.PRIMED_TNT);
            Player player = event.getPlayer();
            player.playSound(player.getLocation(), Sound.ENTITY_WITHER_HURT, SoundCategory.AMBIENT, 0.1f,0.1f);
            player.sendTitle((ChatColor.RED + "no"), null, 1, 1, 1);
            event.getBlock().setType(Material.AIR);
        }

    }

    @EventHandler
    public void playerDeathEvent(PlayerDeathEvent event) {

        // Once player dies, play sound and show title
        Player player = event.getEntity();
        player.playSound(player.getLocation(), Sound.ENTITY_WITHER_DEATH, SoundCategory.AMBIENT, 0.1f,0.1f);
        player.sendTitle((ChatColor.RED + "lol"), (ChatColor.RED + "u died"), 60, 60, 60);

    }

    @EventHandler
    public void playerRespawnEvent(PlayerRespawnEvent event) {

        // Once player respawns, set maxEntityCramming to -1 (infinity) so the tropical fish dont DIE
        Player player = event.getPlayer();
        player.getWorld().setGameRule(GameRule.MAX_ENTITY_CRAMMING, -1);
        for (int i=1; i<=50; i++) {
            player.getWorld().spawnEntity(player.getLocation(), EntityType.TROPICAL_FISH);
        }
    }

    @EventHandler
    public void playerAdvancementDoneEvent(PlayerAdvancementDoneEvent event) {

        // Once player completes advancement, summon tropical fish
        Player player = event.getPlayer();
        player.getWorld().spawnEntity(player.getLocation(), EntityType.TROPICAL_FISH);

    }

    @EventHandler
    public void playetBucketEmptyEvent(PlayerBucketEmptyEvent event) {

        // 80% chance of bucket place failing
        double a = Math.random() * 49 + 1;
        if (a < 40) {
            Player player = event.getPlayer();
            player.playSound(player.getLocation(), Sound.ENTITY_WITHER_HURT, SoundCategory.AMBIENT, 0.1f,0.1f);
            player.sendTitle((ChatColor.RED + "unlucky"), (ChatColor.RED + "try again"), 1, 1, 1);
            event.setCancelled(true);
        }

    }


}
