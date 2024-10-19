# DemoTicTacToe
**Player.java**
```java
public enum Player {
    X, O;

    public Player next() {
        return this == X ? O : X;
    }
}

**Board.java**
```java
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

**TicTacToeActivity.java**
```java
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

**styles.xml**
```xml
<resources>
    <style name="TicTacToeButton">
        <item name="android:layout_width">100dp</item>
        <item name="android:layout_height">100dp</item>
        <item name="android:layout_margin">5dp</item>
        <item name="android:gravity">center</item>
        <item name="android:textSize">24sp</item>
    </style>
</resources>

**tictactoe.xml**
```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp"
    android:gravity="center">

    <TextView
        android:id="@+id/winnerPlayerLabel"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="24sp"
        android:textColor="@android:color/holo_blue_dark"
        android:visibility="gone"
        android:textStyle="bold"
        android:layout_gravity="center_horizontal" />

    <GridLayout
        android:id="@+id/buttonGrid"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="32dp"
        android:columnCount="3"
        android:rowCount="3"
        android:layout_gravity="center">

        <!-- 9 Buttons for Tic-Tac-Toe Grid -->
        <Button
            android:id="@+id/button_00"
            style="@style/TicTacToeButton"/>
        <Button
            android:id="@+id/button_01"
            style="@style/TicTacToeButton"/>
        <Button
            android:id="@+id/button_02"
            style="@style/TicTacToeButton"/>

        <Button
            android:id="@+id/button_10"
            style="@style/TicTacToeButton"/>
        <Button
            android:id="@+id/button_11"
            style="@style/TicTacToeButton"/>
        <Button
            android:id="@+id/button_12"
            style="@style/TicTacToeButton"/>

        <Button
            android:id="@+id/button_20"
            style="@style/TicTacToeButton"/>
        <Button
            android:id="@+id/button_21"
            style="@style/TicTacToeButton"/>
        <Button
            android:id="@+id/button_22"
            style="@style/TicTacToeButton"/>
    </GridLayout>

    <Button
        android:id="@+id/resetButton"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Reset"
        android:layout_gravity="center_horizontal"
        android:layout_marginTop="16dp" />
</LinearLayout>
