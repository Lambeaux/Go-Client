import data.GoBoard;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextField;

public class GuiController
{
    @FXML
    private TextField txtBoardSize;
    @FXML
    private Canvas goCanvas;

    private GoBoard goBoard = GoBoard.GetInstance(10);

    @FXML
    void goCanvas_mouseClicked(Event event)
    {

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
