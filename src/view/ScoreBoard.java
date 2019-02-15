package view;

import controller.ScoreBoardController;
import global.Constants;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import model.ScoreBoardModel;

import java.util.ArrayList;

public class ScoreBoard extends Page {

    private TableView<ScoreBoardModel> tableView;
    private ScoreBoardController controller ;

    public ScoreBoard(String name) {
        super(name);
        controller = new ScoreBoardController(this);
    }

    @Override
    protected ArrayList<Node> createHeader() {
        ArrayList<Node> nodes = new ArrayList<>();
        Button[] levels = new Button[9];
        HBox hb = new HBox();
        hb.setAlignment(Pos.CENTER);
        hb.setSpacing(10);

        for(int i = 0; i < levels.length; i++){
            levels[i] = new Button(String.valueOf(i + 1));
            levels[i].setFont(Font.font("Comic Sans MS"));
            int id = i;
            levels[i].setOnAction(event -> controller.populateTableView(event, id));
            hb.getChildren().add(levels[i]);
        }
        nodes.add(hb);
        return nodes;
    }

    @Override
    protected ArrayList<Node> createBody() {

        ArrayList<Node> nodes = new ArrayList<>();

        tableView = new TableView<>();

        TableColumn<ScoreBoardModel, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<ScoreBoardModel, Integer> moves = new TableColumn<>("Moves");
        moves.setCellValueFactory(new PropertyValueFactory<>("moves"));

        TableColumn<ScoreBoardModel, Integer> level = new TableColumn<>("Level");
        level.setCellValueFactory(new PropertyValueFactory<>("level"));

        {
            name.setPrefWidth(Constants.WINDOW_WIDTH / 3 - 1);
            name.setResizable(false);

            moves.setPrefWidth(Constants.WINDOW_WIDTH / 3 - 1);
            moves.setResizable(false);

            level.setPrefWidth(Constants.WINDOW_WIDTH / 3 - 1);
            level.setResizable(false);
        }

        Pane pane = new Pane();
        pane.getChildren().add(tableView);

        //noinspection unchecked
        tableView.getColumns().addAll(name, moves, level);

        nodes.add(pane);

        return nodes;
    }

    public TableView<ScoreBoardModel> getTableView() {
        return tableView;
    }

    @Override
    protected Button backButton() {
        Button button = super.backButton();
        button.setOnAction(controller::onBackPress);
        return button;
    }
}
