package chooser;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.ToggleGroup;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class chooserMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        // set up stage
        primaryStage.setTitle("Random Supernatural Episode Picker");

        // set up grid pane
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(20);

        // make episode type selector buttons
        RadioButton epAll = new RadioButton("All episodes");
        RadioButton epReg = new RadioButton("Normal episodes only");
        RadioButton epCrk = new RadioButton("Crack/Meta episodes only");
        // add to a toggle group so only one is selected at a time
        ToggleGroup epToggle = new ToggleGroup();
        epAll.setToggleGroup(epToggle);
        epCrk.setToggleGroup(epToggle);
        epReg.setToggleGroup(epToggle);
        //select all episodes by default
        epAll.setSelected(true);
        // add to layout
        VBox epGroup = new VBox(epAll, epReg, epCrk);

        // make empty array of seaons
        ObservableList<String> seasons = FXCollections.observableArrayList();
        // loop and add all the season numbers to array
        for (int i = 1; i < 16; i++) {
            seasons.add("Season " + i);
        }

        // make list of seasons from array
        ListView<String> seasonsGroup = new ListView<String>(seasons);
        // make it able to select multiple & select all season by default
        seasonsGroup.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        seasonsGroup.getSelectionModel().selectAll();

        // get the list view to only be the height of 15 rows
        // why don't they have a feature for this??
        seasonsGroup.setFixedCellSize(25);
        seasonsGroup.setPrefHeight(seasons.size() * seasonsGroup.getFixedCellSize() + 2);
        
        // make area for generated episode to appear
        Text episode = new Text();
        episode.setWrappingWidth(200);
        episode.setTextAlignment(TextAlignment.CENTER);
        episode.setText("hello this is supposed to be text");

        // make buttons for "generate" and "close"
        Button generate = new Button("Generate");
        Button close = new Button("Close");
        // add to a button bar
        HBox buttonGroup = new HBox(8);
        buttonGroup.getChildren().addAll(generate, close);
        buttonGroup.setAlignment(Pos.CENTER);

        // add rows to grid pane
        grid.add(seasonsGroup, 0, 0, 1, 3); // top left, takes up 3 rows
        grid.add(epGroup, 1, 0); // top right
        grid.add(buttonGroup, 1, 1); // middle right
        grid.add(episode, 1, 2); // bottom right
        
        
        // make scene and display stage
        Scene scene = new Scene(grid, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
