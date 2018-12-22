package creature;

import javafx.scene.image.Image;

public class Demon extends Creature {
    private boolean ifAlive=true;
    public Demon(String name, Image image,Image deadImage,int attack){
        super(name,image,deadImage,attack);
    }

    public void setIfAlive(boolean ifAlive) {
        this.ifAlive = ifAlive;
    }

    public boolean getIfAlive() {
        return ifAlive;
    }
}
