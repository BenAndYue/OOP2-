package practicumopdracht.controller;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import practicumopdracht.MainApllication;
import practicumopdracht.data.PlayerDAO;
import practicumopdracht.model.Player;
import practicumopdracht.views.PlayerView;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class PlayerController {

    private Player player;
    private PlayerView playerView;
    private ArrayList<Player> players;
    private PlayerDAO playerDAO;
    private EarningController earningController;
    private Scene scene;
    private Player geselecteerde;

    public PlayerController() {
        playerDAO = new PlayerDAO(null);
        playerView = new PlayerView();
        player = new Player();
        // buttons koppelen aan handles
        playerView.getAddButton().setOnAction(e -> addPlayerToList());
        playerView.getRemoveButton().setDisable(true);
        playerView.getRemoveButton().setOnAction(e -> removePlayer());
        playerView.getClear().setOnAction(e -> resetfield());

        //Nieuwe knoppen voor bestand
        playerView.getSaveMenuu().setOnAction(e -> handleSave());
        playerView.getLoadMenuu().setOnAction(e -> readPlayerlist());
        playerView.getCloseMenuu().setOnAction(e -> closeHandle());

        //knoppen voor het sorteren
        playerView.getSorteeropearnings().setOnAction(e -> sortEarnings());
        playerView.getSorteeropnaam().setOnAction(e -> sortnaam());
        playerView.getSorteeropgeboortedaum().setOnAction(e -> sortleeftijd());
        playerView.getSorteeropnaamturn().setOnAction(e -> sortturnleeftijd());

        //detail knop
        playerView.getDetail().setDisable(true);
        playerView.getDetail().setOnAction(this::handleDetails);

        playerView.getPlayerListView().getSelectionModel().selectedItemProperty().addListener(this::handleSelect);
        earningController = new EarningController(this);

    //    playerView.getPlayerListView().getSelectionModel().selectedItemProperty().addListener((observableValue, oudeplayer, nieuweplayer) -> {
      //      if (oudeplayer == nieuweplayer) {
        //        return;
          //  }
            //bijwerken(nieuweplayer);

       // });

        scene = new Scene(playerView.getGridPane());

        readPlayerlist();
        refreshData();
    }

    private void bijwerken(Player nieuweplayer) {
        playerView.getNaamInvoerVeld().setText(player.getNaam());
        playerView.getGeboorteDatumveld().setValue(player.getGeboorteDatum());
        playerView.getWinInvoerVeld().setText(Integer.toString(player.getAantalKeerGewonnen()));
        playerView.getGesponserdCheck().setSelected(player.isGesponserd());
    }

    private void sortturnleeftijd() {
        playerDAO.sortturnnaam();
        refreshData();
    }

    private void sortEarnings() {
        playerDAO.sorteerearnings();
        refreshData();
    }

    private void sortnaam() {
        playerDAO.sorteernaam();
        refreshData();
    }

    private void sortleeftijd() {
        playerDAO.sorteerleeftijd();
        refreshData();
    }

    private void closeHandle() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Afsluiten confirmation");
        alert.setContentText("Als u het programma gaat afsluiten wordt er gevraagd of i alles wilt opslaan?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            playerDAO.schrijfPlayerToDisk();
            handleSave();
            Platform.exit();
        }

    }

    private void readPlayerlist() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("load last data");
        alert.setContentText("Wilt u de data van de vorige keer terug halen?");

        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            playerDAO.leesPlayerFromDisk();
            sortEarnings();
            refreshData();
        }
        sortEarnings();
    }


    //naar detail gaan van de het atribuut in de listview
    private void handleDetails(ActionEvent actionEvent) {
        Player player = playerView.getPlayerListView().getSelectionModel().getSelectedItem();
        earningController.displayView(player);
    }


    private void addPlayerToList() {

        String waarschuwingBerichtDefault = "Er is een fout in het formulier: ";
        String waarschuwingBericht = waarschuwingBerichtDefault;


        if (playerView.getNaamInvoerVeld().getText().trim().isEmpty()) {
            waarschuwingBericht += "\n\t- Geen naam ingvuld.";
        }
        if (playerView.getGeboorteDatumveld().getValue() == null) {
            waarschuwingBericht += "\n\t-Geen geboortedatum ingevuld.";
        } else {
            LocalDate tijdNu = LocalDate.now();
            if (playerView.getGeboorteDatumveld().getValue().compareTo(tijdNu) > 0) {
                waarschuwingBericht += "\n\t- Speler is in de toekomst geboren.";
            }
        }

        if (playerView.getNaamInvoerVeld().getText().matches("\\d{0,7}([\\.]\\d{0,4})?")) {
            waarschuwingBericht += "\n\t-U heeft illegale tekens bij de naam staan.";
        }

        if (!waarschuwingBericht.equals(waarschuwingBerichtDefault)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Zorg ervoor dat u alle data wel goed inzet." + "\n"
                    + waarschuwingBericht);
            alert.show();
            return;
        }


        String naam = playerView.getNaamInvoerVeld().getText();
        int geldverdiend;
        try {
            geldverdiend = Integer.parseInt(playerView.getWinInvoerVeld().getText());
            LocalDate geboortedatum = playerView.getGeboorteDatumveld().getValue();
            boolean gesponderd = playerView.getGesponserdCheck().isSelected();

            //Maakt nieuw object en voegt did to aan de lijst.
            player = new Player(naam, geldverdiend, geboortedatum, gesponderd);
            playerDAO.add(new Player(naam, geldverdiend, geboortedatum, gesponderd));

            //Leegt alle velden nadat ze opgeslagen zijn.
            resetfield();

        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Zorg ervoor dat u alle data wel goed inzet." + ex);
            alert.show();
        }
        //werkt nog niet
        if (geselecteerde != null) {
            player.setNaam(geselecteerde.getNaam());
        }

        refreshData();
    }

    private void resetfield() {
        playerView.getWinInvoerVeld().clear();
        playerView.getNaamInvoerVeld().clear();
        playerView.getGesponserdCheck().setSelected(false);
        playerView.getGeboorteDatumveld().setValue(null);
    }


    //verwijdert speler uit de lijst.
    private void removePlayer() {
        Player selectplayer = playerView.getPlayerListView().getSelectionModel().getSelectedItem();
        Player player = playerView.getPlayerListView().getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("verwijderen?");
        alert.setContentText("Weet je zeker dat je dit wilt verwijderen?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            playerDAO.remove(selectplayer);
            refreshData();
        }


    }

    private void refreshData() {
        ObservableList<Player> PlayerObservableList = FXCollections.observableList(playerDAO.getAll());
        playerView.getPlayerListView().setItems(PlayerObservableList);

    }

    private void handleSave() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Opslaan?");
        alert.setContentText("Alles opslaan?");

        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            playerDAO.schrijfPlayerToDisk();
            refreshData();
        }

    }

    private void handleSelect(Observable observable) {
        playerView.getDetail().setDisable(false);
        playerView.getRemoveButton().setDisable(false);
    }

    public PlayerView getPlayerView() {
        return playerView;
    }

    public void displayView() {
        MainApllication.setStage(scene);
    }


    public Player lookup(String naam) {
        for (Player player : playerDAO.getAll()) if (player.getNaam().equals(naam)) return player;
        return null;
    }
}