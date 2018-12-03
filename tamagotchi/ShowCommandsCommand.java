//package tamagotchi;

import java.util.Arrays;

public class ShowCommandsCommand extends Command {	
	public ShowCommandsCommand() {
		synonyms.addAll(Arrays.asList(new String[] {"команды", "помощь", "help"}));
		defaultReply = new Reply(Texts.commands);
	}

	@Override
	public Reply reply(UserData user, String input) {
    	return defaultReply;
	}
}
