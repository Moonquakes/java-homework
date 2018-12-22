package field;

public class Position {
    private int x;
    private int y;
    public Position(int x,int y){
        this.x=x;
        this.y=y;
    }

    @Deprecated
    public void up(){
        x=x-1;
    }
    @Deprecated
    public void down(){
        x=x+1;
    }
    @Deprecated
    public void left(){
        y=y-1;
    }
    @Deprecated
    public void right(){
        y=y+1;
    }


    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}
