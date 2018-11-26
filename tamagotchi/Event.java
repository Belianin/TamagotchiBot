package tamagotchi;
import java.util.Date;

public class Event {
    public String What;
    public String Who;
    public Date When;
    public int HowMuch;
    private Bot Bot;
    public String Message;

    public boolean isEmpty() {
        return What == null || Who == null || When == null;
    }
    Event() {
        What = null;
        Who = null;
        HowMuch = 0;
    }
    public Event(String what, String who, Date when, int howMuch, Bot bot) {
        What = what;
        Who = who;
        When = when;
        Bot = bot;
        HowMuch = howMuch;
    }
    Event(String what, String who, Bot bot, Date when) {
        this.What = what;
        this.Who = who;
        When = when;
        Bot = bot;
        HowMuch = getConst(what);
    }
    void apply()  {
        if (!isEmpty()) {
            //System.out.println(Who + " || " + "Повышено: " + What + " на " + HowMuch);
            Bot.getTamagochyMap().get(Who).Up(What, HowMuch);
        }

    }

    int getConst(String what) {
        switch (what) {
            case "hunger": {
                return 20;
            }
            case "sleep": {
                return 100;
            }
            case "toilet": {
                return 50;
            }
            case "fun": {
                return 10;
            }
            case "clean": {
                return 15;
            }
            default:
            	return 0;
        }
    }
}