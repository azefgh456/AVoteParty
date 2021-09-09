package fr.acore.spigot.utils;

import fr.acore.spigot.api.storage.column.Column;
import fr.acore.spigot.api.storage.table.Table;

import java.util.List;

@Table(name = "votePartyStorage")
public class VoteParty {

	@Column
	private String name;
	private int objectif;
	@Column
	private int current;
	
	private boolean isRunning;
	private boolean isFinish;
	
	private List<String> commands;
	
	public VoteParty(String name, int objectif, List<String> commands) {
		this.name = name;
		this.objectif = objectif;
		this.current = 0;
		this.isRunning = false;
		this.isFinish = false;
		this.commands = commands;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void increaseVote() {
		current++;
	}
	
	public boolean isRunning() {
		return this.isRunning;
	}
	
	public boolean isWin() {
		return current >= objectif;
	}
	
	public boolean isFinish() {
		return this.isFinish;
	}
	
	public void setIsFinish(boolean isFinish) {
		this.isFinish = isFinish;
	}
	
	public List<String> getCommands(){
		return this.commands;
	}
	
	public int getCurrent() {
		return this.current;
	}
	
	public int getObjectif() {
		return this.objectif;
	}
	
	public void start() {
		isRunning = true;
	}
	
	public void stop() {
		isFinish = true;
		isRunning = false;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof VoteParty) {
			VoteParty target = (VoteParty)obj;
			if(target.getName().equals(name)) return true;
		}
		return false;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

}
