package creature;

import field.Position;

public class CalabashBrother extends Creature implements Fight{
    private String color;
    private int rank;

    public CalabashBrother(int CBindex){
        super(CBEnum.values()[CBindex-1].getName(),CBEnum.values()[CBindex-1].getImage(),CBEnum.values()[CBindex-1].getDeadImage(),CBEnum.values()[CBindex-1].getAttack());
        this.color=CBEnum.values()[CBindex-1].getColor();
        this.rank=CBindex;
    }

    public CalabashBrother(String name,int rank){
        super(name,null,null,0);
        this.rank=rank;
    }

    public String getColor(){
        return this.color;
    }
    public int getRank(){
        return rank;
    }


    @Override
    public boolean fight(Creature creature) {
        int attact1=this.getAttack();
        int attact2=creature.getAttack();
        int all=attact1+attact2;
        double randomNumber=Math.random()*all;
        if(randomNumber<=attact1){
            return true;
        }else {
            return false;
        }
    }
}
