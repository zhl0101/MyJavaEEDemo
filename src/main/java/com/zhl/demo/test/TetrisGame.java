package com.zhl.demo.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 * 俄罗斯方块游戏主类
 */
public class TetrisGame extends JPanel implements ActionListener {
    private final int BOARD_WIDTH = 10;
    private final int BOARD_HEIGHT = 20;
    private final int CELL_SIZE = 30;
    private final int DELAY = 400;

    private Timer timer;
    private boolean isRunning = false;
    private boolean isPaused = false;
    private int score = 0;
    private int linesCleared = 0;
    
    private Tetromino currentPiece;
    private Tetromino nextPiece;
    private int[][] board;
    private Random random = new Random();

    public TetrisGame() {
        initBoard();
        setPreferredSize(new Dimension(BOARD_WIDTH * CELL_SIZE + 150, BOARD_HEIGHT * CELL_SIZE));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(new TAdapter());
        
        timer = new Timer(DELAY, this);
        timer.start();
        
        spawnPiece();
    }

    private void initBoard() {
        board = new int[BOARD_HEIGHT][BOARD_WIDTH];
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                board[i][j] = 0;
            }
        }
    }

    private void spawnPiece() {
        if (nextPiece == null) {
            nextPiece = Tetromino.getRandomTetromino(random);
        }
        
        currentPiece = nextPiece;
        nextPiece = Tetromino.getRandomTetromino(random);
        
        // 检查游戏是否结束
        if (!isValidPosition(currentPiece, currentPiece.getX(), currentPiece.getY())) {
            isRunning = false;
            timer.stop();
        }
    }

    private boolean isValidPosition(Tetromino piece, int newX, int newY) {
        for (int i = 0; i < piece.getSize(); i++) {
            for (int j = 0; j < piece.getSize(); j++) {
                if (piece.getCell(i, j) != 0) {
                    int x = newX + j;
                    int y = newY + i;
                    
                    if (x < 0 || x >= BOARD_WIDTH || y >= BOARD_HEIGHT) {
                        return false;
                    }
                    
                    if (y >= 0 && board[y][x] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void rotatePiece() {
        if (currentPiece != null && !isPaused) {
            Tetromino rotated = currentPiece.rotate();
            if (isValidPosition(rotated, currentPiece.getX(), currentPiece.getY())) {
                currentPiece = rotated;
            }
        }
    }

    private void movePieceDown() {
        if (currentPiece != null && !isPaused) {
            if (isValidPosition(currentPiece, currentPiece.getX(), currentPiece.getY() + 1)) {
                currentPiece.setY(currentPiece.getY() + 1);
            } else {
                placePiece();
                clearLines();
                spawnPiece();
            }
        }
    }

    private void movePieceLeft() {
        if (currentPiece != null && !isPaused) {
            if (isValidPosition(currentPiece, currentPiece.getX() - 1, currentPiece.getY())) {
                currentPiece.setX(currentPiece.getX() - 1);
            }
        }
    }

    private void movePieceRight() {
        if (currentPiece != null && !isPaused) {
            if (isValidPosition(currentPiece, currentPiece.getX() + 1, currentPiece.getY())) {
                currentPiece.setX(currentPiece.getX() + 1);
            }
        }
    }

    private void dropPiece() {
        if (currentPiece != null && !isPaused) {
            while (isValidPosition(currentPiece, currentPiece.getX(), currentPiece.getY() + 1)) {
                currentPiece.setY(currentPiece.getY() + 1);
            }
            placePiece();
            clearLines();
            spawnPiece();
        }
    }

    private void placePiece() {
        if (currentPiece != null) {
            for (int i = 0; i < currentPiece.getSize(); i++) {
                for (int j = 0; j < currentPiece.getSize(); j++) {
                    if (currentPiece.getCell(i, j) != 0) {
                        int x = currentPiece.getX() + j;
                        int y = currentPiece.getY() + i;
                        if (y >= 0) {
                            board[y][x] = currentPiece.getType();
                        }
                    }
                }
            }
        }
    }

    private void clearLines() {
        int lines = 0;
        for (int i = BOARD_HEIGHT - 1; i >= 0; i--) {
            boolean fullLine = true;
            for (int j = 0; j < BOARD_WIDTH; j++) {
                if (board[i][j] == 0) {
                    fullLine = false;
                    break;
                }
            }
            
            if (fullLine) {
                lines++;
                // 移动上面的所有行向下移动一行
                for (int k = i; k > 0; k--) {
                    for (int j = 0; j < BOARD_WIDTH; j++) {
                        board[k][j] = board[k-1][j];
                    }
                }
                // 清空顶部行
                for (int j = 0; j < BOARD_WIDTH; j++) {
                    board[0][j] = 0;
                }
                i++; // 重新检查当前行
            }
        }
        
        if (lines > 0) {
            linesCleared += lines;
            score += lines * 100 * lines; // 连击奖励
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isRunning && !isPaused) {
            movePieceDown();
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // 绘制游戏区域
        g.setColor(Color.WHITE);
        g.drawRect(0, 0, BOARD_WIDTH * CELL_SIZE, BOARD_HEIGHT * CELL_SIZE);
        
        // 绘制已放置的方块
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                if (board[i][j] != 0) {
                    drawCell(g, j, i, board[i][j]);
                }
            }
        }
        
        // 绘制当前活动方块
        if (currentPiece != null) {
            for (int i = 0; i < currentPiece.getSize(); i++) {
                for (int j = 0; j < currentPiece.getSize(); j++) {
                    if (currentPiece.getCell(i, j) != 0) {
                        int x = currentPiece.getX() + j;
                        int y = currentPiece.getY() + i;
                        if (y >= 0) {
                            drawCell(g, x, y, currentPiece.getType());
                        }
                    }
                }
            }
        }
        
        // 绘制右侧信息面板
        drawInfoPanel(g);
    }

    private void drawCell(Graphics g, int x, int y, int type) {
        Color color = getColorByType(type);
        g.setColor(color);
        g.fillRect(x * CELL_SIZE + 1, y * CELL_SIZE + 1, CELL_SIZE - 2, CELL_SIZE - 2);
        
        g.setColor(Color.WHITE);
        g.drawRect(x * CELL_SIZE + 1, y * CELL_SIZE + 1, CELL_SIZE - 2, CELL_SIZE - 2);
    }

    private Color getColorByType(int type) {
        switch (type) {
            case 1: return Color.CYAN;     // I
            case 2: return Color.BLUE;     // J
            case 3: return Color.ORANGE;   // L
            case 4: return Color.YELLOW;   // O
            case 5: return Color.GREEN;    // S
            case 6: return Color.MAGENTA;  // T
            case 7: return Color.RED;      // Z
            default: return Color.GRAY;
        }
    }

    private void drawInfoPanel(Graphics g) {
        int panelX = BOARD_WIDTH * CELL_SIZE + 20;
        
        // 绘制分数
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("得分: " + score, panelX, 30);
        g.drawString("消除行数: " + linesCleared, panelX, 60);
        
        // 绘制下一个方块提示
        g.drawString("下一个:", panelX, 100);
        
        if (nextPiece != null) {
            for (int i = 0; i < nextPiece.getSize(); i++) {
                for (int j = 0; j < nextPiece.getSize(); j++) {
                    if (nextPiece.getCell(i, j) != 0) {
                        Color color = getColorByType(nextPiece.getType());
                        g.setColor(color);
                        g.fillRect(panelX + j * CELL_SIZE, 110 + i * CELL_SIZE, 
                                 CELL_SIZE - 2, CELL_SIZE - 2);
                        g.setColor(Color.WHITE);
                        g.drawRect(panelX + j * CELL_SIZE, 110 + i * CELL_SIZE, 
                                 CELL_SIZE - 2, CELL_SIZE - 2);
                    }
                }
            }
        }
        
        // 绘制控制说明
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString("控制说明:", panelX, 250);
        g.drawString("← → : 移动", panelX, 270);
        g.drawString("↑ : 旋转", panelX, 290);
        g.drawString("↓ : 下降", panelX, 310);
        g.drawString("空格: 瞬间下落", panelX, 330);
        g.drawString("P : 暂停", panelX, 350);
        
        // 绘制状态信息
        if (!isRunning) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("游戏结束", panelX, 400);
            g.setFont(new Font("Arial", Font.PLAIN, 14));
            g.drawString("按R重新开始", panelX, 420);
        } else if (isPaused) {
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("暂停中", panelX, 400);
        }
    }

    class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keycode = e.getKeyCode();
            
            if (!isRunning) {
                if (keycode == KeyEvent.VK_R) {
                    restartGame();
                }
                return;
            }
            
            switch (keycode) {
                case KeyEvent.VK_LEFT:
                    movePieceLeft();
                    break;
                case KeyEvent.VK_RIGHT:
                    movePieceRight();
                    break;
                case KeyEvent.VK_DOWN:
                    movePieceDown();
                    break;
                case KeyEvent.VK_UP:
                    rotatePiece();
                    break;
                case KeyEvent.VK_SPACE:
                    dropPiece();
                    break;
                case KeyEvent.VK_P:
                    togglePause();
                    break;
            }
            
            repaint();
        }
    }

    private void togglePause() {
        isPaused = !isPaused;
    }

    private void restartGame() {
        isRunning = true;
        isPaused = false;
        score = 0;
        linesCleared = 0;
        initBoard();
        spawnPiece();
        timer.start();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("俄罗斯方块");
        TetrisGame game = new TetrisGame();
        
        frame.add(game);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        game.isRunning = true;
    }
}