package model.gameObject;

import global.Constants;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Wall extends GameObject {

    Wall(double gridX, double gridY) {
        super(gridX, gridY);
    }

    @Override
    protected void init() {
        this.id = Constants.WALL_ID;
        this.image = new Image("/sprites/wall.png");
        this.imageView = new ImageView(this.image);
    }
}
