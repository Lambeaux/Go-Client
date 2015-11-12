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


    //  Protected constructors
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


    //  Get the instance if already created (2 overloads)
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

    //  Forcefully overwrite the instance (2 overloads)
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


    //  Get the size of the board
    //  Important for doing external calculations.
    public int getBoardSize()
    {
        return s_boardSize;
    }

    //  Call when the next player has chosen their move
    //  Play order cannot be broken
    public boolean playMove(int i, int j)
    {
        //  Play is out of bounds
        if (i < 0 || i >= s_boardSize ||
                j < 0 || j >= s_boardSize)
            return false;

        //  Spot is already claimed
        if (s_board[i][j] != GoState.Free)
            return false;

        //  Input is valid
        if (s_isPlayerOneTurn)
        {
            s_board[i][j] = GoState.PlayerOne;
        }

        else
        {
            s_board[i][j] = GoState.PlayerTwo;
        }

        //  Check for dead stones
        RSR recursiveStoneRemover =
                new RSR(s_board, s_boardSize);

        s_isPlayerOneTurn = !s_isPlayerOneTurn;
        return true;
    }

    //  Render the board
    public void drawBoard(GraphicsContext context)
    {
        double h = context.getCanvas().getHeight();
        double w = context.getCanvas().getWidth();

        double dh = h / (double)s_boardSize;
        double dw = w / (double)s_boardSize;

        double x;
        double y;

        context.setFill(Color.WHITE);
        context.setStroke(Color.BLACK);

        y = 0;

        while (y < h)
        {
            x = 0;

            while (x < w)
            {
                int i = (int)x / (int)dw;
                int j = (int)y / (int)dh;

                if (i < s_boardSize && j < s_boardSize)
                {
                    if (s_board[i][j] == GoState.PlayerOne)
                        context.setFill(Color.NAVY);
                    else if (s_board[i][j] == GoState.PlayerTwo)
                        context.setFill(Color.DARKRED);
                    else
                        context.setFill(Color.WHITE);
                }

                context.strokeRect(x, y, dw, dh);
                context.fillRect(x, y, dw, dh);

                x += dw;
            }

            y += dh;
        }
    }
}
