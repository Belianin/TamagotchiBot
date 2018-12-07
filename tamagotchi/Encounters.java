package tamagotchi;

import java.util.ArrayList;
import java.util.Random;

import com.google.gson.Gson;

public abstract class Encounters {
	private static ArrayList<Encounter> encounters = new ArrayList<Encounter>();
	private static Random random = new Random();
	
	public static Encounter getRandom() {
		return encounters.get(random.nextInt(encounters.size()));
	}
	
	public static void addEncounter(Encounter encounter) {
		encounters.add(encounter);
	}
}
