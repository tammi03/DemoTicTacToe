package com.example.demotictactoe;

public enum Player {
    X, O;

    public Player next() {
        return this == X ? O : X;
    }
}
