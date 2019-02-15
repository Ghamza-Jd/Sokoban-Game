package global;

import javafx.scene.Scene;

import java.util.Stack;

public class SceneStack {

    private static Stack<Scene> sceneStack = new Stack<>();

    private static void push(Scene scene){
        sceneStack.push(scene);
    }

    private static Scene pop(){
        return (sceneStack.size() > 1 ? sceneStack.pop() : null);
    }

    private static Scene peek(){
        return sceneStack.peek();
    }

    public static Scene backward(){
        pop();
        return peek();
    }

    public static Scene forward(Scene scene){
        push(scene);
        return peek();
    }

    public static Scene tailForward(Scene scene){
        pop();
        push(scene);
        return peek();
    }
}
