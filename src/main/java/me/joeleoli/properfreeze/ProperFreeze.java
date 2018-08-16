package me.joeleoli.properfreeze;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import me.joeleoli.properfreeze.util.PlayerUtil;
import me.mrten.commandannotations.CommandHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class ProperFreeze extends JavaPlugin {

	@Getter
	private static ProperFreeze instance;

	private CommandHandler commandHandler;
	private List<UUID> frozenPlayers;

	@Override
	public void onEnable() {
		instance = this;

		this.commandHandler = new CommandHandler(
				this,
				ChatColor.RED + "No permission.",
				ChatColor.RED + "This command can only be executed in-game.",
				ChatColor.RED + "Usage: {usage}",
				ChatColor.RED + "Failed to execute that command."
		);
		this.commandHandler.addCommands(ProperFreeze.class);
		this.frozenPlayers = new ArrayList<>();
	}

	/**
	 * Gets if the player is present in the frozen list.
	 *
	 * @param player the player
	 *
	 * @return true if present, otherwise false
	 */
	public boolean isFrozen(Player player) {
		return this.frozenPlayers.contains(player.getUniqueId());
	}

	/**
	 * Removes a player from the frozen list if present, otherwise adds the player to the frozen list.
	 *
	 * @param player the player
	 *
	 * @return false if removed from the list, true if added to the list
	 */
	public boolean toggleFreeze(Player player) {
		if (this.frozenPlayers.remove(player.getUniqueId())) {
			PlayerUtil.allowMovement(player);
			return false;
		} else {
			this.frozenPlayers.add(player.getUniqueId());
			PlayerUtil.denyMovement(player);
			return true;
		}
	}

	/**
	 * Tries to remove the player from the list.
	 *
	 * @param player the player
	 */
	public void clean(Player player) {
		this.frozenPlayers.remove(player.getUniqueId());
	}

}
