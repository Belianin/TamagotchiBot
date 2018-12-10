package tamagotchi;

import java.util.Arrays;

public class SleepCommand extends Command {	
	public SleepCommand() {
		synonyms.addAll(Arrays.asList(new String[] {"спать", "сон", "спи", "💤"}));
		defaultReply = new Reply("Я ложусь спать, не беспокой меня несколько минут!\n");
	}

	@Override
	public Reply reply(UserData user, String input) {
		if (user.pet.canSleep && !user.pet.isSleep) {
			user.pet.addSleep(100);
			user.events.add(new SleepEvent(user.pet));
			return new Reply(defaultReply.getText() + user.pet.getStates());
		}
		else {
			return new Reply("Эта команда еще не открыта");
		}
	}
}
