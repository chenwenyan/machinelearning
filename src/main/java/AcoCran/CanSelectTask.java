package AcoCran;

/**
 * CanSelectTask
 * Author： wychen
 * Date: 2017/12/15
 * Time: 8:39
 */
public class CanSelectTask {

    private int id;

    private int i; //第i个RRH
    private int j; //第ij个UE
    private float VAP;//信息素浓度+启发式信息
    private float p;//被选择概率

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

    public float getVAP() {
        return VAP;
    }

    public void setVAP(float VAP) {
        this.VAP = VAP;
    }

    public float getP() {
        return p;
    }

    public void setP(float p) {
        this.p = p;
    }

    public CanSelectTask(int i, int j, float VAP, float p){
        this.i = i;
        this.j = j;
        this.VAP = VAP;
        this.p = p;
    }

}
