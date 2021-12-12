package practicumopdracht.model;
import practicumopdracht.data.EarningData;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Earnings implements Serializable {
private int aantalEvents;
private String volledigeNaam;
private LocalDate laatsGespeeld;
private Player player;
private EarningData earningData;

    public Earnings(int aantalEvents, String volledigeNaam, LocalDate laatsGespeeld) {
        this.aantalEvents = aantalEvents;
        this.volledigeNaam = volledigeNaam;
        this.laatsGespeeld = laatsGespeeld;
    }



    public int getAantalEvents() {
        return aantalEvents;
    }

    public String getVolledigeNaam() {
        return volledigeNaam;
    }

    public LocalDate getLaatsGespeeld() {
        return laatsGespeeld;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
        Earnings earnings = new Earnings(aantalEvents,volledigeNaam,laatsGespeeld);
        earnings.setPlayer(player);
        earningData.add(earnings);
    }

    @Override
    public String toString(){
        return String.format(volledigeNaam + " |" + laatsGespeeld + " laatst gespeeld | "+ aantalEvents +" events");

    }



}
