package me.joeleoli.properfreeze.util;

import me.joeleoli.properfreeze.ProperFreeze;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerUtil {

	public static void messageStaff(String message) {
		ProperFreeze.getInstance().getServer().getOnlinePlayers().forEach(player -> {
			if (player.hasPermission("properfreeze.staff")) {
				player.sendMessage(message);
			}
		});
	}

	public static void denyMovement(Player player) {
		player.setWalkSpeed(0.0F);
		player.setFlySpeed(0.0F);
		player.setFoodLevel(0);
		player.setSprinting(false);
		player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 200));
	}

	public static void allowMovement(Player player) {
		player.setWalkSpeed(0.2F);
		player.setFlySpeed(0.0001F);
		player.setFoodLevel(20);
		player.setSprinting(true);
		player.removePotionEffect(PotionEffectType.JUMP);
	}

}
