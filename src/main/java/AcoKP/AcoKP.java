package AcoKP;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * AcoBP
 * Author： wychen
 * Date: 2017/12/13
 * Time: 9:08
 */
public class AcoKP {

    private int NC = 12;//迭代次数
    private int antNum = 10;//蚂蚁数量
    private float Q = 300.0f; //用于控制信息素增量到适度范围
    private float rho = 0.3f;//蒸发率

    private int alpha = 1;  //信息素重要程度
    private int beta = 2;   //启发式因子重要程度

    private float eta; //启发式因子

    private ArrayList<Ant> ants = new ArrayList<Ant>(); //蚁群


    //所有物品对象
    ArrayList<Res> resArrayList = new ArrayList<Res>();

    /**
     * 初始化蚁群
     *
     */
    public void intAnts(){
        for(int i = 0 ; i < antNum; i++){
            Ant ant = new Ant(0.0f,0.0f);
            ants.add(ant);
        }
    }

    /**
     * 找出选取物体价值最优的蚂蚁
     *
     */
    public Ant getBestAnt(ArrayList<Ant> ants){
        Ant bestAnt = ants.get(0);
        for(Ant ant : ants){
            if(ant.getResValue() > bestAnt.getResValue()){
                bestAnt = ant;
            }
        }
        return bestAnt;
    }

    /**
     * 初始化物品重量、价值、信息素浓度
     *
     */
    public void init(){
        for (int i = 0 ; i < Constant.num; i++) {
            Res res = new Res(Constant.thing[i][0],Constant.thing[i][1],Constant.pheromone);
            res.setId(i);
            resArrayList.add(res);
        }
    }

    /**
     * 赌轮法选择物品
     *
     * @param resList
     * @return
     */
    public int getMaxP(Ant curAnt, ArrayList<Res> resList){
        ArrayList<CanSelectRes> canSelect = new ArrayList<CanSelectRes>();//可选择物品列表
        float totalVAP = 0.0f;
        for(int i = 0 ; i < resList.size(); i++){
            Res res = resList.get(i);
            if(!curAnt.isSelectedRes(i)){
                eta = res.getValue()/res.getWeight(); //启发信息
                float vap = (float) (Math.pow(res.getPheromone(),alpha) + Math.pow(eta,beta));
                totalVAP += vap;
                CanSelectRes canSelectRes = new CanSelectRes(res.getId(),vap);
                canSelect.add(canSelectRes);
            }
        }

        //计算每一个物品被选中的概率
        ListIterator<CanSelectRes> iterator = canSelect.listIterator();
        while(iterator.hasNext()){
            CanSelectRes obj = iterator.next();
            obj.setP(obj.getVAP()/totalVAP);
        }

        //赌轮法获取选取物品对象
        float rate = (float)Math.random();
        ListIterator<CanSelectRes> iter = canSelect.listIterator();
        while(iter.hasNext()){
            CanSelectRes canRes = iter.next();
            if(rate <= canRes.getP()){
                return canRes.getId();
            } else {
                rate = rate - canRes.getP();
            }
        }

        //人为返回最后一个物品，精度导致
        iter = canSelect.listIterator();
        while(iter.hasNext()){
            CanSelectRes canSelect1 = iter.next();
            if(iter.hasNext() == false){
                return canSelect1.getId();
            }
        }

        return Integer.MAX_VALUE;
    }


    public void antSelect() {

        float maxValue = 0;//最大价值
        float totalWeight = 0;//背包索状物品重量之和
        List<Integer> resList = null; 
        ArrayList<Ant> ncAnts = new ArrayList<Ant>();//每次迭代的最优解

        for (int k = 0; k < NC; k++) {

            intAnts();
            for (int m = 0; m < antNum; m++) {
                Ant curAnt = ants.get(m);

                Res res = resArrayList.get(getMaxP(curAnt,resArrayList));
                if ((curAnt.getResWeight() + res.getWeight()) <= Constant.capacity) {

                    //选中后，信息素浓度增加
                    float delta = Q / curAnt.getResValue(); //信息素增量
                    res.setPheromone(delta + res.getPheromone());
                    resArrayList.set(res.getId(), res);//更新物品中的信息素浓度大小
                    curAnt.setResValue(curAnt.getResValue() + res.getValue());
                    curAnt.setResWeight(curAnt.getResWeight() + res.getWeight());
                    curAnt.getSelected().add(res.getId());
                }
            }
            //每经过一轮迭代之后，信息素挥发一次
            for (Res item : resArrayList) {
                item.setPheromone(item.getPheromone() * rho);
            }

            Ant nc_bestAnt = getBestAnt(ants);
            ncAnts.add(nc_bestAnt);
            //输出
            System.out.println("k: " + k);
            System.out.println("value：" + nc_bestAnt.getResValue());
            System.out.println("weight: " + nc_bestAnt.getResWeight());
            System.out.println("selected: " + nc_bestAnt.getSelected().toString());
        }
        for(Ant ant : ncAnts){
            if(maxValue < ant.getResValue()){
                maxValue = ant.getResValue();
                totalWeight = ant.getResWeight();
                resList = ant.getSelected();
            }
        }
        System.out.println("**********************************************");
        System.out.println("maxValue:" + maxValue);
        System.out.println("totalWeight:" + totalWeight);
        System.out.println("result:" + resList.toString());
    }

    public static void main(String[] args) {
        AcoKP acoBP = new AcoKP();
        acoBP.init();
        acoBP.antSelect();
    }

}
