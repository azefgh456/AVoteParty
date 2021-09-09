package fr.acore.spigot.command;

import fr.acore.spigot.api.command.CommandStats;
import fr.acore.spigot.api.command.sender.ICommandSender;
import fr.acore.spigot.commands.ACommandSenderCommand;
import fr.acore.spigot.manager.AVotePartyManager;
import org.bukkit.command.CommandSender;

public class CommandPartyStart extends ACommandSenderCommand {

	private AVotePartyManager partyM;
	
	public CommandPartyStart(AVotePartyManager partyM) {
		super("start");
		this.partyM = partyM;
	}
	
	@Override
	public CommandStats performCommand(ICommandSender<CommandSender> sender, String... args) {
		if(partyM.getCurrentParty() != null) {
			sender.getSender().sendMessage("Party Stop");
		}else {
			partyM.startParty();
		}
		return CommandStats.SUCCESS;
	}

	@Override
	public String getPermission() {
		return "party.start";
	}

	@Override
	public String getSyntax() {
		// TODO Auto-generated method stub
		return null;
	}
}
