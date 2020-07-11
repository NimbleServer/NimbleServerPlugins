package de.nimble.server.quests;

import de.nimble.server.quests.rewards.NimbleQuestReward;

public class NimbleQuest {

	private int id;
	private String name;
	private String description;
	private NimbleQuestType type;
	private NimbleQuestReward reward;

	public NimbleQuest(String name) {
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public void setType(NimbleQuestType type) {
		this.type = type;
	}

	public NimbleQuestType getType() {
		return this.type;
	}

	public void setReward(NimbleQuestReward reward) {
		this.reward = reward;
	}

	public NimbleQuestReward getReward() {
		return this.reward;
	}

}
