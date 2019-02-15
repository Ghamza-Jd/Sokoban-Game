package model.gameObject;

import java.util.HashMap;

public class GameObjectFactory {

    private static HashMap<Integer, GameObject> gameObjects = new HashMap<>();

    /**
     * @param id {0: Player, 1: Box, 2: Target, 3: Ground, 4: Wall, 4: Empty}
     * @param gridX abscess of the node
     * @param gridY ordinate of the node
     *
     * @return Entity
     */

    public static GameObject getGameObject(int id, int gridX, int gridY){
        switch (id){
            case 0  : return Player.getPlayer(gridX, gridY);
            case 1  :
                if(gameObjects.containsKey(id)){
                    Box box = (Box) gameObjects.get(id);
                    box.setGridX(gridX);
                    box.setGridY(gridY);
                    return gameObjects.get(id);
                }
                Box box = new Box(gridX, gridY);
                gameObjects.put(id, box);
                return box;
            case 2  :
                if(gameObjects.containsKey(id)){
                    Target target = (Target) gameObjects.get(id);
                    target.setGridX(gridX);
                    target.setGridY(gridY);
                    return gameObjects.get(id);
                }
                Target target = new Target(gridX, gridY);
                gameObjects.put(id, target);
                return target;
            case 3  :
                if(gameObjects.containsKey(id)){
                    Ground ground = (Ground) gameObjects.get(id);
                    ground.setGridX(gridX);
                    ground.setGridY(gridY);
                    return gameObjects.get(id);
                }
                Ground ground = new Ground(gridX, gridY);
                gameObjects.put(id, ground);
                return ground;
            case 4  :
                if(gameObjects.containsKey(id)){
                    Wall wall = (Wall) gameObjects.get(id);
                    wall.setGridX(gridX);
                    wall.setGridY(gridY);
                    return gameObjects.get(id);
                }
                Wall wall = new Wall(gridX, gridY);
                gameObjects.put(id, wall);
                return wall;
            default : return null;
        }
    }
}
