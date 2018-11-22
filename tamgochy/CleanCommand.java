package tamgochy;

import java.util.Arrays;

public class CleanCommand extends Command {	
	public CleanCommand() {
		synonyms.addAll(Arrays.asList(new String[] {"гигиена", "мыться", "мойся"}));
		defaultReply = new Reply("А сейчас я хочу расслабиться и принять ванну");
	}

	@Override
	public Reply reply(String input, String id, Bot bot) {
    	bot.handleEvent("clean", id);
    	return defaultReply;
	}
}
