package me.joeleoli.properfreeze;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import me.joeleoli.properfreeze.command.FreezeCommand;
import me.joeleoli.properfreeze.listener.PlayerListener;
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
		this.commandHandler.addCommands(FreezeCommand.class);
		this.frozenPlayers = new ArrayList<>();

		this.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
	}

	/**
	 * Gets if the player is frozen.
	 *
	 * @param player the player
	 *
	 * @return true if present, otherwise false
	 */
	public boolean isFrozen(Player player) {
		return this.frozenPlayers.contains(player.getUniqueId());
	}

	/**
	 * Freezes or unfreezes a player.
	 *
	 * @param player the player
	 * @param frozen freeze the player
	 */
	public void setFrozen(Player player, boolean frozen) {
		if (frozen) {
			this.frozenPlayers.add(player.getUniqueId());
			PlayerUtil.denyMovement(player);
		} else {
			this.frozenPlayers.remove(player.getUniqueId());
			PlayerUtil.allowMovement(player);
		}
	}

	/**
	 * Tries to unfreeze the player if frozen.
	 * Used when players disconnect as their walk speed,
	 * fly speed, and potion effects could possibly be
	 * saved by the server (and will persist when they
	 * reconnect).
	 *
	 * @param player the player
	 */
	public void clean(Player player) {
		if (this.frozenPlayers.remove(player.getUniqueId())) {
			PlayerUtil.allowMovement(player);
		}
	}

}
