package de.nimble.server.quests;

import de.nimble.server.player.NimblePlayer;
import de.nimble.server.quests.objectives.NimbleQuestObjective;
import de.nimble.server.quests.rewards.NimbleQuestReward;

import java.util.ArrayList;
import java.util.List;

public abstract class NimbleQuest {

  private int id;
  private String name;
  private String description;
  private NimbleQuestType type;
  private List<NimbleQuestReward> rewards;
  private List<NimbleQuestObjective> objectives;

  public NimbleQuest(String name) {
    this.name = name;
    this.rewards = new ArrayList<>();
    this.objectives = new ArrayList<>();
  }

  public abstract void onFinish(NimblePlayer player);

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

  public void addReward(NimbleQuestReward reward) {
    this.rewards.add(reward);
  }

  public void setRewards(List<NimbleQuestReward> rewards) {
    this.rewards = rewards;
  }

  public List<NimbleQuestReward> getRewards() {
    return this.rewards;
  }

  public void addObjective(NimbleQuestObjective objective) {
    this.objectives.add(objective);
  }

  public void setObjectives(List<NimbleQuestObjective> objectives) {
    this.objectives = objectives;
  }

  public List<NimbleQuestObjective> getObjectives() {
    return this.objectives;
  }
}
