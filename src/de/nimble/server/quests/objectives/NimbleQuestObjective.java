package de.nimble.server.quests.objectives;

public class NimbleQuestObjective {

  private int id;
  private int questId;
  private int amount;
  private String name;
  private NimbleQuestObjectiveType type;

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return this.id;
  }

  public void setQuestId(int questId) {
    this.questId = questId;
  }

  public int getQuestId() {
    return questId;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public int getAmount() {
    return this.amount;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setType(NimbleQuestObjectiveType type) {
    this.type = type;
  }

  public NimbleQuestObjectiveType getType() {
    return type;
  }
}
