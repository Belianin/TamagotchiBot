package tamagotchi;

import java.util.Arrays;

public class ShowCommandsCommand extends Command {	
	public ShowCommandsCommand() {
		synonyms.addAll(Arrays.asList(new String[] {"команды"}));
		defaultReply = new Reply(Texts.commands);
	}

	@Override
	public Reply reply(String input, String id, Bot bot) {
    	return defaultReply;
	}
}
