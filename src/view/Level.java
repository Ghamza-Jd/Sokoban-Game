package view;

import controller.GameLogic;
import global.Constants;
import global.Direction;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import model.gameObject.GameObject;
import model.gameObject.GameObjectFactory;
import java.util.ArrayList;
import java.util.Collections;

public class Level extends Page implements EventHandler<KeyEvent> {

    private ArrayList<ImageView> movables;
    private int id;
    private int [][] level_static, level_dynamic;
    private Label moves;
    private GameLogic gameLogic;

    public Level(String name, int id) {
        super(name);
        this.id = id;
        movables = new ArrayList<>();
    }

    public void moveBox(Direction direction, int index){
        index++;
        switch (direction){
            case UP:
                movables.get(index).setTranslateY(movables.get(index).getTranslateY() - Constants.GRID_HEIGHT);
                break;
            case LEFT:
                movables.get(index).setTranslateX(movables.get(index).getTranslateX() - Constants.GRID_WIDTH);
                break;
            case DOWN:
                movables.get(index).setTranslateY(movables.get(index).getTranslateY() + Constants.GRID_HEIGHT);
                break;
            case RIGHT:
                movables.get(index).setTranslateX(movables.get(index).getTranslateX() + Constants.GRID_WIDTH);
                break;
        }
        movePlayer(direction);
        sort();
    }

    public void movePlayer(Direction direction){
        switch (direction){
            case UP:
                movables.get(0).setTranslateY(movables.get(0).getTranslateY() - Constants.GRID_HEIGHT);
                break;
            case LEFT:
                movables.get(0).setTranslateX(movables.get(0).getTranslateX() - Constants.GRID_WIDTH);
                break;
            case DOWN:
                movables.get(0).setTranslateY(movables.get(0).getTranslateY() + Constants.GRID_HEIGHT);
                break;
            case RIGHT:
                movables.get(0).setTranslateX(movables.get(0).getTranslateX() + Constants.GRID_WIDTH);
                break;
        }
    }

    public void toggleBoxColor(int index, boolean isLight){
        if(isLight)
            movables.get(index + 1).setImage(new Image("/sprites/dark_box.png"));
        else
            movables.get(index + 1).setImage(new Image("/sprites/box.png"));
    }

    private GridPane getLevelById(){
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
            default:
                return null;
        }
        return createGridPane(level_static, level_dynamic);
    }

    private GridPane createGridPane(int [][] level_static, int [][] level_dynamic){
        ImageView imageView;
        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);

        ///////////////////////////////
        // Creating the static level //
        ///////////////////////////////

        for(int i = 0; i < level_static.length; i++){
            for(int j = 0; j < level_static[i].length; j++){
                int id = level_static[i][j];
                if(id == Constants.EMPTY_ID) continue;
                if(id == Constants.TARGET_ID){
                    imageView = createGameObjectImageView(Constants.GROUND_ID, j, i);
                    gp.add(imageView, j, i);
                }
                imageView = createGameObjectImageView(id, j, i);
                gp.add(imageView, j, i);
            }
        }

        ////////////////////////////////
        // Creating the dynamic level //
        ////////////////////////////////

        for(int i = 0; i < level_dynamic.length; i++) {
            for(int j = 0; j < level_dynamic[i].length; j++) {
                int id = level_dynamic[i][j];
                if(id == Constants.EMPTY_ID) continue;
                imageView = createGameObjectImageView(id, j, i);
                if(id == Constants.PLAYER_ID){
                    movables.add(0, imageView);
                }
                else{
                    movables.add(imageView);
                }
                gp.add(imageView, j, i);
            }
        }
        return gp;
    }

    private void sort(){
        ImageView player = movables.get(0);
        movables.remove(0);
        for(int i = 0; i < movables.size(); i++) {
            boolean changed = false;
            double minY =  movables.get(i).localToScene(movables.get(i).getX(), movables.get(i).getY()).getY();
            int minIndex = i, minCounter = 0;
            for (int j = i; j < movables.size(); j++) {
                double yj = movables.get(j).localToScene(movables.get(j).getX(), movables.get(j).getY()).getY();
                if(yj < minY){
                    minCounter = 1;
                    minY = yj;
                    minIndex = j;
                    changed = true;
                }
                else if(yj == minY){
                    minCounter++;
                }
            }
            if(minCounter > 1){
                double minX =  movables.get(i).localToScene(movables.get(i).getX(), movables.get(i).getY()).getX();
                for (int j = i; j < movables.size(); j++){
                    double
                            xj = movables.get(j).localToScene(movables.get(j).getX(), movables.get(j).getX()).getX(),
                            yj = movables.get(j).localToScene(movables.get(j).getY(), movables.get(j).getY()).getY();
                    if(yj == minY && xj < minX){
                        minX = xj;
                        minIndex = j;
                        changed = true;
                    }
                }
            }
            if(changed)
                Collections.swap(movables, i, minIndex);
        }
        movables.add(0, player);
    }

    private ImageView createGameObjectImageView(int id, int j, int i){
        ImageView imageView;
        GameObject gameObject = GameObjectFactory.getGameObject(id, j, i);
        imageView = new ImageView(gameObject.getImage());
        imageView.setFitWidth(Constants.GRID_WIDTH);
        imageView.setFitHeight(Constants.GRID_HEIGHT);
        return imageView;
    }

    public void setMoves(int count){
        moves.setText("Moves: " + count);
    }

    public Node getPlayer(){
        return movables.get(0);
    }

    public int getId() {
        return id;
    }

    @Override
    protected ArrayList<Node> createBody() {
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(getLevelById());
        return nodes;
    }

    @Override
    protected VBox createVBox() {
        VBox vb = new VBox();
        vb.setSpacing(30);
        vb.setAlignment(Pos.CENTER);
        return vb;
    }

    @Override
    public Scene inflate() {
        Scene scene = super.inflate();
        gameLogic = new GameLogic(level_static, level_dynamic, this);
        scene.setOnKeyPressed(gameLogic::handlePress);
        return scene;
    }

    @Override
    protected ArrayList<Node> createHeader() {
        ArrayList<Node> nodes = super.createHeader();
        moves = new Label("Moves: 0");
        moves.setFont(Font.font("Comic Sans MS", FontPosture.ITALIC, 18));
        nodes.add(moves);
        return nodes;
    }

    @Override
    public void handle(KeyEvent event) {

    }

}
