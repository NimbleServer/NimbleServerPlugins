package de.nimble.server.quests.objectives;

public enum NimbleQuestObjectiveType {
  ITEM((byte) 1),
  MOB((byte) 2),
  WALK((byte) 3),
  TALK((byte) 4);

  private byte id;

  NimbleQuestObjectiveType(byte id) {
    this.id = id;
  }

  public void setId(byte id) {
    this.id = id;
  }

  public byte getId() {
    return this.id;
  }
}
