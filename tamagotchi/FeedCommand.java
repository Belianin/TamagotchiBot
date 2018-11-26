package tamagotchi;

import java.util.Arrays;

public class FeedCommand extends Command {	
	public FeedCommand() {
		synonyms.addAll(Arrays.asList(new String[] {"питание", "еда", "ешь", "покормить"}));
		defaultReply = new Reply("Сейчас я буду кушать, сейчас меня покормят!");
	}

	@Override
	public Reply reply(String input, String id, Bot bot) {
		if (bot.getTamagochyMap().get(id).canEat) {
			bot.getTamagochyMap().get(id).addHunger(60);
			return defaultReply;
		}
		else {
			return new Reply("Эта команда еще не открыта");
		}
	}
}
