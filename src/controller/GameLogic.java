package controller;

import global.Constants;
import global.Direction;
import global.SceneStack;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.MatrixManipulation;
import model.ScoresDatabase;
import view.Level;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class GameLogic extends PageController {

    private int [][] level_static, level_dynamic;
    private MatrixManipulation matrix;
    private Boxes boxes;
    private int counter;
    private Level level;

    public GameLogic(int [][] level_static, int [][] level_dynamic, Level level){
        super(level);
        counter = 0;
        this.level = level;
        this.level_static = level_static;
        this.level_dynamic = level_dynamic;
        matrix = new MatrixManipulation(level_static, level_dynamic);
        boxes = new Boxes();
    }

    private void getLevelMatrixById(int id){
        switch (id){
            case 1:
                level_static    = Constants.LEVEL_ONE_STATIC;
                level_dynamic   = Constants.LEVEL_ONE_DYNAMIC;
                break;
            case 2:
                level_static    = Constants.LEVEL_TWO_STATIC;
                level_dynamic   = Constants.LEVEL_TWO_DYNAMIC;
                break;
            case 3:
                level_static    = Constants.LEVEL_THREE_STATIC;
                level_dynamic   = Constants.LEVEL_THREE_DYNAMIC;
                break;
            case 4:
                level_static    = Constants.LEVEL_FOUR_STATIC;
                level_dynamic   = Constants.LEVEL_FOUR_DYNAMIC;
                break;
            case 5:
                level_static    = Constants.LEVEL_FIVE_STATIC;
                level_dynamic   = Constants.LEVEL_FIVE_DYNAMIC;
                break;
            case 6:
                level_static    = Constants.LEVEL_SIX_STATIC;
                level_dynamic   = Constants.LEVEL_SIX_DYNAMIC;
                break;
            case 7:
                level_static    = Constants.LEVEL_SEVEN_STATIC;
                level_dynamic   = Constants.LEVEL_SEVEN_DYNAMIC;
                break;
            case 8:
                level_static    = Constants.LEVEL_EIGHT_STATIC;
                level_dynamic   = Constants.LEVEL_EIGHT_DYNAMIC;
                break;
            case 9:
                level_static    = Constants.LEVEL_NINE_STATIC;
                level_dynamic   = Constants.LEVEL_NINE_DYNAMIC;
                break;
        }
    }

    public void restart(){
        getLevelMatrixById(level.getId());
    }

    private void incCounter(){
        level.setMoves(++counter);
    }

    private void getMatrices(){
        this.level_static   = matrix.getLevel_static();
        this.level_dynamic  = matrix.getLevel_dynamic();
    }

    public void handlePress(KeyEvent event){
        if(event.getCode() == KeyCode.UP    || event.getCode() == KeyCode.W)    moveUp();
        if(event.getCode() == KeyCode.LEFT  || event.getCode() == KeyCode.A)    moveLeft();
        if(event.getCode() == KeyCode.DOWN  || event.getCode() == KeyCode.S)    moveDown();
        if(event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D)    moveRight();
    }

    private void moveUp() {
        if(matrix.canMove(Direction.UP) == 1) {
            level.movePlayer(Direction.UP);
            matrix.move(Direction.UP);
            incCounter();
            getMatrices();
        } else if(matrix.canMove(Direction.UP) == 2){
            int index = boxes.getIndex(matrix.getColumn(), matrix.getRow() - 1);
            level.moveBox(Direction.UP, index);
            matrix.moveBox(Direction.UP);
            incCounter();
            getMatrices();
            boxes.manage();
        }
    }

    private void moveLeft() {
        if(matrix.canMove(Direction.LEFT) == 1) {
            level.movePlayer(Direction.LEFT);
            matrix.move(Direction.LEFT);
            incCounter();
            getMatrices();
        } else if(matrix.canMove(Direction.LEFT) == 2){
            int index = boxes.getIndex(matrix.getColumn() - 1, matrix.getRow());
            level.moveBox(Direction.LEFT, index);
            matrix.moveBox(Direction.LEFT);
            incCounter();
            getMatrices();
            boxes.manage();
        }
    }

    private void moveDown() {
        if(matrix.canMove(Direction.DOWN) == 1) {
            level.movePlayer(Direction.DOWN);
            matrix.move(Direction.DOWN);
            incCounter();
            getMatrices();
        } else if(matrix.canMove(Direction.DOWN) == 2){
            int index = boxes.getIndex(matrix.getColumn(), matrix.getRow() + 1);
            level.moveBox(Direction.DOWN, index);
            matrix.moveBox(Direction.DOWN);
            incCounter();
            getMatrices();
            boxes.manage();
        }
    }

    private void moveRight() {
        if(matrix.canMove(Direction.RIGHT) == 1) {
            level.movePlayer(Direction.RIGHT);
            matrix.move(Direction.RIGHT);
            incCounter();
            getMatrices();
        } else if(matrix.canMove(Direction.RIGHT) == 2){
            int index = boxes.getIndex(matrix.getColumn() + 1, matrix.getRow());
            level.moveBox(Direction.RIGHT, index);
            matrix.moveBox(Direction.RIGHT);
            incCounter();
            getMatrices();
            boxes.manage();
        }
    }

    public class Boxes{

        ArrayList<Box> boxes;

        Boxes(){
            boxes = new ArrayList<>();
            manage();
        }

        private int getIndex(int x, int y){
            for(Box box : boxes)
                if(box.getX() == x && box.getY() == y)
                    return box.getIndex();
            return -1;
        }

        private void assignBoxIndices(){
            boxes.clear();
            try {
                for (int i = 0, index = 0; i < level_dynamic.length; i++)
                    for (int j = 0; j < level_dynamic[i].length; j++)
                        if (level_dynamic[i][j] == 1)
                            boxes.add(new Box(j, i, index++));
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }

        private void manage(){
            assignBoxIndices();
            boxCycle();
            if(matrix.isGameOver()){
                Constants.AVAILABLE_LEVELS[level.getId()] = true;
                ScoresDatabase database = new ScoresDatabase();
                try {
                    database.insert(Constants.getName(), counter, level.getId());
                    database.closeDatabase();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("You win");
                alert.setHeaderText(null);
                alert.setContentText("Advance to the next level?");
                alert.setResizable(true);
                alert.getDialogPane().setPrefSize(40,30);
                alert.setResizable(false);

                ButtonType no = new ButtonType("No");
                ButtonType yes = new ButtonType("Yes");

                alert.getButtonTypes().setAll(yes, no);
                alert.setWidth(30);
                alert.setHeight(30);

                ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/sprites/door.png"));

                Optional<ButtonType> result = alert.showAndWait();

                if(!result.isPresent())         alert.close();
                else if(result.get() == yes){
                    Stage stage = ((Stage) level.getPlayer().getScene().getWindow());
                    stage.setScene(SceneStack.tailForward(new Level("Level " + (level.getId() + 1), level.getId() + 1).inflate()));
                }
                else if(result.get() == no){
                    Stage stage = ((Stage) level.getPlayer().getScene().getWindow());
                    stage.setScene(SceneStack.backward());
                }
            }
        }

        private void boxCycle() {
            for (int i = 0; i < level_dynamic.length; i++)
                for (int j = 0; j < level_dynamic[i].length; j++)
                    if (level_dynamic[i][j] == 1 && getIndex(j, i) != -1)
                        if (level_static[i][j] == 2)
                            level.toggleBoxColor(getIndex(j, i), true);
                        else
                            level.toggleBoxColor(getIndex(j, i), false);
        }

        class Box{
            private int x, y, index;

            Box(int x, int y, int index) {
                this.x = x;
                this.y = y;
                this.index = index;
            }

            int getIndex() {
                return index;
            }

            int getX() {
                return x;
            }

            int getY() {
                return y;
            }
        }
    }

}
