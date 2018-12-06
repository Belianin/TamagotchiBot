package tamagotchi;

import java.util.Date;

public class EncounterEvent extends Event {
	public static int timeToNext = 5000;
	private Pet pet;
	
	public EncounterEvent(Pet pet)
	{
		pet.InDungeon = true;
		this.pet = pet;
		Date currentDate = new Date();
        this.when = new Date(currentDate.getTime() + timeToNext);
	}
	@Override
	boolean tryApply() {
		if (!pet.InDungeon) {
			reply = new Reply("Питомец вернулся домой");
			when = new Date(when.getTime() + timeToNext);
			return true;
		}
		reply = new Reply("Что-то произошло в путешествии");
		when = new Date(when.getTime() + timeToNext);
		return false;
	}

}
