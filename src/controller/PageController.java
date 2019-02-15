package controller;

import global.SceneStack;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import view.Page;

public class PageController {

    private Page page;

    public PageController(Page page){
        this.page = page;
    }

    public void onBackPress(ActionEvent event){
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(SceneStack.backward());
    }
}
