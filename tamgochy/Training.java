import java.util.ArrayList;
import java.util.Date;

public class Training extends Event {
    StringBuilder messages;
    Pet pet;

    Training(String id, Date date, Pet pet, String what) {
        this.Who = id;
        this.When = date;
        this.What = what;
        this.pet = pet;
        messages = pet.messages;
    }

    public void apply() {
        messages.append(this.Message);
        switch (What) {
            case "toilet": {
                pet.canToilet = true;
                return;
            }
            case "hungry": {
                pet.canEat = true;
                return;
            }
            case "sleep": {
                pet.canSleep = true;
                return;
            }
            case "fun": {
                pet.canFun = true;
                return;
            }
            case "clean": {
                pet.canClean = true;

            }
        }
    }
}
