package controller;

import global.Constants;
import global.SceneStack;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import view.*;

import java.util.Optional;

public class MainMenuController {

    public void play(ActionEvent event){
        TextInputDialog name = new TextInputDialog("Enter you name");
        name.setHeaderText(null);
        name.setTitle("Enter your name");
        ((Stage) name.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/sprites/door.png"));
        Optional<String> result = name.showAndWait();
        if (result.isPresent()) {
            Constants.setName(name.getEditor().getText());
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneStack.forward(new LevelPicker("Levels").inflate()));
        }
    }

    public void scores(ActionEvent event){
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(SceneStack.forward(new ScoreBoard("High Scores").inflate()));
    }

    public void howToPlay(ActionEvent event){
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(SceneStack.forward(new HowToPlay("How to play").inflate()));
    }

}
