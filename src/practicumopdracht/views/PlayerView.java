package practicumopdracht.views;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import practicumopdracht.model.Player;


public class PlayerView {
    private TextField naamInvoerVeld;
    private TextField winInvoerVeld;
    private DatePicker geboorteDatumveld;
    private CheckBox gesponserdCheck;
    private ListView<Player> playerListView;
    private GridPane gridPane;
    private Button addButton;
    private Button removeButton;
    private Button detail;
    private SplitMenuButton split;
    private MenuItem saveMenuu;
    private MenuItem loadMenuu;
    private MenuItem closeMenuu;
    private SplitMenuButton sorteer;
    private MenuItem sorteeropnaam;
    private MenuItem sorteeropnaamturn;
    private MenuItem sorteeropearnings;
    private MenuItem sorteeropgeboortedaum;
    private Button clear;


    public Button getClear() {
        return clear;
    }

    public PlayerView() {

        this.split = new SplitMenuButton();
        this.sorteer = new SplitMenuButton();
        this.winInvoerVeld = new TextField();
        this.addButton = new Button("Toevoegen");
        this.removeButton = new Button("Verwijderen");
        this.naamInvoerVeld = new TextField();
        this.geboorteDatumveld = new DatePicker();
        this.gesponserdCheck = new CheckBox("Gesponderd?");
        this.playerListView = new ListView<>();
        this.gridPane = new GridPane();
        this.detail = new Button("Details");
        this.closeMenuu = new MenuItem("close");
        this.loadMenuu = new MenuItem("laad");
        this.saveMenuu = new MenuItem("save");
        this.sorteeropearnings = new MenuItem("Earnings");
        this.sorteeropgeboortedaum = new MenuItem("Leeftijd");
        this.sorteeropnaam = new MenuItem("A-Z");
        this.sorteeropnaamturn = new MenuItem("Z-A");
        this.clear = new Button("Leeg maken");
        schermLayout();
    }


    private void schermLayout() {
        split.setText("Bestand");
        sorteer.setText("Sorteren");
        Label earningLabel = new Label("Hoeveel earnings?");
        Label playerLabel = new Label("Naam van de Speler");
        Label geboorteLabel = new Label("Geboortedatum");
        Label listview = new Label("    De lijst:");
        gridPane.add(split, 11, 0);
        split.getItems().addAll(loadMenuu, saveMenuu, closeMenuu);
        gridPane.add(sorteer,1,6);
        sorteer.getItems().addAll(sorteeropnaam, sorteeropnaamturn, sorteeropgeboortedaum,sorteeropearnings);
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.add(clear,3,0);
        gridPane.add(playerLabel, 0, 0);
        gridPane.add(naamInvoerVeld, 1, 0);
        gridPane.add(earningLabel, 0, 1);
        gridPane.add(winInvoerVeld, 1, 1);
        gridPane.add(geboorteLabel, 0, 2);
        gridPane.add(geboorteDatumveld, 1, 2);
        gridPane.add(gesponserdCheck, 1, 3);
        gridPane.add(listview, 0, 6);
        gridPane.add(playerListView, 1, 7, 4, 1);
        gridPane.add(detail, 2, 6);
        gridPane.add(addButton, 1, 4);
        gridPane.add(removeButton, 2, 4);

    }

    public Button getAddButton() {
        return addButton;
    }

    public TextField getNaamInvoerVeld() {
        return naamInvoerVeld;
    }

    public TextField getWinInvoerVeld() {
        return winInvoerVeld;
    }

    public DatePicker getGeboorteDatumveld() {
        return geboorteDatumveld;
    }

    public CheckBox getGesponserdCheck() {
        return gesponserdCheck;
    }

    public ListView<Player> getPlayerListView() {
        return playerListView;
    }

    public Button getRemoveButton() {
        return removeButton;
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public Button getDetail() {
        return detail;
    }


    public MenuItem getSaveMenuu() {
        return saveMenuu;
    }

    public MenuItem getLoadMenuu() {
        return loadMenuu;
    }

    public MenuItem getCloseMenuu() {
        return closeMenuu;
    }



    public MenuItem getSorteeropnaam() {
        return sorteeropnaam;
    }

    public MenuItem getSorteeropearnings() {
        return sorteeropearnings;
    }

    public MenuItem getSorteeropgeboortedaum() {
        return sorteeropgeboortedaum;
    }

    public MenuItem getSorteeropnaamturn() {
        return sorteeropnaamturn;
    }
}

