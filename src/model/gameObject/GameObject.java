package model.gameObject;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class GameObject {

    private double gridX, gridY;
    protected int id;
    protected Image image;
    ImageView imageView;

    protected abstract void init();

    GameObject(double gridX, double gridY){
        this.gridX = gridX;
        this.gridY = gridY;
        init();
    }

    public double getGridX() {
        return gridX;
    }

    void setGridX(double gridX) {
        this.gridX = gridX;
    }

    public double getGridY() {
        return gridY;
    }

    void setGridY(double gridY) {
        this.gridY = gridY;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
