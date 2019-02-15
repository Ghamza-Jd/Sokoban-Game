package view;

import controller.LevelPickerController;
import global.Constants;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

public class LevelPicker extends Page {

    private LevelPickerController controller;

    public LevelPicker(String name) {
        super(name);
        controller = new LevelPickerController();
    }

    @Override
    protected VBox createVBox() {
        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(30);
        return vb;
    }

    @Override
    protected ArrayList<Node> createBody() {
        ArrayList<Node> nodes = new ArrayList<>();
        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setHgap(20);
        gp.setVgap(20);

        Button[][] levels = new Button[3][3];
        for (int i = 0, k = 1; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                levels[i][j] = new Button(String.valueOf(k));
                levels[i][j].setFont(Font.font("Comic Sans MS"));
                int level = k;
                levels[i][j].setOnAction(event -> controller.openLevel(event, level));
                if(!Constants.AVAILABLE_LEVELS[level - 1]){
                    levels[i][j].setDisable(true);
                }
                k++;
                gp.add(levels[i][j], j, i);
            }
        }

        nodes.add(gp);
        return nodes;
    }
}
