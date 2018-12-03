//package tamagotchi;
import java.util.Date;

public abstract class Event {
    public Date when;
    abstract boolean tryApply();
}