package tamagotchi;

import java.util.Date;
import java.util.Random;

public class EncounterEvent extends Event {
	public static int timeToNext = 10000;
	private Pet pet;
	private Bot bot;
	
	public EncounterEvent(Pet pet, Bot bot)
	{
		this.bot = bot;
		pet.InDungeon = true;
		this.pet = pet;
		Date currentDate = new Date();
        this.when = new Date(currentDate.getTime() + timeToNext);
	}
	@Override
	boolean tryApply() {
		if (pet.isSleep)
			return false;
		if (!pet.InDungeon) {
			reply = new Reply("Питомец вернулся домой");
			when = new Date(when.getTime() + timeToNext);
			return true;
		}
		Encounter encounter = Encounters.getEncounter(pet, bot);
		reply = encounter.act(pet);

		
		when = new Date(when.getTime() + timeToNext);
		return false;
	}

}
