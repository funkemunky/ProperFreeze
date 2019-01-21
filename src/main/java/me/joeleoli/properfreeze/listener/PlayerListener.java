package me.joeleoli.properfreeze.listener;

import me.joeleoli.properfreeze.ProperFreeze;
import me.joeleoli.properfreeze.util.PlayerUtil;
import me.joeleoli.properfreeze.util.Style;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		final Player player = event.getPlayer();

		if (ProperFreeze.getInstance().isFrozen(player)) {
			PlayerUtil.messageStaff(Style.formatDisconnectMessage(event.getPlayer()));
		}

		ProperFreeze.getInstance().clean(player);
	}

	/* We include an additional move event in case client developers get smart and send in a
	   PacketPlayInAbilities packet to update their walk speed back to normal. */
	@EventHandler
	public void onEvent(PlayerMoveEvent event) {
		final Player player = event.getPlayer();

		if(ProperFreeze.getInstance().isFrozen(player)) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent event) {
		if (ProperFreeze.getInstance().isFrozen(event.getPlayer())) {
			event.getPlayer().sendMessage(ChatColor.RED + "You cannot drop items while frozen.");
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			final Player player = (Player) event.getEntity();

			if (ProperFreeze.getInstance().isFrozen(player)) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent event) {
		if (event.getEntity() instanceof Player) {
			final Player player = (Player) event.getEntity();

			if (ProperFreeze.getInstance().isFrozen(player)) {
				event.setCancelled(true);
			}
		}
	}

}
