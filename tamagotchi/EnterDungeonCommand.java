package tamagotchi;

import java.util.Arrays;

public class EnterDungeonCommand extends Command {
	private Bot bot;
	public EnterDungeonCommand(Bot bot) {
		this.bot = bot;
		synonyms.addAll(Arrays.asList(new String[] { "🏔️ в подзмелье!", "🏔️", "подземелье" }));
	}

	@Override
	public Reply reply(UserData user, String input) {
		if (!user.pet.isSleep) {
			user.events.add(new EncounterEvent(user.pet, bot));
			return new Reply("Питомец пошёл в подземелье.", Button.getNeedsButtons());
		}
		return new Reply("вроде в мейне должно это обрабатыватся");
	}

}
