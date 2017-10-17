package com.game;

import GameBoardAssets.HexagonActor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import org.codetome.hexameter.core.api.CubeCoordinate;
import org.codetome.hexameter.core.api.Hexagon;
import org.codetome.hexameter.core.api.HexagonalGrid;

import java.util.ArrayList;

public class Player {

    private boolean isAI;
    private String name;

    private int[] PlayerScore = new int[6];
/*
int[0] = blue
int[1] = green
int[2] = orange
int[3] = purple
int[4] = red
int[5] = yellow
*/

    int playerNo;
    ArrayList<Sprite[]> gamePieces = new ArrayList<Sprite[]>();

    public Player(int playerNo, ArrayList<Sprite[]> gamePieces) {
        this.playerNo = playerNo;
        this.gamePieces = gamePieces;
    }


    public boolean isAI() {
        this.isAI = isAI;
        return isAI;
    }

    public ArrayList<Sprite[]> getGamePieces() {
        return this.gamePieces;
    }

    public int getPlayerNo() {
        return playerNo;
    }

    public static void updateScore(Player player, Hexagon hex, HexagonalGrid hexGrid) {
        int result = 0;

        int[] playerScore = player.PlayerScore;

        //Calculate color combination from hex to left:
        result = result + CalculateScoreHexToLeft(hexGrid, hex);
        //Calculate color combination from hex to right
        result = result + CalculateScoreHexToRight(hexGrid, hex);
        //Calculate color combination from hex to bottom left
        //result = result + CalculateScoreHexToBotLeft(hexGrid, hex);
        //Calculate color combination from hex to bottom right
        //result = result + CalculateScoreHexToBotRight(hexGrid, hex);
        //Calculate color combination from hex to top left
        //result = result + CalculateScoreHexToTopLeft(hexGrid, hex);
        //Calculate color combination from hex to top right
        //result = result + CalculateScoreHexToTopRight(hexGrid, hex);


        //Update score of designated tile sort:
        HexagonActor hexActor = new HexagonActor(hex);
        String color = hexActor.getHexColor().toString();

        if (color == "B") playerScore[0] = playerScore[0] + result;
        if (color == "G") playerScore[1] = playerScore[1] + result;
        if (color == "O") playerScore[2] = playerScore[2] + result;
        if (color == "P") playerScore[3] = playerScore[3] + result;
        if (color == "R") playerScore[4] = playerScore[4] + result;
        if (color == "Y") playerScore[5] = playerScore[5] + result;

    }


    public static int CalculateScoreHexToLeft(HexagonalGrid hexGrid, Hexagon hex) {
        int result = 0;
        boolean sameColor = true;

        Hexagon currentHex;
        Hexagon currentHex2;
        CubeCoordinate ccCurrentHex = CubeCoordinate.fromCoordinates(hex.getGridX(), hex.getGridZ());
        CubeCoordinate ccCurrentHex2;

        currentHex = (Hexagon) hexGrid.getByCubeCoordinate(ccCurrentHex).get();

        HexagonActor hexActor1;
        HexagonActor hexActor2;

        while (currentHex.getGridX() > -2 && sameColor == true) {
            hexActor1 = new HexagonActor(currentHex);
            ccCurrentHex2 = CubeCoordinate.fromCoordinates(currentHex.getGridX() - 1, currentHex.getGridZ());
            currentHex2 = (Hexagon) hexGrid.getByCubeCoordinate(ccCurrentHex2).get();

            hexActor2 = new HexagonActor(currentHex2);

            if (hexActor1.getHexColor() == hexActor2.getHexColor()) {
                result += 1;
                currentHex = currentHex2;
            } else {
                sameColor = false;
            }
        }
        return result;

    }

    public static int CalculateScoreHexToRight(HexagonalGrid hexGrid, Hexagon hex) {
        int result = 0;
        boolean sameColor = true;

        Hexagon currentHex;
        Hexagon currentHex2;
        CubeCoordinate ccCurrentHex = CubeCoordinate.fromCoordinates(hex.getGridX(), hex.getGridZ());
        CubeCoordinate ccCurrentHex2;

        currentHex = (Hexagon) hexGrid.getByCubeCoordinate(ccCurrentHex).get();

        HexagonActor hexActor1;
        HexagonActor hexActor2;

        while (currentHex.getGridX() < 8 && sameColor == true) {
            hexActor1 = new HexagonActor(currentHex);
            ccCurrentHex2 = CubeCoordinate.fromCoordinates(currentHex.getGridX() + 1, currentHex.getGridZ());
            currentHex2 = (Hexagon) hexGrid.getByCubeCoordinate(ccCurrentHex2).get();

            hexActor2 = new HexagonActor(currentHex2);
            if (hexActor1.getHexColor() == hexActor2.getHexColor()) {
                result += 1;
                currentHex = currentHex2;
            } else {
                sameColor = false;
            }
        }

        return result;
    }

    public static int CalculateScoreHexToTopLeft(HexagonalGrid hexGrid, Hexagon hex) {
        int result = 0;

        boolean sameColor = true;

        Hexagon currentHex;
        Hexagon currentHex2;
        CubeCoordinate ccCurrentHex = CubeCoordinate.fromCoordinates(hex.getGridX(), hex.getGridZ());
        CubeCoordinate ccCurrentHex2;

        currentHex = (Hexagon) hexGrid.getByCubeCoordinate(ccCurrentHex).get();

        HexagonActor hexActor1;
        HexagonActor hexActor2;

        while (currentHex.getGridX() > 0 && currentHex.getGridZ() < 11 && sameColor == true) {
            hexActor1 = new HexagonActor(currentHex);
            ccCurrentHex2 = CubeCoordinate.fromCoordinates(currentHex.getGridX() - 1, currentHex.getGridZ() + 1);
            currentHex2 = (Hexagon) hexGrid.getByCubeCoordinate(ccCurrentHex2).get();
            hexActor2 = new HexagonActor(currentHex2);
            if (hexActor1.getHexColor() == hexActor2.getHexColor()) {
                result += 1;
                currentHex = currentHex2;
            } else {
                sameColor = false;
            }
        }
        return result;
    }

    public static int CalculateScoreHexToTopRight(HexagonalGrid hexGrid, Hexagon hex) {
        int result = 0;

        boolean sameColor = true;

        Hexagon currentHex;
        Hexagon currentHex2;
        CubeCoordinate ccCurrentHex = CubeCoordinate.fromCoordinates(hex.getGridX(), hex.getGridZ());
        CubeCoordinate ccCurrentHex2;

        currentHex = (Hexagon) hexGrid.getByCubeCoordinate(ccCurrentHex).get();

        HexagonActor hexActor1;
        HexagonActor hexActor2;

        while (sameColor == true) {
            hexActor1 = new HexagonActor(currentHex);
            ccCurrentHex2 = CubeCoordinate.fromCoordinates(currentHex.getGridX(), currentHex.getGridZ() + 1);
            currentHex2 = (Hexagon) hexGrid.getByCubeCoordinate(ccCurrentHex2).get();
            hexActor2 = new HexagonActor(currentHex2);
            if (hexActor1.getHexColor() == hexActor2.getHexColor()) {
                result += 1;
                currentHex = currentHex2;
            } else {
                sameColor = false;
            }
        }
        return result;
    }

    public static int CalculateScoreHexToBotLeft(HexagonalGrid hexGrid, Hexagon hex) {
        int result = 0;

        boolean sameColor = true;

        Hexagon currentHex;
        Hexagon currentHex2;
        CubeCoordinate ccCurrentHex = CubeCoordinate.fromCoordinates(hex.getGridX(), hex.getGridZ());
        CubeCoordinate ccCurrentHex2;

        currentHex = (Hexagon) hexGrid.getByCubeCoordinate(ccCurrentHex).get();

        HexagonActor hexActor1;
        HexagonActor hexActor2;


        while (sameColor == true) {
            hexActor1 = new HexagonActor(currentHex);
            ccCurrentHex2 = CubeCoordinate.fromCoordinates(currentHex.getGridX(), currentHex.getGridZ() - 1);
            currentHex2 = (Hexagon) hexGrid.getByCubeCoordinate(ccCurrentHex2).get();

            hexActor2 = new HexagonActor(currentHex2);
            if (hexActor1.getHexColor() == hexActor2.getHexColor()) {
                result += 1;
                currentHex = currentHex2;
            } else {
                sameColor = false;
            }
        }
        return result;
    }

    public static int CalculateScoreHexToBotRight(HexagonalGrid hexGrid, Hexagon hex) {
        int result = 0;

        boolean sameColor = true;

        Hexagon currentHex;
        Hexagon currentHex2;
        CubeCoordinate ccCurrentHex = CubeCoordinate.fromCoordinates(hex.getGridX(), hex.getGridZ());
        CubeCoordinate ccCurrentHex2;

        currentHex = (Hexagon) hexGrid.getByCubeCoordinate(ccCurrentHex).get();

        HexagonActor hexActor1;
        HexagonActor hexActor2;

        while (sameColor == true) {
            hexActor1 = new HexagonActor(currentHex);
            ccCurrentHex2 = CubeCoordinate.fromCoordinates(currentHex.getGridX() + 1, currentHex.getGridZ() - 1);
            currentHex2 = (Hexagon) hexGrid.getByCubeCoordinate(ccCurrentHex2).get();
            hexActor2 = new HexagonActor(currentHex2);
            if (hexActor1.getHexColor() == hexActor2.getHexColor()) {
                result += 1;
                currentHex = currentHex2;
            } else {
                sameColor = false;
            }
        }
        return result;
    }
}






