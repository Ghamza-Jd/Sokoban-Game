package view;

import controller.PageController;
import global.Constants;
import global.SceneStack;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.ScoresDatabase;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public abstract class Page {

    private String name;
    private PageController controller;
    private VBox vb;

    Page(String name){
        this.name = name;
        controller = new PageController(this);
    }

    protected VBox createVBox(){
        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(10);
        return vb;
    }

    protected Button backButton(){
        Button back;
        back = new Button("Back");
        back.setFont(Font.font("Comic Sans MS"));
        back.setOnAction(controller::onBackPress);
        return back;
    }

    protected ArrayList<Node> createHeader(){
        Label title = new Label(name);
        title.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.ITALIC, 32));
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(title);
        return nodes;
    }

    protected ArrayList<Node> createFooter(){
        Button back = backButton();
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(back);
        return nodes;
    }

    public Scene inflate(){
        vb = createVBox();

        ArrayList<Node> nodes;

        if((nodes = createHeader()) != null) {
            for(Node n : nodes)
                vb.getChildren().add(n);
        }

        if((nodes = createBody()) != null) {
            for(Node n : nodes)
                vb.getChildren().add(n);
        }

        if((nodes = createFooter()) != null) {
            for (Node n : nodes)
                vb.getChildren().add(n);
        }
        return new Scene(vb, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
    }

    protected abstract ArrayList<Node> createBody();
}
