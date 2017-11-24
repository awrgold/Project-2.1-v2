package com.game;

import GameBoardAssets.HexagonActor;
import GameConstants.Constants;
import GameLogic.Results;
import Screens.GameScreen;
import Tools.Link;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import org.codetome.hexameter.core.api.Hexagon;
import org.codetome.hexameter.core.api.HexagonalGrid;
import rx.functions.Action1;

public class Board{
    private HexagonalGrid<Link> grid;
    private Group boardView;
    private boolean secondTouch;


    public Board(){
        this.grid = Constants.grid.build();
        boardView = new Group();
        secondTouch = false;
    }

    public Group initializeBoard(){

        grid.getHexagons().forEach(new Action1<Hexagon<Link>>() {
            @Override
            public void call(Hexagon hexagon) {
                // Create the Actor and link it to the hexagon (and vice-versa)
                final HexagonActor hexActor = new HexagonActor(hexagon);

                hexActor.setPosition((float) hexagon.getCenterX(), (float) hexagon.getCenterY());

                //STARTING COLOURS FOR EACH HEXAGON ON THE BOARD

                if (hexActor.getHexagon().getGridX() == -2 && hexActor.getHexagon().getGridY() == -8 && hexActor.getHexagon().getGridZ() == 10) {
                    hexActor.setHexColor("B");
                } else if (hexActor.getHexagon().getGridX() == 3 && hexActor.getHexagon().getGridY() == -13 && hexActor.getHexagon().getGridZ() == 10) {
                    hexActor.setHexColor("Y");
                } else if (hexActor.getHexagon().getGridX() == 8 && hexActor.getHexagon().getGridY() == -13 && hexActor.getHexagon().getGridZ() == 5) {
                    hexActor.setHexColor("O");
                } else if (hexActor.getHexagon().getGridX() == 8 && hexActor.getHexagon().getGridY() == -8 && hexActor.getHexagon().getGridZ() == 0) {
                    hexActor.setHexColor("P");
                } else if (hexActor.getHexagon().getGridX() == 3 && hexActor.getHexagon().getGridY() == -3 && hexActor.getHexagon().getGridZ() == 0) {
                    hexActor.setHexColor("V");
                } else if (hexActor.getHexagon().getGridX() == -2 && hexActor.getHexagon().getGridY() == -3 && hexActor.getHexagon().getGridZ() == 5) {
                    hexActor.setHexColor("R");
                } else {
                    hexActor.setHexColor("EMPTY");
                }

                hexagon.setSatelliteData(new Link(hexActor));

                boardView.addActor(hexActor);

                hexActor.addListener(new ClickListener(){



                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        System.out.println(hexActor.getHexagon().getGridX() + ", " + hexActor.getHexagon().getGridY() + ", " + hexActor.getHexagon().getGridZ());
                        if(!secondTouch){
                            //GameScreen.manager.getGamingPlayer().setHexMove1(hexagon);
                            secondTouch = true;
                        } else {
                            //GameScreen.manager.getGamingPlayer().setHexMove2(hexagon);
                            secondTouch = false;
                        }

/**
                        HexagonActor actorOne;

                        // Ensure that what we've clicked on is an empty space to place the tile upon
                        if(touched[0] != null && hexActor.getHexColor().equals("EMPTY")){
                            hexActor.setSprite(touched[0]);
                            hexActor.setHexColor(HexagonActor.getSpriteColor(hexActor));
                            first = hexActor;
                            touched[0] = null;
                            Player.updateScore(manager.getGamingPlayer(), hexActor, manager.getBoard(), first);



                            // Place the second hexagon in the tile
                        } else if (touched[0] == null && touched[1] != null && hexActor.getSprite() == Constants.emptySprite){
                            if (manager.getBoard().getNeighborsOf(first.getHexagon()).contains(hexActor.getHexagon())){
                                hexActor.setSprite(touched[1]);
                                hexActor.setHexColor(HexagonActor.getSpriteColor(hexActor));
                                touched[1] = null;
                                Player.updateScore(manager.getGamingPlayer(), hexActor, manager.getBoard(), first);
                                first = null;

                                // after the second click remove from hand the placed tile
                                manager.getGamingPlayer().getGamePieces().remove(selectedTileIndex);

                                // take a new one
                                Pieces.takePiece(manager.getBag(), manager.getGamingPlayer().getGamePieces());

                                // and set the new sprites
                                int ind = 0;
                                for (Actor hex : selectedTile.getChildren()){
                                    if (hex instanceof HexagonActor){
                                        HexagonActor one = (HexagonActor) hex;
                                        one.setSprite(manager.getGamingPlayer().getGamePieces().get(0)[ind]);
                                        ind++;
                                    }
                                }
                                selectedTile.moveBy(0, -30);

                                //TODO: Fix this, find way to change gaming player.
                                //manager.changeGamingPlayer();


                            }
                            // if you click on the same tile you just placed
                            else {
                                System.out.println("Select a neighbor");
                            }
                        } else if (touched[0] == null && touched[1] == null){
                            System.out.println("Select a piece from your hand!");
                        } else {
                            System.out.println("This slot is full! Color here is: " + hexActor.getHexColor());
                        }*/
                    }
                });
            }
        });

        return boardView;
    }

//    public Board boardAfterMove(CellPosition position, CellValue newValue) {
//        Board nextBoard = new Board(this);
//        nextBoard.cells[position.r][position.c].position = position;
//        nextBoard.cells[position.r][position.c].value = newValue;
//        return nextBoard;
//    }

//    public void setCell(CellPosition position, CellValue value) {
//        if (cells[position.r][position.c].value == CellValue.EMPTY) {
//            cells[position.r][position.c].value = value;
//        } else {
//            Gdx.app.log(TAG, "cell already taken");
//        }
//    }

    public void clearBoard() {
//        this.cells = new Cell[3][3];
//        for (int r = 0; r < 3; r++) {
//            for (int c = 0; c < 3; c++) {
//                cells[r][c] = new Cell(new CellPosition(r, c));
//            }
//        }
    }

  //  public Tile cellAtPosition(CellPosition position) {
  //      return t
  //  }

 //   public Results getResults() {
//        Results tempResults = new Results();
//        for (CellPosition[] pattern: Constants.WINNING_PATTERNS) {
//            Cell cell = cells[pattern[0].r][pattern[0].c];
//            if (cell.value == CellValue.EMPTY) {
//                continue;
//            }
//            if (cells[pattern[0].r][pattern[0].c].value == cells[pattern[1].r][pattern[1].c].value &&
//                    cells[pattern[1].r][pattern[1].c].value == cells[pattern[2].r][pattern[2].c].value) {
//                tempResults.setWinner(cells[pattern[0].r][pattern[0].c].value);
//                tempResults.setHasWinner(true);
//                break;
//            }
//        }
//        return tempResults;
  //  }
}