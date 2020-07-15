package de.nimble.server.quests.objectives;

import de.nimble.server.quests.rewards.NimbleQuestRewardType;

public enum NimbleQuestObjectiveType {
  DEFAULT((byte) 0),
  ITEM((byte) 1),
  MOB((byte) 2),
  WALK((byte) 3),
  TALK((byte) 4);

  private byte id;

  NimbleQuestObjectiveType(byte id) {
    this.id = id;
  }

  public byte getId() {
    return this.id;
  }

  public static NimbleQuestObjectiveType getById(byte id) {
    NimbleQuestObjectiveType type = DEFAULT;
    for (NimbleQuestObjectiveType t : values()) {
      if (t.getId() == id) {
        type = t;
      }
    }
    return type;
  }

  public static NimbleQuestObjectiveType getByName(String name) {
    NimbleQuestObjectiveType type = DEFAULT;
    for (NimbleQuestObjectiveType t : values()) {
      if (t.name().equalsIgnoreCase(name)) {
        type = t;
      }
    }
    return type;
  }
}
