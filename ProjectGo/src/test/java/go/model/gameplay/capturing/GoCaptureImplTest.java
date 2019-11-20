package go.model.gameplay.capturing;

import go.model.datamodel.GoGameBoard;
import go.model.datamodel.GoMove;
import go.model.datamodel.GoPoint;
import go.model.datamodel.StoneColor;
import go.model.datamodel.impl.GoGameBoardImpl;
import go.model.datamodel.impl.GoMoveImpl;
import go.model.datamodel.impl.GoPointImpl;
import go.model.gameplay.GoCapture;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GoCaptureImplTest {
    private static final int TEST_SIZE = 5;
    private GoCapture goCapture;

    @Before
    public void setUp() throws Exception {
        goCapture = new GoCaptureImpl(TEST_SIZE);
    }

    private GoMove getMove(int x, int y, StoneColor color) {
        return new GoMoveImpl(GoPointImpl.of(x, y), color);
    }

    @Test
    public void testCapturePiecesForMove_CapturesSimpleSurroundedStone() {
        // setup
        GoGameBoard goGameBoard = new GoGameBoardImpl(TEST_SIZE);
        // set up the board so white is almost surrounded
        goGameBoard.setStone(getMove(1, 1, StoneColor.WHITE));
        goGameBoard.setStone(getMove(0, 1, StoneColor.BLACK));
        goGameBoard.setStone(getMove(1, 0, StoneColor.BLACK));
        goGameBoard.setStone(getMove(2, 1, StoneColor.BLACK));
        GoMove capturingMove = getMove(1, 2, StoneColor.BLACK);

        // run
        List<GoPoint> capturedStoneLocations = goCapture.capturePiecesForMove(goGameBoard, capturingMove);

        // verify
        // one piece was captured
        assertEquals(1, capturedStoneLocations.size());
        // it was the white piece
        assertEquals(GoPointImpl.of(1, 1), capturedStoneLocations.get(0));
    }
    @Test
    public void testCapturePiecesForMove_AdjacentStonesProtectNeighbors() {
        // setup
        GoGameBoard goGameBoard = new GoGameBoardImpl(TEST_SIZE);
        // set up the board - white has two adjacent pieces.
        // black is near surrounding it but two moves away. It must also have stones on 1, 2 and 2, 2 to capture the white stones.
        goGameBoard.setStone(getMove(1, 1, StoneColor.WHITE));
        goGameBoard.setStone(getMove(2, 1, StoneColor.WHITE));
        goGameBoard.setStone(getMove(1, 0, StoneColor.BLACK));
        goGameBoard.setStone(getMove(2, 0, StoneColor.BLACK));
        goGameBoard.setStone(getMove(0, 1, StoneColor.BLACK));
        goGameBoard.setStone(getMove(3, 1, StoneColor.BLACK));
        GoMove notCapturingMove = getMove(1, 2, StoneColor.BLACK);

        // run
        List<GoPoint> capturedStoneLocations = goCapture.capturePiecesForMove(goGameBoard, notCapturingMove);

        // verify
        // no pieces were captured
        assertEquals(0, capturedStoneLocations.size());
    }
    @Test
    public void testCapturePiecesForMove_CapturesEdgeAndCornerStones() {
        // setup
        GoGameBoard goGameBoard = new GoGameBoardImpl(TEST_SIZE);
        // set up the board - black has a corner piece, so white only needs two stones to capture it
        goGameBoard.setStone(getMove(0, 0, StoneColor.BLACK));
        goGameBoard.setStone(getMove(1, 0, StoneColor.WHITE));
        GoMove capturingMove = getMove(0, 1, StoneColor.WHITE);

        // run
        List<GoPoint> capturedStoneLocations = goCapture.capturePiecesForMove(goGameBoard, capturingMove);

        // verify
        // one piece was captured
        assertEquals(1, capturedStoneLocations.size());
        // the captured piece was black's corner piece
        assertEquals(GoPointImpl.of(0, 0), capturedStoneLocations.get(0));
    }

    @Test
    public void testCapturePiecesForMove_CapturesMultipleStoneGroup() {
        // setup
        GoGameBoard goGameBoard = new GoGameBoardImpl(TEST_SIZE);
        // set up the board - white has two adjacent pieces.
        // black is near surrounding the group - black must also have a stone on 1, 2 to capture the white stones.
        goGameBoard.setStone(getMove(1, 1, StoneColor.WHITE));
        goGameBoard.setStone(getMove(2, 1, StoneColor.WHITE));
        goGameBoard.setStone(getMove(1, 0, StoneColor.BLACK));
        goGameBoard.setStone(getMove(2, 0, StoneColor.BLACK));
        goGameBoard.setStone(getMove(0, 1, StoneColor.BLACK));
        goGameBoard.setStone(getMove(3, 1, StoneColor.BLACK));
        goGameBoard.setStone(getMove(2, 2, StoneColor.BLACK));

        GoMove capturingMove = getMove(1, 2, StoneColor.BLACK);

        // run
        List<GoPoint> capturedStoneLocations = goCapture.capturePiecesForMove(goGameBoard, capturingMove);

        // verify
        // two pieces were captured
        assertEquals(2, capturedStoneLocations.size());
        // white's group was captured
        assertTrue(capturedStoneLocations.contains(GoPointImpl.of(1, 1)));
        assertTrue(capturedStoneLocations.contains(GoPointImpl.of(2, 1)));
    }



}