//package tamgochy;

import java.util.Arrays;

public class FunCommand extends Command {	
	public FunCommand() {
		synonyms.addAll(Arrays.asList(new String[] {"счастье", "веселье"}));
		defaultReply = new Reply("Я так рад, что ты у меня есть!");
	}

	@Override
	public Reply reply(String input, String id, Bot bot) {
		if (bot.getTamagochyMap().get(id).canFun) {
			bot.getTamagochyMap().get(id).addFun(40);
			return defaultReply;
		}
		else {
			return new Reply("Эта команда еще не открыта");
		}
	}
}
