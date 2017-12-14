package AcoKP;

import java.util.ArrayList;
import java.util.List;

/**
 * AntColony
 * Author： wychen
 * Date: 2017/12/13
 * Time: 10:14
 */
public class Ant {

    private ArrayList<Integer> selected = new ArrayList<Integer>();//已经选择的物体id
    private float resValue ; //已选择物体的价值
    private float resWeight; //已选择物品重量

    public ArrayList<Integer> getSelected() {
        return selected;
    }

    public void setSelected(ArrayList<Integer> selected) {
        this.selected = selected;
    }

    public float getResValue() {
        return resValue;
    }

    public void setResValue(float resValue) {
        this.resValue = resValue;
    }

    public float getResWeight() {
        return resWeight;
    }

    public void setResWeight(float resWeight) {
        this.resWeight = resWeight;
    }

    public Ant(float resValue,float resWeight){
        this.resWeight = resWeight;
        this.resValue = resValue;
    }

    public boolean isSelectedRes(int resIndex){
        for(int i = 0; i < selected.size(); i++){
            if(selected.get(i) == resIndex){
                return true;
            }
        }
        return false;
    }
}
