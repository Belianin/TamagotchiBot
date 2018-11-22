package tamgochy;

import java.util.Arrays;

public class SleepCommand extends Command {	
	public SleepCommand() {
		synonyms.addAll(Arrays.asList(new String[] {"спать", "сон", "спи"}));
		defaultReply = new Reply("Я ложусь спать, не беспокой меня несколько минут!");
	}

	@Override
	public Reply reply(String input, String id, Bot bot) {
    	bot.handleEvent("sleep", id);
    	return defaultReply;
	}
}
