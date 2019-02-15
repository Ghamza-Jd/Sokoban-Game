package model.gameObject;

import global.Constants;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Target extends GameObject {

    Target(double gridX, double gridY) {
        super(gridX, gridY);
    }

    @Override
    protected void init() {
        this.id = Constants.TARGET_ID;
        this.image = new Image("/sprites/target.png");
        this.imageView = new ImageView(this.image);
    }
}
