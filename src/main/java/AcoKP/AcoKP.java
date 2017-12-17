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

    private int NC = 200;//迭代次数
    private int antNum = 20;//蚂蚁数量（一般设置的与物品数目相等）
    private float Q = 1.0f; //用于控制信息素增量到适度范围
    private float rho = 0.1f;//蒸发系数

    private float alpha = 3;  //信息素重要程度
    private float beta = 2;   //启发式因子重要程度

    private float eta; //启发式因子

    private ArrayList<Ant> ants = new ArrayList<Ant>(); //蚁群


    //所有物品对象
    ArrayList<Res> resArrayList = new ArrayList<Res>();

    /**
     * 初始化蚁群
     *
     */
    public void intAnts(){
        ants.clear();
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
            Res res = new Res(Constant.thing[i][1],Constant.thing[i][0],Constant.pheromone);
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
                float vap = (float) (Math.pow(res.getPheromone(),alpha) * Math.pow(eta,beta));
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
//        iter = canSelect.listIterator();
//        while(iter.hasNext()){
//            CanSelectRes canSelect1 = iter.next();
//            if(iter.hasNext() == false){
//                return canSelect1.getId();
//            }
//        }
        return Integer.MAX_VALUE;
    }


    public void antSelect() {

        float maxValue = 0;//最大价值
        float totalWeight = 0;//背包所装物品重量之和
        List<Integer> resList = null; 
        ArrayList<Ant> ncAnts = new ArrayList<Ant>();//每次迭代的最优解

        for (int k = 0; k < NC; k++) {
            intAnts();
            for (int m = 0; m < antNum; m++) {
                Ant curAnt = ants.get(m);
                for(int q = 0 ; q < Constant.num; q++){
                    Res res = resArrayList.get(getMaxP(curAnt,resArrayList));
                    if ((curAnt.getResWeight() + res.getWeight()) <= Constant.capacity) {
                        curAnt.setResValue(curAnt.getResValue() + res.getValue());
                        curAnt.setResWeight(curAnt.getResWeight() + res.getWeight());
                        curAnt.getSelected().add(res.getId());
                    }
                }
            }
            //找出本轮迭代最优的蚂蚁
            Ant nc_bestAnt = getBestAnt(ants);
            //每经过一轮迭代之后，蚂蚁选择的最优物品列表的信息素更新一次
            ArrayList betterList = nc_bestAnt.getSelected();
            if(betterList.size() > 0){
                for(Res res: resArrayList){
                    //选中后，信息素浓度增加
                    float delta = 0;
                    if(betterList.contains(res.getId())) {
                        delta = Q * res.getValue() / nc_bestAnt.getResValue(); //信息素增量
                    }
                    res.setPheromone(delta + res.getPheromone()*(1-rho));//所有物品信息素挥发一次
                    resArrayList.set(res.getId(),res);
                }
            }
            ncAnts.add(nc_bestAnt);
            //输出
            System.out.println("k:" + k + ", value：" + nc_bestAnt.getResValue());
        }
        for(Ant ant : ncAnts){
            if(maxValue < ant.getResValue()){
                maxValue = ant.getResValue();
                totalWeight = ant.getResWeight();
                resList = ant.getSelected();
            }
        }
        System.out.println("maxValue:" + maxValue);
        System.out.println("totalWeight:" + totalWeight);
        System.out.println("result:" + resList.toString());
    }

    public static void main(String[] args) {
        AcoKP acokp = new AcoKP();
        acokp.init();
        acokp.antSelect();
    }

}
