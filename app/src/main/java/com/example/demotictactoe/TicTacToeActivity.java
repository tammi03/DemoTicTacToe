package com.example.demotictactoe;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TicTacToeActivity extends AppCompatActivity {

    private Board model;
    private ViewGroup buttonGrid;
    private TextView winnerPlayerLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tictactoe);  // Liên kết với layout XML

        model = new Board();
        winnerPlayerLabel = findViewById(R.id.winnerPlayerLabel);
        buttonGrid = findViewById(R.id.buttonGrid);

        setupGrid();
        findViewById(R.id.resetButton).setOnClickListener(v -> reset());
    }

    private void setupGrid() {
        for (int i = 0; i < buttonGrid.getChildCount(); i++) {
            Button button = (Button) buttonGrid.getChildAt(i);
            int row = i / 3;
            int col = i % 3;
            button.setOnClickListener(v -> onCellClicked(button, row, col));
        }
    }

    private void onCellClicked(Button button, int row, int col) {
        Player playerThatMoved = model.mark(row, col);

        if (playerThatMoved != null) {
            button.setText(playerThatMoved.toString());

            Player winner = model.getWinner();
            if (winner != null) {
                winnerPlayerLabel.setText("Winner: " + winner);
                winnerPlayerLabel.setVisibility(View.VISIBLE);
            }
        }
    }

    private void reset() {
        model.restart();
        winnerPlayerLabel.setVisibility(View.GONE);
        winnerPlayerLabel.setText("");

        for (int i = 0; i < buttonGrid.getChildCount(); i++) {
            ((Button) buttonGrid.getChildAt(i)).setText("");
        }
    }
}
