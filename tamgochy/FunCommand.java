package tamgochy;

import java.util.Arrays;

public class FunCommand extends Command {	
	public FunCommand() {
		synonyms.addAll(Arrays.asList(new String[] {"счастье", "веселье"}));
		defaultReply = new Reply("Я так рад, что ты у меня есть!");
	}

	@Override
	public Reply reply(String input, String id, Bot bot) {
    	bot.handleEvent("fun", id);
    	return defaultReply;
	}
}
