package de.nimble.server.quests;

import java.util.ArrayList;
import java.util.List;

public class NimbleQuestManager {

	private List<NimbleQuest> quests;

	public NimbleQuestManager() {
		this.quests = new ArrayList<>();
	}

	public void addQuest(NimbleQuest quest) {
		this.quests.add(quest);
	}

	public List<NimbleQuest> getQuests() {
		return this.quests;
	}

	public void deleteQuest(int id) {
		for(NimbleQuest quest : quests) {
			if(quest.getId() == id) {
				quests.remove(quest);
			}
		}
	}

	public void deleteQuest(String name) {
		for(NimbleQuest quest : quests) {
			if(quest.getName().equals(name)) {
				quests.remove(quest);
			}
		}
	}

	public NimbleQuest getQuestById(int id) {
		for(NimbleQuest quest : quests) {
			if(quest.getId() == id) {
				return quest;
			}
		}
		return null;
	}

	public NimbleQuest getQuestByName(String name) {
		for(NimbleQuest quest : quests) {
			if(quest.getName().equals(name)) {
				return quest;
			}
		}
		return null;
	}

}
