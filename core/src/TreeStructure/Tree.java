package TreeStructure;

import com.game.GameState;

public class Tree {

    public Node root;

    public Tree(){}

    public void buildTree(GameState state){
        root = new Node(state);
    }

    public Node getRoot(){
        return root;
    }

}