package tamagotchi;

import java.util.Arrays;

public class ToiletCommand extends Command {	
	public ToiletCommand() {
		synonyms.addAll(Arrays.asList(new String[] {"туалет", "🚽"}));
		defaultReply = new Reply("Секундочку, я отлучусь...");
	}

	@Override
	public Reply reply(String input, String id, Bot bot) {
		if (bot.getTamagochyMap().get(id).canToilet) {
			bot.getTamagochyMap().get(id).addToilet(100);
			return defaultReply;
		}
		else {
			return new Reply("Эта команда еще не открыта");
		}
	}
}
