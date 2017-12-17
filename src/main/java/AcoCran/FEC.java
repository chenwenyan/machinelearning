package AcoCran;

/**
 * FEC
 * Author： wychen
 * Date: 2017/12/17
 * Time: 15:24
 */
public class FEC {

    private int id; //第i个NEC

    private float Fe;//最大计算资源限制

    private float R;//前传链路容量限制

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getFe() {
        return Fe;
    }

    public void setFe(float fe) {
        Fe = fe;
    }

    public float getR() {
        return R;
    }

    public void setR(float r) {
        R = r;
    }

    public FEC(int id, float Fe, float R){
        this.id = id;
        this.Fe = Fe;
        this.R = R;
    }

}
