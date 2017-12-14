package AcoCran;

/**
 * Task
 * Author： wychen
 * Date: 2017/12/14
 * Time: 19:45
 */
public class Task {

    private float time;//时延
    private float cycle;//CPU计算周期
    private float data;//传输数据

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

    public Task(float cycle, float data){
        this.cycle = cycle;
        this.data = data;
    }

}
