package AcoCran;

/**
 * Task
 * Author： wychen
 * Date: 2017/12/14
 * Time: 19:45
 */
public class Task {

    private int id;

    private int i;
    private int j;

    private float time;//时延
    private float cycle;//CPU计算周期
    private float data;//传输数据
    private float ft;//占用计算资源

    private float pheromone; //信息素浓度

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public float getCycle() {
        return cycle;
    }

    public void setCycle(float cycle) {
        this.cycle = cycle;
    }

    public float getData() {
        return data;
    }

    public void setData(float data) {
        this.data = data;
    }

    public float getPheromone() {
        return pheromone;
    }

    public void setPheromone(float pheromone) {
        this.pheromone = pheromone;
    }

    public float getFt() {
        return ft;
    }

    public void setFt(float ft) {
        this.ft = ft;
    }

    public Task(int i, int j, float cycle, float data, float ft, float pheromone){
        this.i = i;
        this.j = j;
        this.cycle = cycle;
        this.data = data;
        this.ft = ft;
        this.pheromone = pheromone;
    }

    public Task(){

    }

}
