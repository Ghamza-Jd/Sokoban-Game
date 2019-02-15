package view;

import global.Constants;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class HowToPlay extends Page {

    public HowToPlay(String name) {
        super(name);
    }

    @Override
    protected ArrayList<Node> createBody() {

        GridPane gp;
        ImageView box, keys, target;
        Label rule_one, rule_two, rule_three, space;

        gp = new GridPane();

        rule_one = new Label("Move boxes by pushing them using the arrow key.");
        rule_one.setFont(Font.font("Comic Sans MS", 16));

        rule_two = new Label("You can only push one box at a time.");
        rule_two.setFont(Font.font("Comic Sans MS", 16));

        rule_three = new Label("The level is won when all the targets are covered.");
        rule_three.setFont(Font.font("Comic Sans MS", 16));

        space = new Label("");
        space.setMinHeight(40);

        box = new ImageView(new Image("/sprites/box.png"));
        box.setFitWidth(Constants.GRID_WIDTH);
        box.setFitHeight(Constants.GRID_HEIGHT);

        GridPane.setHalignment(box, HPos.CENTER);

        keys = new ImageView(new Image("/sprites/arrows.png"));
        keys.setFitWidth(Constants.GRID_WIDTH * 2);
        keys.setFitHeight(Constants.GRID_HEIGHT * 2);

        target = new ImageView(new Image("/sprites/target.png"));
        target.setFitWidth(Constants.GRID_WIDTH * 2);
        target.setFitHeight(Constants.GRID_HEIGHT * 2);

        gp.setAlignment(Pos.CENTER);
        gp.setHgap(20);
        gp.setVgap(30);


        gp.add(keys, 0, 0);
        gp.add(rule_one, 1, 0);

        gp.add(box, 0, 1);
        gp.add(rule_two, 1, 1);

        gp.add(target, 0, 2);
        gp.add(rule_three, 1, 2);

        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(gp);

        return nodes;
    }

}
