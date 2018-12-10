package tamagotchi;

import java.util.Arrays;

public class CleanCommand extends Command {	
	public CleanCommand() {
		synonyms.addAll(Arrays.asList(new String[] {"гигиена", "мыться", "мойся", "💦"}));
		defaultReply = new Reply("А сейчас я хочу расслабиться и принять ванну.\n");
	}

	@Override
	public Reply reply(UserData user, String input) {
		if (user.pet.canClean && !user.pet.isSleep) {
			user.pet.addClean(100);
			return new Reply(defaultReply.getText() + user.pet.getStates());
		}
		else {
			return new Reply("Эта команда еще не открыта");
		}
	}
}
