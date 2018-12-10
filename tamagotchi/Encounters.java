package tamagotchi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import com.google.gson.Gson;

import javax.validation.constraints.AssertFalse;

public abstract class Encounters {
    private static ArrayList<Encounter> encounters = new ArrayList<Encounter>();
    private static HashMap<String, ArrayList<Encounter>> storiesEncounter = new HashMap<String, ArrayList<Encounter>>();
    private static ArrayList<String> stories = new ArrayList<>();
    private static Random random = new Random();

    public static Encounter getEncounter(Pet pet) {
        if (!pet.isStory) {
            if (random.nextBoolean()) {
                return encounters.get(random.nextInt(encounters.size()));
            } else {
                String key = stories.get(random.nextInt(stories.size()));
                pet.storyName = key;
                //storyStart();
                pet.isStory = true;
                pet.storyLevel = 0;
                return storiesEncounter.get(key).get(pet.storyLevel);
            }
        } else {
            if (checkIsStory(pet)) {
                return storiesEncounter.get(pet.storyName).get(pet.storyLevel);
            }
        }
        return encounters.get(random.nextInt(encounters.size()));
    }

    public static void putStoryName(String story) {
        stories.add(story);

    }

    public static boolean checkIsStory(Pet pet) {
        pet.storyLevel += 1;
        if (pet.storyLevel < storiesEncounter.get(pet.storyName).size()) {
            return true;
        } else {
            pet.isStory = false;
            return false;
        }
    }

    public static void addEncounter(Encounter encounter) {
        encounters.add(encounter);
    }

    public static void addStoryEncounter(Encounter encounter, String key) {
        //System.out.println(key);
        if (storiesEncounter.get(key) == null) {
            ArrayList<Encounter> listEnc = new ArrayList<>();
            listEnc.add(encounter);
            storiesEncounter.put(key, listEnc);
        } else {
            storiesEncounter.get(key).add(encounter);

        }

    }
}
