package com.example.demotictactoe;

public class Board {
    private Player[][] cells = new Player[3][3];
    private Player currentTurn = Player.X;

    public Player mark(int row, int col) {
        if (cells[row][col] == null) {
            cells[row][col] = currentTurn;
            Player playerThatMoved = currentTurn;
            currentTurn = currentTurn.next();
            return playerThatMoved;
        }
        return null;
    }

    public Player getWinner() {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if (cells[i][0] != null && cells[i][0] == cells[i][1] && cells[i][1] == cells[i][2]) {
                return cells[i][0];
            }
            if (cells[0][i] != null && cells[0][i] == cells[1][i] && cells[1][i] == cells[2][i]) {
                return cells[0][i];
            }
        }
        if (cells[0][0] != null && cells[0][0] == cells[1][1] && cells[1][1] == cells[2][2]) {
            return cells[0][0];
        }
        if (cells[0][2] != null && cells[0][2] == cells[1][1] && cells[1][1] == cells[2][0]) {
            return cells[0][2];
        }
        return null;
    }

    public void restart() {
        cells = new Player[3][3];
        currentTurn = Player.X;
    }
}