package tamagotchi;

import java.util.Arrays;

public class HealCommand extends Command {
	public HealCommand() {
		synonyms.addAll(Arrays.asList(new String[] {"здоровье", "жизни", "💊"}));

		defaultReply = new Reply("Лечу свои раны...\n");
	}

	@Override
	public Reply reply(UserData user, String input) {
		if (user.pet.canFun && !user.pet.isSleep) {
			user.pet.addHealth(40);
			return new Reply(user.pet.getStates() + defaultReply.getText());
		}
		else {
			return new Reply("Эта команда еще не открыта");
		}
	}
}
