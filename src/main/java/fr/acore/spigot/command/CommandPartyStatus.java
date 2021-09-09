package fr.acore.spigot.command;

import java.util.Arrays;

import fr.acore.spigot.api.command.CommandStats;
import fr.acore.spigot.commands.APlayerCommand;
import fr.acore.spigot.commands.sender.CorePlayerSender;
import fr.acore.spigot.manager.AVotePartyManager;
import fr.acore.spigot.utils.VoteParty;
import org.bukkit.entity.Player;

public class CommandPartyStatus extends APlayerCommand {

	private AVotePartyManager partyM;
	
	public CommandPartyStatus(AVotePartyManager partyM) {
		super("status");
		setAlliases(Arrays.asList("statut", "statuts", "infos", "info"));
		this.partyM = partyM;
	}
	
	@Override
	public CommandStats performAPlayerCommand(CorePlayerSender sender, String... args) {
		
		VoteParty current = partyM.getCurrentParty();
		
		if(current == null) {
			sender.getSender().sendMessage("Aucun VoteParty en cour");
		}else {

			sender.getSender().sendMessage("VoteParty en cour : " + current.getName());
			sender.getSender().sendMessage("Status : " + current.getCurrent() + "/" + current.getObjectif());
			
		}
		
		
		
		return CommandStats.SUCCESS;
	}

	@Override
	public String getPermission() {
		return "party.status";
	}

	@Override
	public String getSyntax() {
		// TODO Auto-generated method stub
		return null;
	}

}
