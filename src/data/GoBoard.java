package data;

import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Lambeaux on 11/10/2015.
 * Define the data structure for a variable-sized
 * playing board.
 *
 */
public class GoBoard
{
    private static final int DEFAULT_SIZE = 19;

    private GoState[][] s_board;
    private int s_boardSize;

    private boolean s_isPlayerOneTurn;

    private void InitializeBoard()
    {
        s_board = new GoState[s_boardSize][s_boardSize];
        s_isPlayerOneTurn = true;

        //  Initialize all spaces to FREE
        for (int i = 0; i < s_boardSize; i++)
            for (int j = 0; j < s_boardSize; j++)
                s_board[i][j] = GoState.Free;
    }

    public GoBoard()
    {
        s_boardSize = DEFAULT_SIZE;
        InitializeBoard();
    }

    public GoBoard(int p_size)
    {
        s_boardSize = p_size;
        InitializeBoard();
    }

    public boolean playMove(int i, int j)
    {

        return true;
    }

    public void drawBoard(GraphicsContext context)
    {

    }
}
