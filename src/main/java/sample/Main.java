package sample;

import creature.*;
import field.BattleField;
import field.Position;
import formation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main extends Application {
    static int row=10;
    static int column=20;
    static int singleRow=row;    //两个不同队伍各自的行和列的值
    static int singleColumn=column/2-1;
    static boolean exit=false;  //是否结束
    static CalabashBrother[] calabashBrothers=new CalabashBrother[7];
    public static BattleField battleField;
    public static Controller controller;
    static ArrayList<String> content=new ArrayList<>();
    static int index=0;
    static Demon[] demon;
    static BattleField leftBattleField;
    static BattleField rightBattleField;
    static sample.Coordinator coordinator;
    static int indexOfArray=5;

    @Override
    public void start(final Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("CalabashBrother");
        Scene scene=new Scene(root, 1350, 750);
        primaryStage.setScene(scene);
        controller = fxmlLoader.getController();
       // Scanner scanner=new Scanner(System.in);
        battleField=new BattleField(row,column);

        coordinator=new sample.Coordinator(); //初始化指挥员
        coordinator.RandomSort(calabashBrothers);
        coordinator.BubbleSort(calabashBrothers);  //对起初乱序的七个葫芦娃进行排序
        leftBattleField=coordinator.command(calabashBrothers,new SingleLineBattleArray(singleRow,singleColumn)); //得到葫芦娃排列完后的阵列

        //System.out.print("请输入蝎子精、小喽啰的总个数（若随机到鱼鳞、方円、偃月、锋矢阵，则总个数已定）：");
       // int numOfDemon=scanner.nextInt();
        final int numOfDemon=8;
        demon=new Demon[numOfDemon];
        demon[0]=new Scorpion();
        for(int i=1;i<numOfDemon;i++){
            demon[i]=new Minions();
        }
        rightBattleField=coordinator.command(demon,new FangYuanArray(singleRow,singleColumn));
        //rightBattleField=coordinator.command(demon,coordinator.RandomFormation(singleRow,singleColumn)); //随机选取一个阵列，得到妖精排列完后的阵列
        if(leftBattleField!=null && rightBattleField!=null) {  //如果两个阵列均符合总场地的大小，则合并并打印
            Embattle(battleField,leftBattleField,rightBattleField);
        }
        battleField.print(controller);
        //System.out.println(calabashBrothers[0].getPosition().getX()+" "+calabashBrothers[0].getPosition().getY());

        primaryStage.show();
        Button button1=controller.ArrayButton1;
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                indexOfArray=1;
                int numOfDemon=7;
                refresh(indexOfArray,numOfDemon);
            }
        });
        Button button2=controller.ArrayButton2;
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                indexOfArray=2;
                int numOfDemon=7;
                refresh(indexOfArray,numOfDemon);
            }
        });
        Button button3=controller.ArrayButton3;
        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                indexOfArray=3;
                int numOfDemon=8;
                refresh(indexOfArray,numOfDemon);
            }
        });
        Button button4=controller.ArrayButton4;
        button4.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                indexOfArray=4;
                int numOfDemon=12;
                refresh(indexOfArray,numOfDemon);
            }
        });
        Button button5=controller.ArrayButton5;
        button5.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                indexOfArray=5;
                int numOfDemon=8;
                refresh(indexOfArray,numOfDemon);
            }
        });
        Button button6=controller.ArrayButton6;
        button6.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                indexOfArray=6;
                int numOfDemon=18;
                refresh(indexOfArray,numOfDemon);
            }
        });
        Button button7=controller.ArrayButton7;
        button7.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                indexOfArray=7;
                int numOfDemon=9;
                refresh(indexOfArray,numOfDemon);
            }
        });

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.SPACE){
                    battleField.setIndexOfArray(indexOfArray);
                    for(int i=0;i<calabashBrothers.length;i++){
                        ThreadCB threadCB=new ThreadCB(calabashBrothers[i]);
                        threadCB.start();
                    }
                    for(int i=0;i<demon.length;i++){
                        ThreadDemon threadDemon=new ThreadDemon(demon[i]);
                        threadDemon.start();
                    }
                }else if(event.getCode() == KeyCode.L){
                    FileChooser fileChooser = new FileChooser();
                    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                    fileChooser.getExtensionFilters().add(extFilter);
                    File file = fileChooser.showOpenDialog(primaryStage);
                    try {
                        FileReader fileReader = new FileReader(file);
                        BufferedReader reader = new BufferedReader(fileReader);
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            content.add(line);
                        }
                        reader.close();
                        if (content.size() > 0 && index < content.size()) {
                            int num=Integer.parseInt(content.get(index));
                            index++;
                            int numOfDemon=7;
                            switch (num){
                                case 1:numOfDemon=7;break;
                                case 2:numOfDemon=7;break;
                                case 3:numOfDemon=8;break;
                                case 4:numOfDemon=12;break;
                                case 5:numOfDemon=8;break;
                                case 6:numOfDemon=18;break;
                                case 7:numOfDemon=9;break;
                            }
                            refresh(num,numOfDemon);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }else if(event.getCode() == KeyCode.N) {
                    if (content.size() > 0 && index < content.size()) {
                        String[] all = content.get(index).split(" ");
                        if (all[0].equals("move")) {
                            Position oldPosition = new Position(Integer.parseInt(all[1].split(",")[0]), Integer.parseInt(all[1].split(",")[1]));
                            Position newPosition = new Position(Integer.parseInt(all[2].split(",")[0]), Integer.parseInt(all[2].split(",")[1]));
                            battleField.move(controller, oldPosition, newPosition);
                        }else if(all[0].equals("win") || all[0].equals("lose")) {
                            Position position = new Position(Integer.parseInt(all[1].split(",")[0]), Integer.parseInt(all[1].split(",")[1]));
                            battleField.dead(controller,position);
                        }else if(all[0].equals("clean")){
                            Position oldPosition = new Position(Integer.parseInt(all[1].split(",")[0]), Integer.parseInt(all[1].split(",")[1]));
                            Position newPosition = new Position(Integer.parseInt(all[2].split(",")[0]), Integer.parseInt(all[2].split(",")[1]));
                            battleField.clean(controller, oldPosition, newPosition);
                        }else if(content.get(index).equals("Demons win!")){
                            System.out.println("Demons win!");
                        }else if(content.get(index).equals("CalabashBrothers win!")){
                            System.out.println("CalabashBrothers win!");
                        }
                        index++;
                    }
                }else if(event.getCode() == KeyCode.R){
                    exit=true;
                    battleField.clean(controller);
                    battleField=new BattleField(row,column);
                    coordinator=new sample.Coordinator(); //初始化指挥员
                    coordinator.RandomSort(calabashBrothers);
                    coordinator.BubbleSort(calabashBrothers);  //对起初乱序的七个葫芦娃进行排序
                    leftBattleField=coordinator.command(calabashBrothers,new SingleLineBattleArray(singleRow,singleColumn)); //得到葫芦娃排列完后的阵列
                    final int numOfDemon=8;
                    demon=new Demon[numOfDemon];
                    demon[0]=new Scorpion();
                    for(int i=1;i<numOfDemon;i++){
                        demon[i]=new Minions();
                    }
                    rightBattleField=coordinator.command(demon,new FangYuanArray(singleRow,singleColumn));
                    Embattle(battleField,leftBattleField,rightBattleField);
                    battleField.print(controller);
                    exit=false;
                }
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void Embattle(BattleField field, BattleField leftBattleField, BattleField rightBattleField) {  //合并两个阵列
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < singleColumn; j++) {
                field.battlefield[i][j].setCreature(leftBattleField.battlefield[i][j].getCreature());
                field.battlefield[i][20 - singleColumn + j].setCreature(rightBattleField.battlefield[i][j].getCreature());
            }
        }
        Grandpa grandpa = new Grandpa();
        Snake snake = new Snake();
        field.battlefield[0][singleColumn-1].setCreature(grandpa);
        field.battlefield[0][(20 - 1) / 2 + 2].setCreature(snake);
    }

    public void refresh(int index,int number){
        battleField.setLivedDemon(number);
        battleField.clean(controller);
        battleField=new BattleField(row,column);
        demon=new Demon[number];
        demon[0]=new Scorpion();
        for(int i=1;i<number;i++){
            demon[i]=new Minions();
        }
        Formation formation=null;
        switch (index){
            case 1:formation=new CraneWingArray(singleRow,singleColumn);break;
            case 2:formation=new EnEchelonArray(singleRow,singleColumn);break;
            case 3:formation=new BluntYokeArray(singleRow,singleColumn);break;
            case 4:formation=new FishScaleArray(singleRow,singleColumn);break;
            case 5:formation=new FangYuanArray(singleRow,singleColumn);break;
            case 6:formation=new CrescentMoonArray(singleRow,singleColumn);break;
            case 7:formation=new FengVectorArray(singleRow,singleColumn);break;
        }
        rightBattleField=coordinator.command(demon,formation);
        Embattle(battleField,leftBattleField,rightBattleField);
        battleField.print(controller);
    }

    public class ThreadCB extends Thread {
        private Thread t;
        private CalabashBrother calabashBrother;

        ThreadCB(CalabashBrother calabashBrother) {
            this.calabashBrother=calabashBrother;
        }

        public void run() {
            while (!exit) {
                Position position = calabashBrother.getPosition();
                Position newPosition=new Position(0,0);
                if(position.getY()==9 && position.getX()<=4){
                    newPosition = new Position(position.getX()+1, position.getY());
                }else if(position.getY()==9 && position.getX()>4){
                    newPosition = new Position(position.getX()-1, position.getY());
                }else if(position.getY()<9){
                    newPosition = new Position(position.getX(), position.getY() + 1);
                }
                calabashBrother.setPosition(newPosition);
                int flag=battleField.moveCalabashBrother(controller, position, newPosition);
                if(flag==0){
                    calabashBrother.setPosition(position);
                }else if(flag==-1){
                    break;
                }else if(flag==2 || flag==3){
                    exit=true;
                }
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void start () {
            if (t == null) {
                t = new Thread (this);
                t.start ();
            }
        }
    }

    public class ThreadDemon extends Thread {
        private Thread t;
        private Demon demon;

        ThreadDemon(Demon demon) {
            this.demon=demon;
        }

        public void run() {
            while (!exit) {
                Position position = demon.getPosition();
                Position newPosition=new Position(0,0);
                if(position.getY()==10 && position.getX()<=4){
                    newPosition = new Position(position.getX()+1, position.getY());
                }else if(position.getY()==10 && position.getX()>4){
                    newPosition = new Position(position.getX()-1, position.getY());
                }else if(position.getY()>10) {
                    newPosition = new Position(position.getX(), position.getY() - 1);
                }
                demon.setPosition(newPosition);
                boolean flag=battleField.moveDemon(controller, position, newPosition);
                if(!flag){
                    demon.setPosition(position);
                }
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void start () {
            if (t == null) {
                t = new Thread (this);
                t.start ();
            }
        }
    }
}
