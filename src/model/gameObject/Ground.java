package model.gameObject;

import global.Constants;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ground extends GameObject {

    Ground(double gridX, double gridY) {
        super(gridX, gridY);
    }

    @Override
    protected void init() {
        this.id = Constants.GROUND_ID;
        this.image = new Image("/sprites/ground.png");
        this.imageView = new ImageView(this.image);
    }
}
