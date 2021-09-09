package fr.acore.spigot;

import fr.acore.spigot.manager.AVotePartyManager;
import fr.acore.spigot.module.AModule;

public class AVoteParty extends AModule {

	@Override
	public void onEnable() {
		super.onEnable();
		registerManager(new AVotePartyManager(this));
		log("AVoteParty Enabled");
	}
	
}
