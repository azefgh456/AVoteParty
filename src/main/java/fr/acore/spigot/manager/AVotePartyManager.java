package fr.acore.spigot.manager;

import java.util.ArrayList;
import java.util.List;

import fr.acore.spigot.command.CommandParty;
import fr.acore.spigot.data.AVotePartyFactory;
import fr.acore.spigot.module.AManager;
import fr.acore.spigot.module.AModule;
import fr.acore.spigot.utils.VoteParty;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;


public class AVotePartyManager extends AManager {

	private boolean rememberPlaceAtRestart;
	public boolean getRememberPlaceAtRestart() { return this.rememberPlaceAtRestart;}
	private boolean autoStart;
	public boolean getAutoStart() { return this.autoStart;}
	private boolean autoRestartAtEnd;
	public boolean getAutoRestartAtEnd() { return this.autoRestartAtEnd;}
	private int restartDelay;
	public int getRestartDelay() { return this.restartDelay;}
	private String startPartyMessage;
	public String getStartPartyMessage() { return this.startPartyMessage;}
	
	private String winMessage;
	public String getWinMessage() { return this.winMessage;}
	
	private List<VoteParty> partys;
	
	public AVotePartyManager(AModule plugin) {
		super(plugin, true);
		registerCommand(new CommandParty(this));
		registerDataFactory(new AVotePartyFactory(this));
	}
	
	@Override
	public void setup(FileConfiguration config) {
		rememberPlaceAtRestart = config.getBoolean("rememberPlaceAtRestart");
		autoStart = config.getBoolean("autoStart");
		autoRestartAtEnd = config.getBoolean("autoRestartAtEnd");
		restartDelay = config.getInt("restartDelay");
		startPartyMessage = config.getString("startPartyMessage");
		winMessage = convertColor(config.getString("winMessage"));
		partys = new ArrayList<>();
		for(String party : config.getConfigurationSection("party").getKeys(false)) {
			System.out.println(party);
			partys.add(new VoteParty(party, config.getInt("party." + party + ".objectif"), config.getStringList("party." + party + ".recompences")));
		}
		
		if(autoStart)
			startParty();
		
	}
	
	public void startParty() {
		if(partys.isEmpty()) {
			System.out.println("acunne party");
			return;
		}
		partys.get(0).start();
		Bukkit.broadcastMessage(startPartyMessage);
	}
	
	public VoteParty getCurrentParty() {
		for(VoteParty party : partys) {
			if(party.isRunning()) return party;
		}
		return null;
	}
	
	public void winCurrentParty() {
		VoteParty current = getCurrentParty();
		Bukkit.broadcastMessage(winMessage);
		for(Player player : Bukkit.getOnlinePlayers()) {
			for(String cmd : current.getCommands()) {
				((Plugin)key).getServer().dispatchCommand(((Plugin) key).getServer().getConsoleSender(), cmd.replace("%player%", player.getName()));
			}
		}
		VoteParty futur = partys.get(partys.indexOf(current)+1);
		current.stop();
		
		if(futur == null) {
			if(autoRestartAtEnd)
				startParty();
		}else {
			futur.start();
		}
	}

	public void checkIsWin() {
		if(getCurrentParty().isWin()) {
			winCurrentParty();
		}
	}

	public void stopParty() {
		for(VoteParty party : partys) {
			party.stop();
		}
	}

	public VoteParty setCurrentParty(String partyName) {
		VoteParty newCurrent = null;
		for(VoteParty party : partys) {
			if(party.isRunning() && party.getName().equals(partyName)) return party;
			if(party.isRunning() && !party.getName().equals(partyName)) party.stop();
			
			if(party.getName().equals(partyName)) {
				newCurrent = party;
				party.start();
			}
			
		}
		return newCurrent;
	}
	

}
