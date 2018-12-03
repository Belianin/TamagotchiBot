package tamagotchi;

import java.util.Arrays;

public class CleanCommand extends Command {	
	public CleanCommand() {
		synonyms.addAll(Arrays.asList(new String[] {"гигиена", "мыться", "мойся", "💦"}));
		defaultReply = new Reply("А сейчас я хочу расслабиться и принять ванну");
	}

	@Override
	public Reply reply(UserData user, String input) {
		if (user.pet.canClean && !user.pet.isSleep) {
			user.pet.addClean(40);
			return defaultReply;
		}
		else {
			return new Reply("Эта команда еще не открыта");
		}
	}
}
