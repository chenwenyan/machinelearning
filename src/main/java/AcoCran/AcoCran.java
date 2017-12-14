package AcoCran;

import AcoKP.Ant;

import java.util.ArrayList;

/**
 * AcoCran
 * Author： wychen
 * Date: 2017/12/14
 * Time: 19:45
 */
public class AcoCran {

    private int NC = 500;//迭代次数
    private int antNum = 10;//蚂蚁数量
    private float Q = 20.0f; //用于控制信息素增量到适度范围
    private float rho = 0.1f;//蒸发系数

    private float alpha = 3;  //信息素重要程度
    private float beta = 2;   //启发式因子重要程度

    private float eta; //启发式因子

    private ArrayList<Ant> ants = new ArrayList<Ant>(); //蚁群

    private Integer M = 5; //RRHs
    private Integer N = 50; //UEs

    /**
     * 初始化计算任务
     *
     */
    public void initTask() {
        Task task[][] = null;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                Task item = new Task(Constant.fc, Constant.fe);
                task[i][j] = item;
            }
        }
    }

    public void initAnt(){
         
    }


    public static void main(String[] args) {


    }


}
