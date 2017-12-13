package com.game;

import GameBoardAssets.HexagonActor;
import GameConstants.Constants;
import GameLogic.Results;
import Interfaces.GroupView;
import Screens.GameScreen;
import Tools.Link;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import org.codetome.hexameter.core.api.Hexagon;
import org.codetome.hexameter.core.api.HexagonalGrid;
import rx.Observable;
import rx.functions.Action1;

public class Board extends GroupView{
    private HexagonalGrid<Link> grid;
    private boolean over;

    private boolean secondTouch;
    private Hexagon first;
    private Hexagon second;


    public Board(){
        super();


        secondTouch = false;
        create();
    }




    public void create(){
        this.grid = Constants.grid.build();

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

                addActor(hexActor);
            }
        });
    }
//    //!!
//    public void act( float delta) {
//        //System.out.println("board");
//
//
//
//                /*hexActor.addListener(new ClickListener(){
//
//
//
//                    @Override
//                    public void clicked(InputEvent event, float x, float y) {
//
//                        System.out.println(hexActor.getHexagon().getGridX() + ", " + hexActor.getHexagon().getGridY() + ", " + hexActor.getHexagon().getGridZ());
//                        if(!secondTouch){
//                            //GameScreen.manager.getGamingPlayer().setHexMove1(hexagon);
//                            secondTouch = true;
//                            first = hexActor.getHexagon();
//                        } else {
//                            //GameScreen.manager.getGamingPlayer().setHexMove2(hexagon);
//                            secondTouch = false;
//                            second = hexActor.getHexagon();
//
//                        }
//                    }*/
//                    /**
//                     HexagonActor actorOne;
//
//                     // Ensure that what we've clicked on is an empty space to place the tile upon
//                     if(touched[0] != null && hexActor.getHexColor().equals("EMPTY")){
//                     hexActor.setSprite(touched[0]);
//                     hexActor.setHexColor(HexagonActor.getSpriteColor(hexActor));
//                     first = hexActor;
//                     touched[0] = null;
//                     Player.updateScore(manager.getGamingPlayer(), hexActor, manager.getBoard(), first);
//
//
//
//                     // Place the second hexagon in the tile
//                     } else if (touched[0] == null && touched[1] != null && hexActor.getSprite() == Constants.emptySprite){
//                     if (manager.getBoard().getNeighborsOf(first.getHexagon()).contains(hexActor.getHexagon())){
//                     hexActor.setSprite(touched[1]);
//                     hexActor.setHexColor(HexagonActor.getSpriteColor(hexActor));
//                     touched[1] = null;
//                     Player.updateScore(manager.getGamingPlayer(), hexActor, manager.getBoard(), first);
//                     first = null;
//
//                     // after the second click remove from hand the placed tile
//                     manager.getGamingPlayer().getGamePieces().remove(selectedTileIndex);
//
//                     // take a new one
//                     Pieces.takePiece(manager.getBag(), manager.getGamingPlayer().getGamePieces());
//
//                     // and set the new sprites
//                     int ind = 0;
//                     for (Actor hex : selectedTile.getChildren()){
//                     if (hex instanceof HexagonActor){
//                     HexagonActor one = (HexagonActor) hex;
//                     one.setSprite(manager.getGamingPlayer().getGamePieces().get(0)[ind]);
//                     ind++;
//                     }
//                     }
//                     selectedTile.moveBy(0, -30);
//
//                     //TODO: Fix this, find way to change gaming player.
//                     //manager.changeGamingPlayer();
//
//
//                     }
//                     // if you click on the same tile you just placed
//                     else {
//                     System.out.println("Select a neighbor");
//                     }
//                     } else if (touched[0] == null && touched[1] == null){
//                     System.out.println("Select a piece from your hand!");
//                     } else {
//                     System.out.println("This slot is full! Color here is: " + hexActor.getHexColor());
//                     }*/
//                //});
//
//
//      //  super.act(delta);
//    }
    public Hexagon getFirst() {
        return first;
    }

    public Hexagon getSecond() {
        return second;
    }

    public boolean gameOver() {
        this.over = true;
        grid.getHexagons().forEach(new Action1<Hexagon<Link>>() {
            @Override
            public void call(Hexagon hexagon) {
                if (hexagon.getSatelliteData().isPresent()) {
                    Link hexLink = (Link) hexagon.getSatelliteData().get();
                    HexagonActor currentHexActor = hexLink.getActor();

                    if (currentHexActor.getHexColor().equals("EMPTY")) {
                        //System.out.println("(" + hexagon.getGridX() + ", " + hexagon.getGridY() + ", " + hexagon.getGridZ() + ") is empty");
                        for (Object hex : grid.getNeighborsOf(hexagon)) {
                            if (hex instanceof Hexagon) {
                                Hexagon currentNeighbor = (Hexagon) hex;

                                if (currentNeighbor.getSatelliteData().isPresent()) {
                                    Link neighLink = (Link) hexagon.getSatelliteData().get();
                                    HexagonActor neighHexActor = neighLink.getActor();
                                    if (neighHexActor.getHexColor().equals("EMPTY")) {
                                        //System.out.println("empty neighbor");
                                        over = false;

                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
        if (over){
            System.out.println("GAME OVER");
        }
        return over;
    }







    public HexagonalGrid<Link> getGrid() {
        return grid;
    }
}
