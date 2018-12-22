package creature;

import javafx.scene.image.Image;

public class Grandpa extends Creature implements Cheer {
    public Grandpa(){
        super("老爷爷",new Image("/images/老爷爷.png"),new Image("/images/老爷爷.png"),0);
    }

    @Override
    public void cheer() {
        //nothing to do
    }
}
