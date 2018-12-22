package creature;

import field.Position;
import javafx.scene.image.Image;

public class Creature extends Being {
    private int attack;
    private Image deadImage;
    private Position position;

    public Creature(String name, Image image,Image deadImage,int attack){
        super(name,image);
        this.deadImage=deadImage;
        this.attack=attack;
    }
    public void setPosition(Position position){
        this.position=position;
    }
    public Position getPosition(){
        return this.position;
    }
    public int getAttack(){
        return this.attack;
    }
    public Image getDeadImage(){
        return this.deadImage;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
}
