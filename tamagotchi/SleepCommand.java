//package tamagotchi;

import java.util.Arrays;

public class SleepCommand extends Command {	
	public SleepCommand() {
		synonyms.addAll(Arrays.asList(new String[] {"спать", "сон", "спи", "💤"}));
		defaultReply = new Reply("Я ложусь спать, не беспокой меня несколько минут!");
	}

	@Override
	public Reply reply(UserData user, String input) {
		if (user.pet.canSleep) {
			user.pet.addSleep(100);
			return defaultReply;
		}
		else {
			return new Reply("Эта команда еще не открыта");
		}
	}
}
