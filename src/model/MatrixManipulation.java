package model;

import global.Direction;

public class MatrixManipulation {

    private int [][] level_static, level_dynamic;
    private int column, row;

    public MatrixManipulation(int [][] level_static, int [][] level_dynamic){
        this.level_static = level_static;
        this.level_dynamic = level_dynamic;
        getPlayer();
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public int[][] getLevel_static() {
        return level_static;
    }

    public int[][] getLevel_dynamic() {
        return level_dynamic;
    }

    public int canMove(Direction direction){

        switch (direction){

            case UP:
                if(level_static[row - 1][column] == 4)
                    return 0;
                else if(level_dynamic[row - 1][column] != 1)
                    return 1;
                else if(level_static[row - 2][column] != 4 && level_dynamic[row - 2][column] != 1 && level_dynamic[row - 1][column] == 1)
                    return 2;
                break;

            case DOWN:
                if(level_static[row + 1][column] == 4)
                    return 0;
                else if(level_dynamic[row + 1][column] != 1)
                    return 1;
                else if(level_static[row + 2][column] != 4 && level_dynamic[row + 2][column] != 1 && level_dynamic[row + 1][column] == 1)
                    return 2;
                break;

            case LEFT:
                if(level_static[row][column - 1] == 4)
                    return 0;
                else if(level_dynamic[row][column - 1] != 1)
                    return 1;
                else if(level_static[row][column - 2] != 4 && level_dynamic[row][column - 2] != 1 && level_dynamic[row][column - 1] == 1)
                    return 2;
                break;

            case RIGHT:
                if(level_static[row][column + 1] == 4)
                    return 0;
                else if(level_dynamic[row][column + 1] != 1)
                    return 1;
                else if(level_static[row][column + 2] != 4 && level_dynamic[row][column + 2] != 1 && level_dynamic[row][column + 1] == 1)
                    return 2;
                break;
        }
        return 0;
    }

    public void move(Direction direction){

        if(direction == Direction.UP){
            level_dynamic[row - 1][column]  ^= level_dynamic[row][column];
            level_dynamic[row][column]      ^= level_dynamic[row - 1][column];
            level_dynamic[row - 1][column]  ^= level_dynamic[row][column];
            row--;
        }
        else if(direction == Direction.DOWN){
            level_dynamic[row + 1][column]  ^= level_dynamic[row][column];
            level_dynamic[row][column]      ^= level_dynamic[row + 1][column];
            level_dynamic[row + 1][column]  ^= level_dynamic[row][column];
            row++;
        }
        else if(direction == Direction.LEFT){
            level_dynamic[row][column - 1]  ^= level_dynamic[row][column];
            level_dynamic[row][column]      ^= level_dynamic[row][column - 1];
            level_dynamic[row][column - 1]  ^= level_dynamic[row][column];
            column--;

        }
        else if(direction == Direction.RIGHT){
            level_dynamic[row][column + 1]  ^= level_dynamic[row][column];
            level_dynamic[row][column]      ^= level_dynamic[row][column + 1];
            level_dynamic[row][column + 1]  ^= level_dynamic[row][column];
            column++;
        }
    }

    public void moveBox(Direction direction){
        if(direction == Direction.UP){
            level_dynamic[row - 1][column]  ^= level_dynamic[row - 2][column];
            level_dynamic[row - 2][column]  ^= level_dynamic[row - 1][column];
            level_dynamic[row - 1][column]  ^= level_dynamic[row - 2][column];
        }
        else if(direction == Direction.DOWN){
            level_dynamic[row + 1][column]  ^= level_dynamic[row + 2][column];
            level_dynamic[row + 2][column]  ^= level_dynamic[row + 1][column];
            level_dynamic[row + 1][column]  ^= level_dynamic[row + 2][column];
        }
        else if(direction == Direction.LEFT){
            level_dynamic[row][column - 1]  ^= level_dynamic[row][column - 2];
            level_dynamic[row][column - 2]  ^= level_dynamic[row][column - 1];
            level_dynamic[row][column - 1]  ^= level_dynamic[row][column - 2];
        }
        else if(direction == Direction.RIGHT){
            level_dynamic[row][column + 1]  ^= level_dynamic[row][column + 2];
            level_dynamic[row][column + 2]  ^= level_dynamic[row][column + 1];
            level_dynamic[row][column + 1]  ^= level_dynamic[row][column + 2];
        }
        move(direction);
    }

    private void getPlayer() {
        for (int i = 0; i < level_dynamic.length; i++){
            for (int j = 0; j < level_dynamic[i].length; j++) {
                if (level_dynamic[i][j] == 0) {
                    column = j;
                    row = i;
                }
            }
        }
    }

    public boolean isGameOver(){
        for(int i = 0; i < level_dynamic.length; i++){
            for(int j = 0; j < level_dynamic[i].length; j++){
                if(level_dynamic[i][j] == 1 && level_static[i][j] != 2)
                    return false;
            }
        }
        return true;
    }

}
