package fr.acore.spigot.command;

import fr.acore.spigot.api.command.CommandStats;
import fr.acore.spigot.commands.sender.ConsoleSender;
import fr.acore.spigot.commands.utils.AConsoleCommand;
import fr.acore.spigot.manager.AVotePartyManager;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;


public class CommandPartyAddVote extends AConsoleCommand {

	private AVotePartyManager partyM;
	
	public CommandPartyAddVote(AVotePartyManager partyM) {
		super("add");
		this.partyM = partyM;
	}



	@Override
	public CommandStats performAPlayerCommand(ConsoleSender sender, String... args) {
		
		partyM.getCurrentParty().increaseVote();
		partyM.checkIsWin();
		
		return CommandStats.SUCCESS;
	}

	@Override
	public String getPermission() {
		return "consol.vote";
	}

	@Override
	public String getSyntax() {
		// TODO Auto-generated method stub
		return null;
	}
}
