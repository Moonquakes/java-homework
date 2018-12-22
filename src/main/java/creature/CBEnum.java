package creature;

import javafx.scene.image.Image;

public enum CBEnum {
    First("大娃",new Image("/images/老大.png"),new Image("/images/老大亡.png"),"红色",3),
    Second("二娃",new Image("/images/老二.png"),new Image("/images/老二亡.png"),"橙色",2),
    Third("三娃",new Image("/images/老三.png"),new Image("/images/老三亡.png"),"黄色",1),
    Fourth("四娃",new Image("/images/老四.png"),new Image("/images/老四亡.png"),"绿色",1),
    Fifth("五娃",new Image("/images/老五.png"),new Image("/images/老五亡.png"),"青色",1),
    Sixth("六娃",new Image("/images/老六.png"),new Image("/images/老六亡.png"),"蓝色",1),
    Seventh("七娃",new Image("/images/老七.png"),new Image("/images/老七亡.png"),"紫色",2);

    private String name;
    private String color;
    private Image image;
    private Image deadImage;
    private int attack;

    CBEnum(String name,Image image,Image deadImage, String color,int attack){
        this.name=name;
        this.image=image;
        this.deadImage=deadImage;
        this.color=color;
        this.attack=attack;
    }
    public String getName(){
        return name;
    }
    public String getColor(){
        return color;
    }
    public Image getImage(){
        return image;
    }
    public Image getDeadImage(){
        return deadImage;
    }
    public int getAttack() {
        return attack;
    }

}
