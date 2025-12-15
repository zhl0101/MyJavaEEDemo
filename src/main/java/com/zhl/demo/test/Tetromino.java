package com.zhl.demo.test;

import java.util.Random;

/**
 * 俄罗斯方块中的方块类
 */
public class Tetromino {
    private int[][] shape;
    private int size;
    private int type;
    private int x, y;

    // 定义7种不同形状的方块
    private static final int[][][] SHAPES = {
        // I 形状
        {
            {0, 0, 0, 0},
            {1, 1, 1, 1},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        },
        // J 形状
        {
            {2, 0, 0},
            {2, 2, 2},
            {0, 0, 0}
        },
        // L 形状
        {
            {0, 0, 3},
            {3, 3, 3},
            {0, 0, 0}
        },
        // O 形状
        {
            {4, 4},
            {4, 4}
        },
        // S 形状
        {
            {0, 5, 5},
            {5, 5, 0},
            {0, 0, 0}
        },
        // T 形状
        {
            {0, 6, 0},
            {6, 6, 6},
            {0, 0, 0}
        },
        // Z 形状
        {
            {7, 7, 0},
            {0, 7, 7},
            {0, 0, 0}
        }
    };

    public Tetromino(int type) {
        this.type = type;
        this.shape = copyShape(SHAPES[type - 1]);
        this.size = shape.length;
        this.x = 0;
        this.y = 0;
    }

    private int[][] copyShape(int[][] original) {
        int rows = original.length;
        int cols = original[0].length;
        int[][] copy = new int[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                copy[i][j] = original[i][j];
            }
        }
        
        return copy;
    }

    public Tetromino rotate() {
        Tetromino rotated = new Tetromino(this.type);
        rotated.x = this.x;
        rotated.y = this.y;
        
        // 创建旋转后的形状
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                rotated.shape[i][j] = shape[size - 1 - j][i];
            }
        }
        
        return rotated;
    }

    public static Tetromino getRandomTetromino(Random random) {
        int type = random.nextInt(7) + 1;
        return new Tetromino(type);
    }

    // Getters and Setters
    public int getCell(int row, int col) {
        return shape[row][col];
    }

    public int getSize() {
        return size;
    }

    public int getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int[][] getShape() {
        return shape;
    }
}