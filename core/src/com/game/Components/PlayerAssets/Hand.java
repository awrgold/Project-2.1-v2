package com.game.Components.PlayerAssets;


import com.game.Components.Tools.GroupView;

import java.util.ArrayList;

public class Hand extends GroupView{

    private ArrayList<Tile> hand;
    private float s;

    public Hand(ArrayList<Tile> tiles){
        this.hand = tiles;
        create();
    }

    public void create(){
        float x = 0;
        for (Tile tile: hand){
            tile.setPosition(x, 0);
            addActor(tile);
            x += 180;
        }
    }

    public ArrayList<Tile> getPieces() {
        return hand;
    }

    public void pickFromBag(Tile picked){
        hand.add(picked);
        picked.setPosition(s, 0);
        addActor(picked);
    }

    public void removeFromHand(Tile placed){
        s = placed.getX();
        hand.remove(placed);
        removeActor(placed);
    }

    public void changeTiles(ArrayList<Tile> tiles){
        this.hand = tiles;
    }

    public void act(float delta) {
        super.act(delta);
    }
}