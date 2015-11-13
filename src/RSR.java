import javafx.util.Pair;
import java.util.ArrayList;

/**
 * Created by Lambeaux on 11/11/2015.
 * Container class for my flagship algorithm.
 * Scans the provided 2D array and performs a
 * recursive group check on stone arrangements.
 * Removes completely surrounded groups.
 *
 * End result is a constructed object with metrics
 * on the recursive performance.
 *
 */
public class RSR
{
    private ArrayList<Pair<Integer, Integer>> checkList;
    private GoState[][] board;
    private GoState player;
    private int boardSize;

    public RSR(GoState[][] b, int s)
    {
        checkList = new ArrayList<>();

        board = b;
        boardSize = s;

        for (int i = 0; i < boardSize; i++)
            for (int j = 0; j < boardSize; j++)
                if (board[i][j] != GoState.Free)
                {
                    checkList.clear();
                    player = board[i][j];

                    if (!isAlive(i, j, RDir.None))
                    {
                        for (Pair<Integer, Integer> p : checkList)
                            board[p.getKey()][p.getValue()] = GoState.Free;
                    }
                }
    }

    private boolean isAlive(int i, int j, RDir d)
    {
        // We have to avoid going in circles
        Pair<Integer, Integer> p = new Pair<>(i, j);

        if (checkList.contains(p))
            return false;

        checkList.add(p);

        // CHECK LEFT:
        if (i > 0 && d != RDir.Right)
        {
            if (board[i - 1][j] == player)
            {
                if (isAlive(i - 1, j, RDir.Left))
                    return true;
            }
            else if (board[i - 1][j] == GoState.Free)
                return true;
        }

        // CHECK UP:
        if (j > 0 && d != RDir.Down)
        {
            if (board[i][j - 1] == player)
            {
                if (isAlive(i, j - 1, RDir.Up))
                    return true;
            }
            else if (board[i][j - 1] == GoState.Free)
                return true;
        }

        // CHECK RIGHT:
        if (i < boardSize - 1 && d != RDir.Left)
        {
            if (board[i + 1][j] == player)
            {
                if (isAlive(i + 1, j, RDir.Right))
                    return true;
            }
            else if (board[i + 1][j] == GoState.Free)
                return true;
        }

        // CHECK DOWN:
        if (j < boardSize - 1 && d != RDir.Up)
        {
            if (board[i][j + 1] == player)
            {
                if (isAlive(i, j + 1, RDir.Down))
                    return true;
            }
            else if (board[i][j + 1] == GoState.Free)
                return true;
        }

        // We didn't find life
        return false;
    }



}
