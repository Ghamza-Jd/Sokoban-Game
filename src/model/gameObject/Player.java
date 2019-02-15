package model.gameObject;

import global.Constants;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player extends GameObject {

    private static Player _player;
    private Player(double gridX, double gridY) {
        super(gridX, gridY);
    }
    static Player getPlayer(double gridX, double gridY){
        if(_player == null)
            return new Player(gridX, gridY);
        _player.setGridX(gridX);
        _player.setGridY(gridY);
        return _player;
    }

    @Override
    protected void init() {
        this.id = Constants.PLAYER_ID;
        this.image = new Image("/sprites/player.png");
        this.imageView = new ImageView(this.image);
    }


}
