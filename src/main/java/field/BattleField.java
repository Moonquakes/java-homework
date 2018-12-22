package field;

import creature.CalabashBrother;
import creature.Creature;
import creature.Demon;
import sample.Controller;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class BattleField {
    private int row;
    private int column;
    private static int livedCalabashBrother=7;
    private static int livedDemon=8;
    private static int deadNumberOfCalabashBrother=0;
    private static int deadNumberOfDemon=0;
    static ArrayList<String> content=new ArrayList<>();
    public Cell<Creature>[][] battlefield;
    public BattleField(int row, int column){
        this.row=row;
        this.column=column;
        battlefield=new Cell[row][column];
        for(int i=0;i<row;i++){
            for (int j=0;j<column;j++){
                battlefield[i][j]=new Cell<Creature>(new Position(i,j));
            }
        }
    }

    public ArrayList<String> getContent() {
        return content;
    }
    public void setIndexOfArray(int indexOfArray){
        content=new ArrayList<>();
        content.add(indexOfArray+"");
    }
    public void setLivedDemon(int livedDemon){
        this.livedDemon=livedDemon;
    }

    public void print(Controller controller){
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                if(!(battlefield[i][j].getIfEmpty())){
                    controller.setCreature(i,j,battlefield[i][j].getCreature());
                };
            }
        }
    }

    public void clean(Controller controller){
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                controller.setNull(i,j);
                battlefield[i][j].setIfEmpty(true);
                battlefield[i][j].setCreature(null);
            }
        }
    }

    public synchronized int moveCalabashBrother(Controller controller,Position oldPosition,Position newPosition){
        String record="";
        if(oldPosition.getY()<=8){
            if(battlefield[newPosition.getX()][newPosition.getY()].getIfEmpty()) {  //能向前走
                Creature creature = battlefield[oldPosition.getX()][oldPosition.getY()].getCreature();
                controller.setNull(oldPosition.getX(), oldPosition.getY());
                controller.setCreature(newPosition.getX(), newPosition.getY(), creature);
                battlefield[newPosition.getX()][newPosition.getY()].setCreature(creature);
                battlefield[oldPosition.getX()][oldPosition.getY()].setCreature(null);
                record="move "+oldPosition.getX()+","+oldPosition.getY()+" "+newPosition.getX()+","+newPosition.getY();
                content.add(record);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 1;
            }else {
                return 0;
            }
        }else{   //到达边界
            if(!(battlefield[oldPosition.getX()][oldPosition.getY()+1].getIfEmpty())) {  //能攻击先攻击
                CalabashBrother calabashBrother = (CalabashBrother) (battlefield[oldPosition.getX()][oldPosition.getY()].getCreature());
                boolean result = calabashBrother.fight(battlefield[oldPosition.getX()][oldPosition.getY()+1].getCreature());
                if (result) {
                    Demon demon=(Demon)battlefield[oldPosition.getX()][oldPosition.getY()+1].getCreature();
                    demon.setIfAlive(false);
                    controller.setNull(oldPosition.getX(), oldPosition.getY()+1);
                    controller.setDeadCreature(oldPosition.getX(), oldPosition.getY()+1,demon);
                    battlefield[oldPosition.getX()][oldPosition.getY()+1].setCreature(null);
                    battlefield[oldPosition.getX()][oldPosition.getY()+1].setIfEmpty(true);
                    livedDemon--;
                    deadNumberOfDemon++;
                    int a=oldPosition.getY()+1;
                    record="win "+oldPosition.getX()+","+a;
                    content.add(record);
                    if (livedDemon == 0) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(deadNumberOfDemon<10) {
                            controller.setNull(oldPosition.getX(), oldPosition.getY() + 1);
                            controller.setDeadCreature(deadNumberOfDemon, 19, demon);
                            record = "clean " + oldPosition.getX() + "," + a + " " + deadNumberOfDemon + "," + "19";
                            content.add(record);
                        }else {
                            controller.setNull(oldPosition.getX(), oldPosition.getY() + 1);
                            controller.setDeadCreature(deadNumberOfDemon-9, 18, demon);
                            int b=deadNumberOfDemon-9;
                            record = "clean " + oldPosition.getX() + "," + a + " " + b + "," + "18";
                            content.add(record);
                        }
                        record="CalabashBrothers win!";
                        content.add(record);
                        System.out.println("CalabashBrothers win!");
                        File file=new File("demo.txt");
                        try {
                            if(!file.exists()){
                                file.createNewFile();
                            }
                            FileWriter writer = new FileWriter(file, false);   //覆盖原文件
                            for (int i = 0; i < content.size(); i++) {
                                writer.write(content.get(i) + "\n");
                            }
                            writer.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return 2;
                    }else {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(deadNumberOfDemon<10) {
                            controller.setNull(oldPosition.getX(), oldPosition.getY() + 1);
                            controller.setDeadCreature(deadNumberOfDemon, 19, demon);
                            int b = oldPosition.getY() + 1;
                            record = "clean " + oldPosition.getX() + "," + b + " " + deadNumberOfDemon + "," + "19";
                            content.add(record);
                        }else {
                            controller.setNull(oldPosition.getX(), oldPosition.getY() + 1);
                            controller.setDeadCreature(deadNumberOfDemon-9, 18, demon);
                            int b = oldPosition.getY() + 1;
                            int c = deadNumberOfDemon-9;
                            record = "clean " + oldPosition.getX() + "," + b + " " + c + "," + "18";
                            content.add(record);
                        }
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return 0;
                    }
                } else {
                    Creature creature=battlefield[oldPosition.getX()][oldPosition.getY()].getCreature();
                    controller.setNull(oldPosition.getX(), oldPosition.getY());
                    controller.setDeadCreature(oldPosition.getX(), oldPosition.getY(), creature);
                    battlefield[oldPosition.getX()][oldPosition.getY()].setCreature(null);
                    battlefield[oldPosition.getX()][oldPosition.getY()].setIfEmpty(true);
                    livedCalabashBrother--;
                    deadNumberOfCalabashBrother++;
                    record="lose "+oldPosition.getX()+","+oldPosition.getY();
                    content.add(record);
                    if (livedCalabashBrother == 0) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        record="clean "+oldPosition.getX()+","+oldPosition.getY()+" "+deadNumberOfCalabashBrother+","+"0";
                        content.add(record);
                        controller.setNull(oldPosition.getX(), oldPosition.getY());
                        controller.setDeadCreature(deadNumberOfCalabashBrother, 0, creature);
                        record="Demons win!";
                        content.add(record);
                        System.out.println("Demons win!");
                        File file=new File("demo.txt");
                        try {
                            if(!file.exists()){
                                file.createNewFile();
                            }
                            FileWriter writer = new FileWriter(file, false);   //覆盖原文件
                            for (int i = 0; i < content.size(); i++) {
                                writer.write(content.get(i) + "\n");
                            }
                            writer.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return 3;
                    }else {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        controller.setNull(oldPosition.getX(), oldPosition.getY());
                        controller.setDeadCreature(deadNumberOfCalabashBrother, 0, creature);
                        record="clean "+oldPosition.getX()+","+oldPosition.getY()+" "+deadNumberOfCalabashBrother+","+"0";
                        content.add(record);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return -1;
                    }
                }
            }else if(oldPosition.getX()!=4 && battlefield[newPosition.getX()][newPosition.getY()].getIfEmpty()) {  //能上下移动
                Creature creature = battlefield[oldPosition.getX()][oldPosition.getY()].getCreature();
                controller.setNull(oldPosition.getX(), oldPosition.getY());
                controller.setCreature(newPosition.getX(), newPosition.getY(), creature);
                battlefield[newPosition.getX()][newPosition.getY()].setCreature(creature);
                battlefield[oldPosition.getX()][oldPosition.getY()].setCreature(null);
                record="move "+oldPosition.getX()+","+oldPosition.getY()+" "+newPosition.getX()+","+newPosition.getY();
                content.add(record);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 1;
            }else {  //不能移动也不能攻击
                return 0;
            }
        }
    }

    public synchronized boolean moveDemon(Controller controller,Position oldPosition,Position newPosition) {
        String record="";
        if (oldPosition.getY() >= 11) {
            if (battlefield[newPosition.getX()][newPosition.getY()].getCreature() == null) {
                Creature creature = battlefield[oldPosition.getX()][oldPosition.getY()].getCreature();
                controller.setNull(oldPosition.getX(), oldPosition.getY());
                controller.setCreature(newPosition.getX(), newPosition.getY(), creature);
                battlefield[newPosition.getX()][newPosition.getY()].setCreature(creature);
                battlefield[oldPosition.getX()][oldPosition.getY()].setCreature(null);
                record="move "+oldPosition.getX()+","+oldPosition.getY()+" "+newPosition.getX()+","+newPosition.getY();
                content.add(record);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return true;
            }
            return false;
        }else {  //到达边界
            if(oldPosition.getX()!=4 && battlefield[newPosition.getX()][newPosition.getY()].getIfEmpty()){  //能上下移动
                Creature creature=battlefield[oldPosition.getX()][oldPosition.getY()].getCreature();
                if(creature!=null) {
                    controller.setNull(oldPosition.getX(), oldPosition.getY());
                    controller.setCreature(newPosition.getX(), newPosition.getY(), creature);
                    battlefield[newPosition.getX()][newPosition.getY()].setCreature(creature);
                    battlefield[oldPosition.getX()][oldPosition.getY()].setCreature(null);
                    record="move "+oldPosition.getX()+","+oldPosition.getY()+" "+newPosition.getX()+","+newPosition.getY();
                    content.add(record);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return true;
                }else {
                    return false;
                }
            }else {
                return false;
            }
        }
    }

    public synchronized void move(Controller controller,Position oldPosition,Position newPosition){
        Creature creature = battlefield[oldPosition.getX()][oldPosition.getY()].getCreature();
        controller.setNull(oldPosition.getX(), oldPosition.getY());
        controller.setCreature(newPosition.getX(), newPosition.getY(), creature);
        battlefield[newPosition.getX()][newPosition.getY()].setCreature(creature);
        battlefield[oldPosition.getX()][oldPosition.getY()].setCreature(null);
    }

    public synchronized void dead(Controller controller,Position position){
        Creature creature=battlefield[position.getX()][position.getY()].getCreature();
        controller.setNull(position.getX(), position.getY());
        controller.setDeadCreature(position.getX(), position.getY(),creature);
    }

    public synchronized void clean(Controller controller,Position oldPosition,Position newPosition){
        Creature creature=battlefield[oldPosition.getX()][oldPosition.getY()].getCreature();
        controller.setNull(oldPosition.getX(), oldPosition.getY());
        controller.setDeadCreature(newPosition.getX(), newPosition.getY(), creature);
        battlefield[oldPosition.getX()][oldPosition.getY()].setCreature(null);
        battlefield[oldPosition.getX()][oldPosition.getY()].setIfEmpty(true);
    }

}
