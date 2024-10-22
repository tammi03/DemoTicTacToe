package com.example.demotictactoe.model;

public enum Player {
    X, O;

    public Player next() {
        return this == X ? O : X;
    }
}
