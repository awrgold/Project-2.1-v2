package Screens;


import static org.codetome.hexameter.core.api.HexagonOrientation.POINTY_TOP;
import static org.codetome.hexameter.core.api.HexagonalGridLayout.HEXAGONAL;
import static org.codetome.hexameter.core.api.HexagonalGridLayout.RECTANGULAR;

import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.metal.MetalBorders.TableHeaderBorder;

import GameBoardAssets.HexagonActor;
import GameConstants.Constants;
import GameScoreAssets.Bar;
import Tools.Link;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.game.Pieces;
import org.codetome.hexameter.core.api.Hexagon;
import org.codetome.hexameter.core.api.HexagonOrientation;
import org.codetome.hexameter.core.api.HexagonalGrid;
import org.codetome.hexameter.core.api.HexagonalGridBuilder;
import org.codetome.hexameter.core.api.HexagonalGridLayout;
import org.codetome.hexameter.core.api.Point;
import org.codetome.hexameter.core.api.contract.HexagonDataStorage;
import org.codetome.hexameter.core.api.defaults.DefaultSatelliteData;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.ui.Table.Debug;

import rx.functions.Action1;


public class GameScreen extends AbstractScreen {


    // Ratio of width and height of a regular hexagon.
    public static final int HEXAGON_WIDTH = 100;
    public static final int HEXAGON_HEIGHT = 100;
    
    public HexagonalGrid grid;
    public HexagonalGrid[] tiles = new HexagonalGrid[6];
    public List<HexagonActor> hexagonActors = new ArrayList<HexagonActor>();

    private Table root;
    private Group hexagonView;
    private Group[] tileView = new Group[6];
    //we use this to store informations about the selected tile
    private Sprite[] touched = {null, null};

	//private Texture mainMenuButton;
    private ImageButton tileButton;
    private ImageButton.ImageButtonStyle tileStyle;
    private TextureAtlas tileButtonAtlas;
    private Skin tileButtonSkin;

    private SpriteBatch batch;
    private Skin skin;
    
    //public HexagonalGridCalculator calculator = builder.buildCalculatorFor(grid);

    public GameScreen() {
        //batch = new SpriteBatch();

        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        Gdx.graphics.setWindowedMode(Constants.getWindowWidth(),Constants.getWindowHeight());
    }


    // Subclasses must load actors in this method
    @SuppressWarnings("unchecked")
	public void buildStage() {
        Stage stage  = new Stage();
        // ...

        //board grid
        final int GRID_HEIGHT = 11;
        final int GRID_WIDTH = 11;
        final HexagonalGridLayout GRID_LAYOUT = HEXAGONAL;
        final HexagonOrientation ORIENTATION = POINTY_TOP;

        final double RADIUS = Constants.getHexRadius();



        // ...
        //grid builder
        HexagonalGridBuilder<Link> builder = new HexagonalGridBuilder<Link>()
                .setGridHeight(GRID_HEIGHT)
                .setGridWidth(GRID_WIDTH)
                .setGridLayout(GRID_LAYOUT)
                .setOrientation(ORIENTATION)
                .setRadius(RADIUS);


        HexagonalGrid<Link> grid = builder.build();
        this.grid = grid;

        // Create a HexagonActor for each Hexagon and attach it to the group
        this.hexagonView = new Group();

        grid.getHexagons().forEach(new Action1<Hexagon<Link>>() {
            @Override
            public void call(Hexagon hexagon) {

                // Create the Actor and link it to the hexagon (and vice-versa)
                final HexagonActor hexActor = new HexagonActor(hexagon);

                Sprite emptySprite = new Sprite(new Texture(Gdx.files.internal("4players.png")));
                Sprite corner1Sprite = new Sprite(new Texture(Gdx.files.internal("colours/blue.png")));
                Sprite corner2Sprite = new Sprite(new Texture(Gdx.files.internal("colours/yellow.png")));
                Sprite corner3Sprite = new Sprite(new Texture(Gdx.files.internal("colours/orange.png")));
                Sprite corner4Sprite = new Sprite(new Texture(Gdx.files.internal("colours/purple.png")));
                Sprite corner5Sprite = new Sprite(new Texture(Gdx.files.internal("colours/violet.png")));
                Sprite corner6Sprite = new Sprite(new Texture(Gdx.files.internal("colours/red.png")));

                hexActor.setPosition((float) hexagon.getCenterX(), (float) hexagon.getCenterY());

                hexagonView.addActor(hexActor);

                hexagon.setSatelliteData(new Link(hexActor));

                //STARTING COLOURS FOR EACH HEXAGON ON THE BOARD

                if (hexActor.getHexagon().getGridX() == -2 && hexActor.getHexagon().getGridY() == -8 && hexActor.getHexagon().getGridZ() == 10) {
                    hexActor.setSprite(corner1Sprite);
                    hexActor.setHexColor("B");
                } else if (hexActor.getHexagon().getGridX() == 3 && hexActor.getHexagon().getGridY() == -13 && hexActor.getHexagon().getGridZ() == 10) {
                    hexActor.setSprite(corner2Sprite);
                    hexActor.setHexColor("Y");
                } else if (hexActor.getHexagon().getGridX() == 8 && hexActor.getHexagon().getGridY() == -13 && hexActor.getHexagon().getGridZ() == 5) {
                    hexActor.setSprite(corner3Sprite);
                    hexActor.setHexColor("O");
                } else if (hexActor.getHexagon().getGridX() == 8 && hexActor.getHexagon().getGridY() == -8 && hexActor.getHexagon().getGridZ() == 0) {
                    hexActor.setSprite(corner4Sprite);
                    hexActor.setHexColor("P");
                } else if (hexActor.getHexagon().getGridX() == 3 && hexActor.getHexagon().getGridY() == -3 && hexActor.getHexagon().getGridZ() == 0) {
                    hexActor.setSprite(corner5Sprite);
                    hexActor.setHexColor("V");
                } else if (hexActor.getHexagon().getGridX() == -2 && hexActor.getHexagon().getGridY() == -3 && hexActor.getHexagon().getGridZ() == 5) {
                    hexActor.setSprite(corner6Sprite);
                    hexActor.setHexColor("R");
                } else {
                    hexActor.setSprite(emptySprite);
                }



                // TODO: EXAMPLE WHERE I CHANGE THE COLOR ON HOVER OVER.
                // DO YOUR SHIT HERE IF YOU WANT TO INTERACT WITH THE HEXAGON FOR SOME REASON
                // LIKE PER EXAMPLE IF YOU HAVE ONE SELECTED AND NEED IT PLACED.


                hexActor.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        System.out.println(hexActor.getHexagon().getGridX() + ", " + hexActor.getHexagon().getGridY() + ", " + hexActor.getHexagon().getGridZ());

                        if(touched[0] != null && hexActor.getSprite() == emptySprite){
                            hexActor.setSprite(touched[0]);
                            touched[0] = null;
                        } else if (touched[0] == null && touched[1] != null && hexActor.getSprite() == emptySprite){
                            hexActor.setSprite(touched[1]);
                            touched[1] = null;
                        } else if (touched[0] == null && touched[1] == null){
                            System.out.println("Select a piece from your hand!");
                        } else {
                            System.out.println("This slot is full! Color here is: " + hexActor.getHexColor());
                        }


                    }
                });

            }
        });


        //create the BAG
        ArrayList<Sprite[]> bag = Pieces.createBagPieces();

        //distribute pieces to player 1
        ArrayList<Sprite[]> player1pieces = Pieces.distributePieces(bag);
        ArrayList<Sprite[]> player2pieces = Pieces.distributePieces(bag);

        //tile grid (1x2)
        final int TILE_HEIGHT = 1;
        final int TILE_WIDTH = 2;
        final HexagonalGridLayout TILE_LAYOUT = RECTANGULAR;
        final HexagonOrientation TILE_ORIENTATION = POINTY_TOP;
        final double TILE_RADIUS = Constants.getHexRadius();

        //tile builder
        HexagonalGridBuilder<Link> tileBuilder = new HexagonalGridBuilder<Link>()
                .setGridHeight(TILE_HEIGHT)
                .setGridWidth(TILE_WIDTH)
                .setGridLayout(TILE_LAYOUT)
                .setOrientation(TILE_ORIENTATION)
                .setRadius(TILE_RADIUS);


        //now repeat for the 6 tiles
        for (int i = 0; i < 6; i++){

            //give it a grid (2x1)
            HexagonalGrid<Link> tile = tileBuilder.build();
            this.tiles[i] = tile;

            //create a group that contains the 2 hexagons
            Group tileGroup = new Group();

            //get one of the six couple of sprites
            Sprite[] oneOfSix = player1pieces.get(i);



            //override call for each grid
            tiles[i].getHexagons().forEach(new Action1<Hexagon<Link>>() {
                @Override
                public void call(Hexagon hexagon) {
                    final HexagonActor hexTile = new HexagonActor(hexagon);

                    //give both the sprites
                    if(hexagon.getGridX() == 0) {
                        hexTile.setSprite(oneOfSix[0]);
                    } else {
                        hexTile.setSprite(oneOfSix[1]);
                    }


                    hexTile.setPosition((float) hexagon.getCenterX(), (float) hexagon.getCenterY());

                    //and pass everything in tileGroup
                    tileGroup.addActor(hexTile);

                    hexagon.setSatelliteData(new Link(hexTile));


                    /*DragAndDrop dnd  = new DragAndDrop();

                    dnd.addSource(new DragAndDrop.Source(tileGroup) {
                        DragAndDrop.Payload payload =  new DragAndDrop.Payload();

                        public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {  // here is the dragstart method where you take the item selected from the handtile , once you take it, it removes itself from the hand
                            payload.setObject(tileGroup);
                            payload.setDragActor(tileGroup);
                            return payload;
                        }

                        public void dragStop (InputEvent event, float x, float y, int pointer, DragAndDrop.Payload payload, DragAndDrop.Target target) {
                            // trying to replace the tile to its initial position if it's not placed on the board CURRENTLY NOT WORKING !!!
                            x = (float )hexagon.getCenterX();
                            y = (float) hexagon.getCenterY();
                            dnd.setDragActorPosition(-tileGroup.getWidth() / 2, tileGroup.getHeight() / 2);

                            if(target == null){
                                tileGroup.setPosition(x,y);
                            }
                            // Actor gets removed from the stage apparently
                            //stage.addActor(tileGroup);
                        }
                    });

                    // Target is the place where the tile should be placed
                    dnd.addTarget(new DragAndDrop.Target(hexagonView) { // This is the target class where i'm trying to point out the board BUT ALSO NOT WORKING NOW
                        @Override
                        public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                            return true;
                        }

                        @Override
                        public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {

                            //hexagonView.getItems().add((HexagonActor) payload.getObject());
                            Actor hexagon = (Actor) payload.getObject();
                            hexagonView.addActor(hexagon);
                        }
                    }); */


                    hexTile.addListener(new ClickListener(){
                        @Override
                        public void clicked(InputEvent event, float x, float y) {

                            touched[0] = hexTile.getSprite();

                            Actor two = hexTile.getParent().getChildren().get(Math.abs(hexTile.getHexagon().getGridX() - 1));

                            if (two instanceof HexagonActor){
                                HexagonActor other = (HexagonActor) two;
                                touched[1] = other.getSprite();
                            }

                        }
                    });

                }
            });

            //add tileGroup to tileView
            this.tileView[i] = tileGroup;
        }




        this.root = new Table();
        this.root.setFillParent(true);

        //root.debug(Debug.all);

        // Create the score column
        Table scoreColumn = new Table();
        //scoreColumn.debug(Debug.all);

        Bar bar1a = new Bar(100,50,Color.RED,getRandom());
        Bar bar2a = new Bar(100,50,Color.BLUE,getRandom());
        Bar bar3a = new Bar(100,50,Color.PURPLE,getRandom());
        Bar bar4a = new Bar(100,50,Color.YELLOW,getRandom());
        Bar bar5a = new Bar(100,50,Color.GREEN,getRandom());
        Bar bar6a = new Bar(100,50,Color.ORANGE,getRandom());



        scoreColumn.add(bar1a).expand().left();
        scoreColumn.row();
        scoreColumn.add(bar2a).expand().left();
        scoreColumn.row();
        scoreColumn.add(bar3a).expand().left();
        scoreColumn.row();
        scoreColumn.add(bar4a).expand().left();
        scoreColumn.row();
        scoreColumn.add(bar5a).expand().left();
        scoreColumn.row();
        scoreColumn.add(bar6a).expand().left();
        //  scoreColumn.add(bar);
        // scoreColumn.setColor(Color.BLUE);
        scoreColumn.row();
        scoreColumn.add(new Label("Player 1 Score", skin)).bottom();
        scoreColumn.row();
        Bar bar1b = new Bar(200,50,Color.RED,getRandom());
        Bar bar2b = new Bar(100,50,Color.BLUE,getRandom());
        Bar bar3b = new Bar(100,50,Color.PURPLE,getRandom());
        Bar bar4b = new Bar(100,50,Color.YELLOW,getRandom());
        Bar bar5b = new Bar(100,50,Color.GREEN,getRandom());
        Bar bar6b = new Bar(100,50,Color.ORANGE,getRandom());

        scoreColumn.add(bar1b).expand().fill();
        scoreColumn.row();
        scoreColumn.add(bar2b).expand().fill();
        scoreColumn.row();
        scoreColumn.add(bar3b).expand().fill();
        scoreColumn.row();
        scoreColumn.add(bar4b).expand().fill();
        scoreColumn.row();
        scoreColumn.add(bar5b).expand().fill();
        scoreColumn.row();
        scoreColumn.add(bar6b).expand().fill();
        //scoreView2.setColor(Color.RED);
        // scoreColumn.add(scoreView2).expand().left();
        //  scoreColumn.setColor(Color.RED);
//		for (int i = 0; i < 6; i++) {
//			scoreColumn.add(new TextButton("MEMEMEME", skin)).colspan(1).expandX().left();
//		}

        scoreColumn.row();
        scoreColumn.add(new Label("Player 2 Score", skin)).bottom();

        root.add(scoreColumn).colspan(1).expand().fill();

        // Create the board
        Table boardColumn = new Table();


        //boardColumn.debug(Debug.all);
        boardColumn.add(hexagonView).colspan(5).expand().fill();
        boardColumn.row().bottom().colspan(5);


        //place the tiles on the screen
        for (int i = 0; i < 6; i++) {
			boardColumn.add(tileView[i]).expandX().fill().colspan(1);
		}
        
        root.add(boardColumn).expand().fill();
        root.pack();
                
        addActor(root);





    }


    public void dispose(){
        super.dispose();
        batch.dispose();
    }

    public int getRandom(){
        int n = (int) (Math.random()*100);
        return n;
    }
    //public void show(){
    	//mainMenuButton = new Texture("mainmenu.png");
	//}

}



