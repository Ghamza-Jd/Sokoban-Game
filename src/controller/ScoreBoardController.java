package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import model.ScoreBoardModel;
import model.ScoresDatabase;
import view.ScoreBoard;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ScoreBoardController extends PageController {
    private ScoreBoard scoreBoard;
    private ScoresDatabase scoresDatabase;

    private final ObservableList<ScoreBoardModel> data = FXCollections.observableArrayList();

    public ScoreBoardController(ScoreBoard scoreBoard){
        super(scoreBoard);
        this.scoreBoard = scoreBoard;
        scoresDatabase = new ScoresDatabase();
    }

    public void populateTableView(ActionEvent event, int id){
        try{
            ResultSet resultSet = scoresDatabase.getLevelHighScores(id + 1);
            data.clear();
            while(resultSet.next()){
                data.add(new ScoreBoardModel(resultSet.getString(1), resultSet.getInt(2), resultSet.getInt(3)));
            }
            scoreBoard.getTableView().setItems(data);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPress(ActionEvent event) {
        try {
            scoresDatabase.closeDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.onBackPress(event);
    }
}
