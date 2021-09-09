package fr.acore.spigot.command;

import fr.acore.spigot.ACoreSpigotAPI;
import fr.acore.spigot.api.command.CommandStats;
import fr.acore.spigot.api.player.impl.CorePlayer;
import fr.acore.spigot.api.version.Version;
import fr.acore.spigot.commands.APlayerCommand;
import fr.acore.spigot.commands.sender.CorePlayerSender;
import fr.acore.spigot.manager.AVotePartyManager;
import org.bukkit.entity.Player;

import java.util.Arrays;


public class CommandParty extends APlayerCommand {

	private AVotePartyManager manager;

	public CommandParty(AVotePartyManager partyM) {
		super("voteparty");
		setArguments(Arrays.asList(new CommandPartyAddVote(partyM), new CommandPartyStart(partyM), new CommandPartyStatus(partyM), new CommandPartyStop(partyM)));
		this.manager = partyM;
	}
	
	@Override
	public CommandStats performAPlayerCommand(CorePlayerSender sender, String... args) {
		try {
			sender.getSender().sendMessage(manager.getPluginName() + " " + manager.getPluginVersion().getVersion());
		} catch (Version.ParseVersionException e) {
			e.printStackTrace();
		}
		return CommandStats.SUCCESS;
	}

	@Override
	public String getPermission() {
		return "party.help";
	}

	@Override
	public String getSyntax() {
		// TODO Auto-generated method stub
		return null;
	}

}
