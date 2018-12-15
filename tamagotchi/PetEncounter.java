package tamagotchi;

public class PetEncounter extends Encounter {
	private Pet anotherPet;
	
	public PetEncounter(Pet anotherPet) {
		this.anotherPet = anotherPet;
	}
	
	public Reply act(Pet pet) {
		Reply reply =  new Reply("Вау, " + pet.name + " встретил в своем путешествии " + anotherPet.name + ", вот так да!");
		reply.anotherId = anotherPet.master.id;
		
		return reply;
	}

}
