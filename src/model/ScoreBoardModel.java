package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ScoreBoardModel {
    private final SimpleStringProperty name;
    private final SimpleIntegerProperty moves;
    private final SimpleIntegerProperty level;

    public ScoreBoardModel(String name, int moves, int level) {
        this.name = new SimpleStringProperty(name);
        this.moves = new SimpleIntegerProperty(moves);
        this.level = new SimpleIntegerProperty(level);
    }

    public String getName(){
        return name.get();
    }

    public int getMoves(){
        return moves.get();
    }

    public int getLevel(){
        return level.get();
    }
}
