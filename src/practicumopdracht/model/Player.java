package practicumopdracht.model;
import java.io.Serializable;
import java.time.LocalDate;


public class Player implements Serializable {
    private String naam;
    private int geldverdiend;
    private LocalDate geboorteDatum;
    private boolean gesponserd;

    public Player() {
    }

    public Player(String naam, int earnings, LocalDate geboortedatum, boolean sponser) {
        this.naam = naam;
        this.geldverdiend = earnings;
        this.geboorteDatum = geboortedatum;
        this.gesponserd = sponser;
    }

    public String getNaam() { return naam;}

    public void setNaam(String naam) { this.naam = naam; }

    public int getAantalKeerGewonnen() { return geldverdiend; }

    public void setAantalKeerGewonnen(int aantalKeerGewonnen) { this.geldverdiend = aantalKeerGewonnen; }

    public LocalDate getGeboorteDatum() {
        return geboorteDatum;
    }

    public void setGeboorteDatum(LocalDate geboorteDatum) {
        this.geboorteDatum = geboorteDatum;
    }

    public boolean isGesponserd() {
        return gesponserd;
    }

    public void setGesponserd(boolean gesponserd) {
        this.gesponserd = gesponserd;
    }

    public void setGeldverdiend(int geldverdiend) {
        this.geldverdiend = geldverdiend;
    }

    public int getGeldverdiend() {
        return geldverdiend;
    }




    @Override
    public String toString(){
    return String.format( this.naam + " heeft  "+ this.geldverdiend +" verdiend en is geboren op "+ this.geboorteDatum +" | Gesponserd? "+this.gesponserd);
}
}

