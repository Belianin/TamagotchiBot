package tamagotchi;

public class Encounter {
	//опционально
	private String message;
	
	private int requiredHunger;
	private int requiredHealth;
	private int requiredSleep;
	private int requiredClean;
	private int requiredToilet;

	private int successHunger;
	private int successHealth;
	private int successSleep;
	private int successClean;
	private int successToilet;
	private int reward;
	private String successMessage = "Что-то произошло в путешествии";
	
	private int onFailHunger;
	private int onFailHealth;
	private int onFailSleep;
	private int onFailClean;
	private int onFailToilet;
	private String failMessage = "Что-то пошло не так в путешествии";

	private boolean checkParams(Pet pet) {
		return pet.getClean() >= requiredClean && pet.getHealth() >= requiredHealth && pet.getHunger() >= requiredHunger
				&& pet.getSleep() >= requiredSleep && pet.getToilet() >= requiredToilet;
	}

	private Reply success(Pet pet) {
		pet.master.money += reward;
		
		pet.addClean(successClean);
		pet.addHealth(successHealth);
		pet.addHunger(successHunger);
		pet.addSleep(successSleep);
		pet.addToilet(successToilet);
		
		if (message != null)
			return new Reply(message + "\n" + successMessage);
		return new Reply(successMessage);
	}

	private Reply fail(Pet pet) {
		
		pet.addClean(onFailClean);
		pet.addHealth(onFailHealth);
		pet.addHunger(onFailHunger);
		pet.addSleep(onFailSleep);
		pet.addToilet(onFailToilet);
		
		if (message != null)
			return new Reply(message + "\n" + failMessage);
		return new Reply(failMessage);
	}

	public Reply act(Pet pet) {
		if (checkParams(pet)) {
			return success(pet);
		} else {
			return fail(pet);
		}
	}
}
