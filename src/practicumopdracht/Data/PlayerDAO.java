package practicumopdracht.data;

import practicumopdracht.comparator.earningsComparator;
import practicumopdracht.comparator.leeftijdComparator;
import practicumopdracht.comparator.naamComparator;
import practicumopdracht.comparator.naamturnComparator;
import practicumopdracht.model.Earnings;
import practicumopdracht.model.Player;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;



public class PlayerDAO implements DAO<Player> {
    private ArrayList<Player> data;
    private ArrayList<Player> playerList;
    private static final String FILENAME = "Player.txt";

    public PlayerDAO(ArrayList<Player> playerList) {
        this.playerList = playerList;
        data = new ArrayList<>();
        load();
    }

    @Override
    public ArrayList<Player> getAll() {
        return playerList;
    }

    @Override
    public void add(Player player) {
        playerList.add(player);
    }

    @Override
    public void remove(Player player) {
        playerList.remove(player);
    }

    public void addOrUpdate(Player object) {
        playerList.add(object);
    }

    @Override
    public boolean load() {
        playerList = new ArrayList<>();
        return false;
    }

    //tekst
    public void schrijfPlayerToDisk() {
        try (PrintWriter printWriter = new PrintWriter(new File(FILENAME))) {
            for (Player player : playerList) {
                printWriter.print(player.getNaam() + ",");
                printWriter.print(player.getGeldverdiend() + ",");
                printWriter.print(player.getGeboorteDatum() + ",");
                printWriter.println(player.isGesponserd());
            }
        } catch (Exception ex) {
            System.out.println("Oeps iets ging niet goed.");
        }
    }




    public void leesPlayerFromDisk() {
        try (Scanner scanner = new Scanner(new File(FILENAME))) {
            while (scanner.hasNextLine()) {
                String regel = scanner.nextLine();
                String[] playerInfo = regel.split(",");
                Player player = new Player(playerInfo[0], Integer.parseInt(playerInfo[1]), LocalDate.parse(playerInfo[2]), Boolean.parseBoolean(playerInfo[3]));
                addOrUpdate(player);
            }
        } catch (Exception ex) {
            System.out.println("Oeps iets ging niet goed." + ex);
        }
    }

    public void sorteernaam(){
        Collections.sort(playerList, new naamComparator());
    }
    public void sorteerearnings(){
        Collections.sort(playerList, new earningsComparator());
    }
    public void sorteerleeftijd(){
        Collections.sort(playerList,new leeftijdComparator());
    }
    public void sortturnnaam() { Collections.sort(playerList,new naamturnComparator());   }
}

