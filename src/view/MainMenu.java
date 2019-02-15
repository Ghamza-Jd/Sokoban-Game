package view;

import controller.MainMenuController;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Optional;

public class MainMenu extends Page {

    public MainMenu(String name) {
        super(name);
    }

    @Override
    protected ArrayList<Node> createBody() {

        MainMenuController controller = new MainMenuController();

        ArrayList<Node> nodes = new ArrayList<>();

        Button play, score, htp;

        ImageView imageView = new ImageView(new Image("/sprites/door.png"));
        imageView.setFitHeight(128);
        imageView.setFitWidth(128);
        nodes.add(imageView);

        play = new Button("Play");
        play.setFont(Font.font("Comic Sans MS"));
        play.setOnAction(controller::play);
        nodes.add(play);

        score = new Button("Score board");
        score.setFont(Font.font("Comic Sans MS"));
        score.setOnAction(controller::scores);
        nodes.add(score);

        htp = new Button("How to play");
        htp.setFont(Font.font("Comic Sans MS"));
        htp.setOnAction(controller::howToPlay);
        nodes.add(htp);

        return nodes;
    }

    @Override
    protected ArrayList<Node> createFooter() {
        ArrayList<Node> nodes = new ArrayList<>();

        Button exit = new Button("Exit");
        Label developer = new Label("Developed by: Hamza Jadid");

        developer.setFont(Font.font("Comic Sans MS", 10));

        exit.setFont(Font.font("Comic Sans MS"));
        exit.setOnAction(event -> {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Exit");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to exit SOKOBAN?");
            alert.setResizable(true);
            alert.getDialogPane().setPrefSize(40,30);
            alert.setResizable(false);

            ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/sprites/door.png"));

            ButtonType yes = new ButtonType("Yes");
            ButtonType no = new ButtonType("No");

            alert.getButtonTypes().setAll(yes, no);
            alert.setWidth(30);
            alert.setHeight(30);

            Optional<ButtonType> result = alert.showAndWait();

            if(!result.isPresent())         alert.close();
            else if(result.get() == yes)    System.exit(0);
            else if(result.get() == no)     alert.close();

        });
        nodes.add(exit);
        nodes.add(developer);
        return nodes;
    }
}
