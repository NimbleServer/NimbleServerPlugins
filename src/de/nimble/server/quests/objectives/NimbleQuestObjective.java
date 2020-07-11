package de.nimble.server.quests.objectives;

public class NimbleQuestObjective {

  private String questId;
  private NimbleQuestObjectiveType type;
  private int amount;

  public void setQuestId(String questId) {
    this.questId = questId;
  }

  public String getQuestId() {
    return questId;
  }

  public void setType(NimbleQuestObjectiveType type) {
    this.type = type;
  }

  public NimbleQuestObjectiveType getType() {
    return type;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public int getAmount() {
    return this.amount;
  }
}
