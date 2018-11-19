package tamgochy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

public class Bot {
    private HashMap<String, Pet> TamagochyMap;
    private ArrayList<Event> Events;
    private int DeathTime = 10;

    public ArrayList<Event> getEvents() {
        return Events;
    }

    public HashMap<String, Pet> getTamagochyMap() {
        return TamagochyMap;
    }

    public void setTamagochyMap(String id, Pet pet) {
        TamagochyMap.put(id, pet);
    }

    public void setEvents(Event event) {
        Events.add(event);
    }

    public void removeEvent() {
        Events.remove(0);
    }


    Bot() {
        TamagochyMap = new HashMap<String, Pet>();
        Events = new ArrayList<Event>();
    }

    public String reply(String input, String ID) {
    	//handleEvent(input, ID);
        if (!TamagochyMap.containsKey(ID) || !TamagochyMap.get(ID).alive) {
            Pet needs = new Pet();
            TamagochyMap.put(ID, needs);
            return "Я родился!";
        }
        if (!TamagochyMap.get(ID).alive) {
            return "Кажется, это конец... Чтобы начать новую игру, введи любое сообщение";
        }
        input = input.toLowerCase();
        switch (input) {
            case "питание": {
                return "Сейчас я буду кушать, сейчас меня покормят!";
            }
            case "сон": {
                return "Я ложусь спать, не беспокой меня несколько минут!";
            }
            case "туалет": {
                return "Секундочку, я отлучусь...";
            }
            case "счастье": {
                return "Я так рад, что ты у меня есть!";
            }
            case "гигиена": {
                return "А сейчас я хочу расслабиться и принять ванну";
            }
            case "команды": {
                return Texts.commands;
            }
            case "проверка": {
                return TamagochyMap.get(ID).getStates();
            }
            default:
                return "Я не знаю такой команды! Если хочешь узнать список доступных, введи \"Команды\"";
        }

    }

    public int getDeathCount(Date date) {
        Date currentDate = new Date();
        long difference = currentDate.getTime() - date.getTime();
        int sec = (int) (difference / 1000);
        return Math.abs(sec / DeathTime);
    }

    public void handleEvent(String input, String ID) {
        if (Arrays.asList(Texts.needs).contains(input) && TamagochyMap.containsKey(ID)) {
            Event event = new Event(input, ID, this, new Date());
            Events.add(event);
        }
        if (!TamagochyMap.isEmpty() && TamagochyMap.get(ID).alive) {
            Date currentDate = new Date();
            int coeff = getDeathCount(TamagochyMap.get(ID).getLastUpdate());
            if (coeff > 0) {
                Event death = new Death(this, ID, coeff);
                Events.add(death);
            }
            if (Events.size() != 0) {
                for (int i = Events.size() - 1; i >= 0; i--) {
                    {
                        if (Events.get(i).When.before(currentDate)) {
                            Events.get(i).apply();
                            Events.remove(i);
                        }
                    }
                }
            }
        }
    }
}




