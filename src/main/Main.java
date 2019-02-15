package main;

import global.SceneStack;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import view.MainMenu;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(SceneStack.forward(new MainMenu("SOKOBAN").inflate()));
        primaryStage.setTitle("Sokoban");
        primaryStage.getIcons().add(new Image("/sprites/door.png"));
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
