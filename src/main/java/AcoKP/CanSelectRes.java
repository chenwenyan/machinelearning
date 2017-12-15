package AcoKP;

/**
 * CanSelectRes
 * Author： wychen
 * Date: 2017/12/13
 * Time: 16:11
 */
public class CanSelectRes {

    private int id;
    private float VAP; //信息素浓度+启发式信息之和
    private float p;  //被选中概率

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public CanSelectRes(int id, float VAP){
        this.id = id;
        this.VAP = VAP;
    }
}
