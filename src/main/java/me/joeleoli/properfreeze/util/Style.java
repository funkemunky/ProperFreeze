package me.joeleoli.properfreeze.util;

import java.text.MessageFormat;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Style {

	public static String formatFreezeMessage(CommandSender sender, Player target, String context) {
		return new MessageFormat("{1}{2}{0} has been {4} by {1}{3}").format(new Object[]{
				ChatColor.YELLOW, ChatColor.LIGHT_PURPLE, target.getName(), sender.getName(), context
		});
	}

	public static String formatDisconnectMessage(Player target) {
		return new MessageFormat("{1}{2}{0} has disconnected while frozen.").format(new Object[]{
				ChatColor.YELLOW, ChatColor.LIGHT_PURPLE, target.getName()
		});
	}

}
