package model;

import global.Direction;

import java.util.Stack;

public class DirectionStack {

    private Stack<Movement> movement;

    DirectionStack(){
        movement = new Stack<>();
    }

    private void push(Direction playerDirection, Direction boxDirection, boolean playerOnly) {
        if(playerOnly) {
            switch (playerDirection) {
                case UP:
                    movement.push(new Movement(Direction.DOWN, null, true));
                case LEFT:
                    movement.push(new Movement(Direction.RIGHT, null, true));
                case DOWN:
                    movement.push(new Movement(Direction.UP, null, true));
                case RIGHT:
                    movement.push(new Movement(Direction.LEFT, null, true));
            }
        }
        else{
            switch (playerDirection) {
                case UP:
                    movement.push(new Movement(Direction.DOWN, boxDirection, false));
                case LEFT:
                    movement.push(new Movement(Direction.RIGHT, boxDirection, false));
                case DOWN:
                    movement.push(new Movement(Direction.UP, boxDirection, false));
                case RIGHT:
                    movement.push(new Movement(Direction.LEFT, boxDirection, false));
            }
        }
    }

    class Movement{
        Direction player, box;
        boolean playerOnly;

        Movement(Direction player, Direction box, boolean playerOnly) {
            this.player = player;
            this.box = box;
            this.playerOnly = playerOnly;
        }
    }

}
