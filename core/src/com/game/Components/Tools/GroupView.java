package com.game.Components.Tools;

import com.badlogic.gdx.scenes.scene2d.Group;

public abstract class GroupView extends Group  {
    public GroupView(){
        super();

    }
    public void create(){};
    public void act( float delta)
    {
        super.act(delta);
    }
    public void handleTouch(){

    }
}
