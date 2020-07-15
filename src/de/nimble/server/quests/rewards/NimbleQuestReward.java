package de.nimble.server.quests.rewards;

import de.nimble.server.player.NimblePlayer;

public abstract class NimbleQuestReward {

  private int id;
  private int questId;
  private String name;
  private NimbleQuestRewardType type;

  public abstract void onReward(NimblePlayer player);

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getQuestId() {
    return questId;
  }

  public void setQuestId(int questId) {
    this.questId = questId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public NimbleQuestRewardType getType() {
    return type;
  }

  public void setType(NimbleQuestRewardType type) {
    this.type = type;
  }
}
