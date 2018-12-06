package tamagotchi;

import java.util.Arrays;

public class EnterDungeonCommand extends Command {
	public EnterDungeonCommand() {
		synonyms.addAll(Arrays.asList(new String[] {"🏔️ в подзмелье!", "🏔️", "подземелье"}));
	}
	@Override
	public Reply reply(UserData user, String input) {
		user.events.add(new EncounterEvent(user.pet));
		return new Reply("Питомец пошёл в подземелье.", Button.getNeedsButtons());
	}

}
