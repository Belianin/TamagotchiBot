package tamagotchi;

import java.util.Arrays;

public class ToiletCommand extends Command {	
	public ToiletCommand() {
		synonyms.addAll(Arrays.asList(new String[] {"туалет", "🚽"}));
		defaultReply = new Reply("Секундочку, я отлучусь...\n");
	}

	@Override
	public Reply reply(UserData user, String input) {
		if (user.pet.canToilet && !user.pet.isSleep) {
			user.pet.addToilet(100);
			return new Reply(defaultReply.getText() + user.pet.getStates());
		}
		else {
			return new Reply("Эта команда еще не открыта");
		}
	}
}
