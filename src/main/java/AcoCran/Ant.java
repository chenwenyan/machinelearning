package AcoCran;

import java.util.ArrayList;

/**
 * Ant
 * Author： wychen
 * Date: 2017/12/14
 * Time: 19:38
 */
public class Ant {

    private ArrayList<Task> taskToNEC; //选择近端云
    private ArrayList<Task> taskToFEC; //选择远端云

    //NEC
    private float curNECF;//计算资源
    private float curNECCycle;//计算周期

    //FEC
    private float curFECF;//计算资源
    private float curFECCycle;//计算周期
    private float curR;//前传链路容量

    public ArrayList<Task> getTaskToNEC() {
        return taskToNEC;
    }

    public void setTaskToNEC(ArrayList<Task> taskToNEC) {
        this.taskToNEC = taskToNEC;
    }

    public ArrayList<Task> getTaskToFEC() {
        return taskToFEC;
    }

    public void setTaskToFEC(ArrayList<Task> taskToFEC) {
        this.taskToFEC = taskToFEC;
    }

    public float getCurNECF() {
        return curNECF;
    }

    public void setCurNECF(float curNECF) {
        this.curNECF = curNECF;
    }

    public float getCurNECCycle() {
        return curNECCycle;
    }

    public void setCurNECCycle(float curNECCycle) {
        this.curNECCycle = curNECCycle;
    }

    public float getCurFECF() {
        return curFECF;
    }

    public void setCurFECF(float curFECF) {
        this.curFECF = curFECF;
    }

    public float getCurFECCycle() {
        return curFECCycle;
    }

    public void setCurFECCycle(float curFECCycle) {
        this.curFECCycle = curFECCycle;
    }

    public float getCurR() {
        return curR;
    }

    public void setCurR(float curR) {
        this.curR = curR;
    }

    public Ant(ArrayList<Task> taskToFEC, ArrayList<Task> taskToNEC,
               float curNECF, float curNECCycle, float curFECF,
               float curFECCycle, float curR){
        this.taskToFEC = taskToFEC;
        this.taskToNEC = taskToNEC;
        this.curNECF = curNECF;
        this.curNECCycle = curNECCycle;
        this.curFECF = curFECF;
        this.curFECCycle = curFECCycle;
        this.curR = curR;
    }

    /**
     * 判定计算任务是否已被选择
     *
     * @param task
     * @return
     */
    public boolean isSelectedTask(Task task){
        if(taskToNEC != null && taskToNEC.size() > 0 ){
            for(Task item : taskToNEC){
                if(task.getI() == item.getI() && task.getJ() == item.getJ()){
                    return true;
                }
            }
        }
        if (taskToFEC != null && taskToFEC.size() > 0) {
            for(Task obj : taskToFEC){
                if(task.getI() == obj.getI() && task.getJ() == obj.getJ()){
                    return true;
                }
            }
        }
        return false;
    }

}
