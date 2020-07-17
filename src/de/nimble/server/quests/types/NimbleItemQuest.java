package de.nimble.server.quests.types;

import de.nimble.server.player.NimblePlayer;
import de.nimble.server.quests.NimbleQuest;
import de.nimble.server.quests.rewards.NimbleQuestReward;

public class NimbleItemQuest extends NimbleQuest {

	public NimbleItemQuest(String name) {
		super(name);
	}

	@Override
	public void onFinish(NimblePlayer player) {
		for(NimbleQuestReward reward : getRewards()) {
			reward.onReward(player);
		}
	}

}
