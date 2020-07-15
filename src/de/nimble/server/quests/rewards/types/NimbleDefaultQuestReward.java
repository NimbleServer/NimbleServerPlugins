package de.nimble.server.quests.rewards.types;

import de.nimble.server.player.NimblePlayer;
import de.nimble.server.quests.rewards.NimbleQuestReward;

public class NimbleDefaultQuestReward extends NimbleQuestReward {

  @Override
  public void onReward(NimblePlayer player) {
    player.sendMessage("This is the default Quest reward!");
  }
}
