package controller;

import global.SceneStack;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import view.Level;

public class LevelPickerController {

    public void openLevel(ActionEvent event, int level){
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        //To test the Scene stack function uncomment the following line of code:
        //stage.setScene(SceneStack.forward(new LevelPicker("Scene " + level).inflate()));

        //and comment the following line the following line of code:
        stage.setScene(SceneStack.forward(new Level("Level " + level, level).inflate()));
    }
}
