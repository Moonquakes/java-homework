package formation;

import creature.Creature;
import field.BattleField;
import field.Position;

public class CraneWingArray extends Formation {
    public CraneWingArray(int row, int column) {
        super(row,column);
    }
    public BattleField embattle(Creature[] creatures){
        int numOfPeople=creatures.length;
        int allRow=(numOfPeople+1)/2;
        int allColumn=numOfPeople;
        if(allRow>row || allColumn>column){
            System.out.println("所排人数超过了当前阵列的范围！");
            return null;
        }else {
            int vacancyRow = (row - allRow) / 2;
            int vacancyColumn = (column - allColumn) / 2;
            for (int i = 0; i < allRow; i++) {
                if(numOfPeople==1){
                    field.battlefield[vacancyRow + i][vacancyColumn + i].setCreature(creatures[numOfPeople-1]);
                    field.battlefield[vacancyRow + i][vacancyColumn + allColumn - 1 - i].setCreature(creatures[numOfPeople-1]);
                    creatures[numOfPeople-1].setPosition(new Position(vacancyRow+i,vacancyColumn+i+11));
                    creatures[numOfPeople-1].setPosition(new Position(vacancyRow+i,vacancyColumn+allColumn-1-i+11));
                }else {
                    field.battlefield[vacancyRow + i][vacancyColumn + i].setCreature(creatures[numOfPeople - 1]);
                    field.battlefield[vacancyRow + i][vacancyColumn + allColumn - 1 - i].setCreature(creatures[numOfPeople - 2]);
                    creatures[numOfPeople-1].setPosition(new Position(vacancyRow+i,vacancyColumn+i+11));
                    creatures[numOfPeople-2].setPosition(new Position(vacancyRow+i,vacancyColumn+allColumn-1-i+11));
                }
                numOfPeople = numOfPeople - 2;
            }
        }
        return field;
    }
}
