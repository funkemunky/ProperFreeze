package me.joeleoli.properfreeze.command;

import me.joeleoli.properfreeze.ProperFreeze;
import me.joeleoli.properfreeze.util.PlayerUtil;
import me.joeleoli.properfreeze.util.Style;
import me.mrten.commandannotations.Command;
import me.mrten.commandannotations.CommandContext;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class FreezeCommand {

	@Command(name = "freeze", permission = "properfreeze.freeze", usage = "/freeze <target>", min = 1, max = 1)
	public void freeze(CommandContext commandContext) {
		final Player target = ProperFreeze.getInstance().getServer().getPlayer(commandContext.getArguments()[0]);

		if (target == null) {
			commandContext.getCommandSender().sendMessage(ChatColor.RED + "That player could not be found.");
			return;
		}

		final boolean frozen = !ProperFreeze.getInstance().isFrozen(target);

		ProperFreeze.getInstance().setFrozen(target, frozen);
		PlayerUtil.messageStaff(
				Style.formatFreezeMessage(commandContext.getCommandSender(), target, frozen ? "frozen" : "unfrozen"));
	}

}
