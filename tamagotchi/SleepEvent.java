package tamagotchi;

import java.util.Date;

public class SleepEvent extends Event {

    private Pet pet;

    public SleepEvent(Pet pet) {
        this.pet = pet;
        Date currentDate = new Date();
        pet.isSleep = true;
        this.when = new Date(currentDate.getTime() + 10000);
    }

    @Override
    public boolean tryApply() {
        pet.isSleep = false;
        return true;
    }


}
