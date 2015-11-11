import data.GoBoard;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class GuiController
{
    @FXML
    private TextField txtBoardSize;
    @FXML
    private Canvas goCanvas;

    private GoBoard goBoard = GoBoard.GetInstance(10);

    @FXML
    void goCanvas_mouseClicked(MouseEvent event)
    {
        if (goBoard == null)
            goBoard = GoBoard.GetInstance();

        double x = event.getSceneX() - goCanvas.getLayoutX();
        double y = event.getSceneY() - goCanvas.getLayoutY();

        double h = goCanvas.getHeight();
        double w = goCanvas.getWidth();

        double dh = h / (double)goBoard.getBoardSize();
        double dw = w / (double)goBoard.getBoardSize();

        int i = (int)x / (int)dw;
        int j = (int) y / (int)dh;

        goBoard.playMove(i, j);

        goBoard.drawBoard(goCanvas.getGraphicsContext2D());
    }

    @FXML
    void goCanvas_mouseMoved(Event event)
    {

    }

    @FXML
    void btnNewGame_Clicked(Event event)
    {
        if (txtBoardSize.getText().isEmpty() ||
                !txtBoardSize.getText().matches("[0-9]+")
                )
            goBoard = GoBoard.MakeInstance();

        else
            goBoard = GoBoard.MakeInstance(
                    Integer.parseInt(txtBoardSize.getText())
            );

        goBoard.drawBoard(goCanvas.getGraphicsContext2D());
    }
}
