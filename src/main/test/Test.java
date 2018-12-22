import creature.CalabashBrother;
import field.BattleField;
import javafx.fxml.FXMLLoader;
import sample.Controller;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;

public class Test {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
    Controller controller = fxmlLoader.getController();
    BattleField  battleField=new BattleField(10,20);
    CalabashBrother[] calabashBrothers=new CalabashBrother[7];
    sample.Coordinator coordinator=new sample.Coordinator();
    CalabashBrother calabashBrother1=new CalabashBrother("大娃",1);
    CalabashBrother calabashBrother2=new CalabashBrother("二娃",2);
    CalabashBrother calabashBrother3=new CalabashBrother("三娃",3);
    CalabashBrother calabashBrother4=new CalabashBrother("四娃",4);
    CalabashBrother calabashBrother5=new CalabashBrother("五娃",5);
    CalabashBrother calabashBrother6=new CalabashBrother("六娃",6);
    CalabashBrother calabashBrother7=new CalabashBrother("七娃",7);


    @org.junit.Test
    public void testBubbleSort1(){
        calabashBrothers[0]=calabashBrother3;
        calabashBrothers[1]=calabashBrother4;
        calabashBrothers[2]=calabashBrother6;
        calabashBrothers[3]=calabashBrother2;
        calabashBrothers[4]=calabashBrother1;
        calabashBrothers[5]=calabashBrother7;
        calabashBrothers[6]=calabashBrother5;
        coordinator.BubbleSort(calabashBrothers);
        int[] result=new int[]{1,2,3,4,5,6,7};
        int[] rank=new int[7];
        for(int i=0;i<7;i++){
            rank[i]=calabashBrothers[i].getRank();
        }
        assertEquals(Arrays.toString(result),Arrays.toString(rank));
    }

    @org.junit.Test
    public void testBubbleSort2(){
        calabashBrothers[0]=calabashBrother7;
        calabashBrothers[1]=calabashBrother6;
        calabashBrothers[2]=calabashBrother5;
        calabashBrothers[3]=calabashBrother4;
        calabashBrothers[4]=calabashBrother3;
        calabashBrothers[5]=calabashBrother2;
        calabashBrothers[6]=calabashBrother1;
        coordinator.BubbleSort(calabashBrothers);
        int[] result=new int[]{1,2,3,4,5,6,7};
        int[] rank=new int[7];
        for(int i=0;i<7;i++){
            rank[i]=calabashBrothers[i].getRank();
        }
        assertEquals(Arrays.toString(result),Arrays.toString(rank));
    }
}
