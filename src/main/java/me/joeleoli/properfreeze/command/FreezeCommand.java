package me.joeleoli.properfreeze.command;

import me.joeleoli.properfreeze.ProperFreeze;
import me.mrten.commandannotations.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FreezeCommand {

	@Command(name = "freeze", permission = "properfreeze.freeze", usage = "/freeze <target>", min = 1, max = 1)
	public static void freeze(CommandSender sender) {
		final Player target = ProperFreeze.getInstance().getServer().getPlayer()
	}

}
