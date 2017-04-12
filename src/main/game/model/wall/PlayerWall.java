package game.model.wall;


import game.model.PlayerId;

public class PlayerWall extends Wall {
    private PlayerId playerId;
    private int strength;
    @Override
    public boolean isNeutral() {
        return false;
    }
    public boolean canCross(){
        return true;
    }
    public boolean strengthen(){
        return true;
    }
    public boolean demolish(){
        return true;
    }
    public int getDemolishCost(){
      return 0;
    }
    public int getStrengthenCost(){
        return 0;
    }


}
