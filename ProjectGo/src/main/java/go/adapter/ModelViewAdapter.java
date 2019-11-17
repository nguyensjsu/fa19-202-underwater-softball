package go.adapter;

import java.awt.Color;
import java.awt.Point;

import go.controller.GoMoveController;
import go.controller.GoViewController;
import go.model.datamodel.GoMove;
import go.model.datamodel.GoPoint;
import go.model.datamodel.StoneColor;
import go.model.observer.*;
import go.view.datamodel.impl.GoViewImpl;
import go.view.observer.GoViewObserver;
import go.view.observer.GoViewSubject;

public class ModelViewAdapter implements GoMoveObserver, GoGameObserver {

    private static final int BOARD_SIZE = 9;
    public static final int NUM_TILES = BOARD_SIZE - 1;
    public static final int TILE_SIZE = GoViewImpl.CENTER_DIM.width / (NUM_TILES + 2);
    public static final int BORDER_SIZE = TILE_SIZE;

    private GoViewController goViewController;

    @Override
    public void handlePieceAdditionEvent(GoMove move) {
        //goViewController.drawStone(x, y, color);
    }
    @Override
    public void handlePieceRemovalEvent(GoPoint point) {
        // TODO Auto-generated method stub

    }
    @Override
    public void handleGameEnd(StoneColor winner) {
        // TODO Auto-generated method stub

    }

}
