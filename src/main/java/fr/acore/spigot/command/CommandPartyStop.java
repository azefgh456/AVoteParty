package fr.acore.spigot.command;

import fr.acore.spigot.api.command.CommandStats;
import fr.acore.spigot.commands.APlayerCommand;
import fr.acore.spigot.commands.sender.CorePlayerSender;
import fr.acore.spigot.manager.AVotePartyManager;
import org.bukkit.entity.Player;


public class CommandPartyStop extends APlayerCommand {

	private AVotePartyManager partyM;
	
	public CommandPartyStop(AVotePartyManager partyM) {
		super("stop");
		this.partyM = partyM;
	}
	@Override
	public CommandStats performAPlayerCommand(CorePlayerSender sender, String... args) {
		
		if(partyM.getCurrentParty() == null) {
			sender.getSender().sendMessage("party start");
		}else {
			partyM.stopParty();
			sender.getSender().sendMessage("party quitt�e");
		}
		
		return CommandStats.SUCCESS;
	}

	@Override
	public String getPermission() {
		return "party.stop";
	}

	@Override
	public String getSyntax() {
		// TODO Auto-generated method stub
		return null;
	}
}
