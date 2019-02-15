package model.gameObject;

import global.Constants;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Box extends GameObject {

    private boolean light = true;
    private Image darkImage;

    Box(double gridX, double gridY) {
        super(gridX, gridY);
    }

    protected void init() {
        this.id = Constants.BOX_ID;
        this.image = new Image("/sprites/box.png");
        this.imageView = new ImageView(this.image);
        darkImage = new Image("/sprites/dark_box.png");
    }

    public void toggleBox(){
        if(light) {
            this.imageView = new ImageView(this.darkImage);
            light = false;
        }
        else {
            this.imageView = new ImageView(this.image);
            light = true;
        }
    }
}
