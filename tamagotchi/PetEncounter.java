package tamagotchi;

public class PetEncounter extends Encounter {
	private Pet anotherPet;
	
	public PetEncounter(Pet anotherPet) {
		this.anotherPet = anotherPet;
	}
	
	public Reply act(Pet pet) {
		return new Reply("Вот это встреча, да это же " + anotherPet.name + ".");
	}

}
