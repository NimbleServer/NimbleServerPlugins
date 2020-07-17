package de.nimble.server.quests.rewards;

import java.util.Arrays;

public enum NimbleQuestRewardType {
	DEFAULT((byte) 0),
	ITEM((byte) 1),
	EXPERIENCE((byte) 2),
	CURRENCY((byte) 3);

	private byte id;

	NimbleQuestRewardType(byte id) {
		this.id = id;
	}

	public byte getId() {
		return this.id;
	}

	public static NimbleQuestRewardType getById(byte id) {
		NimbleQuestRewardType type = DEFAULT;
		for(NimbleQuestRewardType t : values()) {
			if(t.getId() == id) {
				type = t;
			}
		}
		return type;
	}

	public static NimbleQuestRewardType getByName(String name) {
		NimbleQuestRewardType type = DEFAULT;
		for(NimbleQuestRewardType t : values()) {
      if (t.name().equalsIgnoreCase(name)) {
        type = t;
			}
		}
		return type;
	}

}
