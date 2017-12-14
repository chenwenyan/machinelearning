package AcoCran;

/**
 * Ant
 * Author： wychen
 * Date: 2017/12/14
 * Time: 19:38
 */
public class Ant {

    private Integer taskToNEC; //选择近端云
    private Integer taskToFEC; //选择远端云

    private float curData;//传输数据
    private float curCycle;//计算周期

    public Integer getTaskToNEC() {
        return taskToNEC;
    }

    public void setTaskToNEC(Integer taskToNEC) {
        this.taskToNEC = taskToNEC;
    }

    public Integer getTaskToFEC() {
        return taskToFEC;
    }

    public void setTaskToFEC(Integer taskToFEC) {
        this.taskToFEC = taskToFEC;
    }

    public float getCurData() {
        return curData;
    }

    public void setCurData(float curData) {
        this.curData = curData;
    }

    public float getCurCycle() {
        return curCycle;
    }

    public void setCurCycle(float curCycle) {
        this.curCycle = curCycle;
    }

    public Ant(Integer taskToFEC, Integer taskToNEC, float curData,float curCycle){
        this.taskToFEC = taskToFEC;
        this.taskToNEC = taskToNEC;
        this.curData = curData;
        this.curCycle = curCycle;
    }
}
