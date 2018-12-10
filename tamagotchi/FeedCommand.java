package tamagotchi;

import java.util.Arrays;

public class FeedCommand extends Command {	
	public FeedCommand() {
		synonyms.addAll(Arrays.asList(new String[] {"питание", "еда", "ешь", "покормить", "🍎"}));
		defaultReply = new Reply("Сейчас я буду кушать, сейчас меня покормят!\n");
	}

	@Override
	public Reply reply(UserData user, String input) {
		if (user.pet.canEat && !user.pet.isSleep) {
			user.pet.addHunger(60);
			return new Reply(user.pet.getStates() + defaultReply.getText());
		}
		else {
			return new Reply("Эта команда еще не открыта");
		}
	}
}
