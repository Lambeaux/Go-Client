package data;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by Lambeaux on 11/10/2015.
 * Define the data structure for a variable-sized
 * playing board. Singleton for simple GUI access.
 *
 */
public class GoBoard
{
    private static final int DEFAULT_SIZE = 19;
    private static GoBoard _INSTANCE = null;

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


    protected GoBoard()
    {
        s_boardSize = DEFAULT_SIZE;
        InitializeBoard();
    }
    protected GoBoard(int p_size)
    {
        s_boardSize = p_size;
        InitializeBoard();
    }


    public static GoBoard GetInstance()
    {
        if (_INSTANCE == null)
            _INSTANCE = new GoBoard();
        return _INSTANCE;
    }
    public static GoBoard GetInstance(int boardSize)
    {
        if (_INSTANCE == null)
            _INSTANCE = new GoBoard(boardSize);
        return _INSTANCE;
    }
    public static GoBoard MakeInstance()
    {
        _INSTANCE = new GoBoard();
        return _INSTANCE;
    }
    public static GoBoard MakeInstance(int boardSize)
    {
        _INSTANCE = new GoBoard(boardSize);
        return _INSTANCE;
    }


    public boolean playMove(int i, int j)
    {

        return true;
    }


    public void drawBoard(GraphicsContext context)
    {
        context.getCanvas().getHeight();
        context.getCanvas().getWidth();

        context.setFill(Color.AQUA);
        context.fillRect(0, 0, 100, 100);
    }
}
