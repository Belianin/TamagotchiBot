package tamagotchi;

import java.util.Arrays;

public class LeaveDungeonCommand extends Command {
	public LeaveDungeonCommand() {
		synonyms.addAll(Arrays.asList(new String[] { "🚪 домой!", "🚪", "домой" }));
	}

	@Override
	public Reply reply(UserData user, String input) {
		if (!user.pet.isSleep) {
			user.pet.InDungeon = false;
			return new Reply("Питомец идёт домой.", Button.getNeedsButtons());
		}
		return new Reply("вроде в мейне должно это обрабатыватся");
	}

}
