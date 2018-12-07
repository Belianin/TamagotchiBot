package tamagotchi;

public class CreationDialog implements Dialog {

	@Override
	public Reply reply(UserData user, String input) {
		//пока без подтверждения ввода
		
		user.pet = new Pet(input, user);
		user.events.add(new DeathEvent(user.pet));
		Reply reply =  new Reply("Игра началась!", DialogName.Main);
		reply.setButtons(Button.getMainButtons(user.pet));
		return reply;
	}

}
