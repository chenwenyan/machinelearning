package AcoCran;


import java.util.ArrayList;
import java.util.ListIterator;

/**
 * AcoCran
 * Author： wychen
 * Date: 2017/12/14
 * Time: 19:45
 */
public class AcoCran {

    private int NC = 5;//迭代次数
    private int antNum = 10;//蚂蚁数量
    private float Q = 20.0f; //用于控制信息素增量到适度范围
    private float rho = 0.1f;//蒸发系数

    private float alpha = 3;  //信息素重要程度
    private float beta = 2;   //启发式因子重要程度

    private float eta; //启发式因子

    private float pheromone = 10.0f;

    private ArrayList<Ant> ants = new ArrayList<Ant>(); //蚁群

    private Integer M = 5; //RRHs
    private Integer N = 50; //UEs

    private ArrayList<Task> tasks = new ArrayList<Task>();//计算任务

    /**
     * 初始化计算任务
     */
    public void initTask() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                Task item = new Task(i, j, 0.0f, Constant.D, Constant.Ft, pheromone);
                item.setId((i+1)*(j+1) - 1);
                tasks.add(item);
            }
        }
    }

    /**
     * 初始化蚁群
     */
    public void initAnt() {
        ants.clear();//清空原有蚁群
        for (int i = 0; i < antNum; i++) {
            Ant ant = new Ant(null,null,0,0,0,0,0);
            ants.add(ant);
        }
    }

    /**
     * 赌轮法选择计算任务
     *
     * @param curAnt
     * @param tasks
     * @return
     */
    public int getMaxP(Ant curAnt, ArrayList<Task> tasks) {
        ArrayList<CanSelectTask> canSelectTasks = new ArrayList<CanSelectTask>();
        float totalVAP = 0.0f;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if(!curAnt.isSelectedTask(task)){
                //FEC
                float fc = Constant.fc;
                float time = task.getData()/Constant.rw + task.getData()/Constant.rf + task.getCycle()/fc;
                eta = fc*time;

                float VAP = (float) (Math.pow(task.getPheromone(), alpha) + (Math.pow(eta, beta)));
                totalVAP += VAP;
                CanSelectTask canSelectTask = new CanSelectTask(task.getI(), task.getJ(), VAP, 0);
                canSelectTasks.add(canSelectTask);
            }
        }

        ListIterator<CanSelectTask> canSelectTaskIterable = canSelectTasks.listIterator();
        while (canSelectTaskIterable.hasNext()) {
            CanSelectTask task = canSelectTaskIterable.next();
            task.setP(task.getVAP() / totalVAP);
        }

        float rate = (float) Math.random();
        ListIterator<CanSelectTask> iter = canSelectTasks.listIterator();
        while (iter.hasNext()) {
            CanSelectTask canSelectTask = iter.next();
            if (rate <= canSelectTask.getP()) {
                return canSelectTask.getId();
            } else {
                rate = rate - canSelectTask.getP();
            }
        }

        ListIterator<CanSelectTask> taskListIterator = canSelectTasks.listIterator();
        while(!taskListIterator.hasNext()){
            return tasks.get(tasks.size()+1).getId();
        }
        return Integer.MAX_VALUE;
    }

    /**
     * 选择一代中的最优蚂蚁路径
     *
     * @param ants
     * @return
     */
    public Ant getBestAnt(ArrayList<Ant> ants){
        Ant bestAnt = ants.get(0);
        int maxSum = ants.get(0).getSumTask();
        for (Ant ant:ants) {
            if(ant.getSumTask() > maxSum){
                bestAnt = ant;
            }
        }
        return bestAnt;
    }

    /**
     * 蚂蚁选择计算任务并分配
     *
     */
    public void antSelectTask() {
        Integer maxTasks = 0;
        ArrayList<Ant> ncAnts = new ArrayList<Ant>();

        for (int i = 0; i < NC; i++) {
            initAnt();//初始化蚁群
            for (int j = 0; j < antNum; j++) {
                Ant ant = ants.get(j);
                ArrayList<Task> taskToFEC = new ArrayList<Task>();
                ArrayList<Task> taskToNEC = new ArrayList<Task>();
                for(int m = 0; m < M; m++){
                    ant.setCurNECF(0);
                    boolean isSelected = false;
                    for (int k = 0; k < tasks.size(); k++) {
                        Task thisTask = tasks.get(getMaxP(ant,tasks));
                        if(m == tasks.get(k).getI()){
                            if(ant.getCurFECF() + thisTask.getFt() < Constant.Fc || ant.getCurFECF() + thisTask.getFt() < Constant.Fe) {
                                if (ant.getCurNECF() + thisTask.getFt() < Constant.Fe) {//NEC
                                    float fe = Constant.fe;
                                    float time = thisTask.getData() / Constant.rw + thisTask.getCycle() / fe;
                                    if (time < Constant.T) {
                                      System.out.println(" task " + thisTask.getI() + "," + thisTask.getJ() + " assign to NEC");
                                        taskToNEC.add(thisTask);
                                        ant.setTaskToNEC(taskToNEC);
                                        ant.setCurNECF(ant.getCurNECF() + thisTask.getFt());
                                        ant.setCurNECCycle(ant.getCurNECCycle() + thisTask.getCycle());
                                        isSelected = true;
                                    }
                                } else if (!isSelected && ant.getCurFECF() + thisTask.getFt() < Constant.Fc) {
                                    float fc = Constant.fc;
                                    float time = thisTask.getData() / Constant.rw + thisTask.getData() / Constant.rf + thisTask.getCycle() / fc;
                                    if (time < Constant.T && (ant.getCurR() + fc) < Constant.R) {
                                      System.out.println(" task " + i + "," + j + " assign to FEC");
                                        taskToFEC.add(thisTask);
                                        ant.setTaskToFEC(taskToFEC);
                                        ant.setCurNECF(ant.getCurFECF() + thisTask.getFt());
                                        ant.setCurFECCycle(ant.getCurFECCycle() + thisTask.getCycle());
                                    }
                                }
                            }else {
                                System.out.println("there is no space tp assign task");
                            }
                            if (isSelected){
                                ant.setSumTask(ant.getSumTask() + 1);
                            }
                        }
                    }
                }
            }
            Ant nc_bestAnt = getBestAnt(ants);
            //更新信息素
            ArrayList<Task> selectedTasks = new ArrayList<Task>();
            if(nc_bestAnt.getTaskToFEC() != null){
                selectedTasks.addAll(nc_bestAnt.getTaskToFEC());
            }
            if(nc_bestAnt.getTaskToNEC() != null){
                selectedTasks.addAll(nc_bestAnt.getTaskToNEC());
            }
            float delta = 0.0f;
            for (int k = 1; k < tasks.size(); k++) {
                Task task = tasks.get(i);
                for (Task item : selectedTasks) {
                    if(task.getI() == item.getI() && task.getJ() == item.getJ()){
//                        delta = Q * task.getCycle() / (nc_bestAnt.getCurFECCycle() + nc_bestAnt.getCurNECCycle());//信息素增量
                        delta = Q ;
                    }
                    task.setPheromone(delta + task.getPheromone()*(1-rho));
                }
                tasks.set(i,task);//更新原task信息素
            }
            ncAnts.add(nc_bestAnt);
        }
        Ant bestAnt = getBestAnt(ncAnts);
        System.out.println(bestAnt.getSumTask());
    }


    public static void main(String[] args) {
      AcoCran acoCran = new AcoCran();
      acoCran.initTask();
      acoCran.antSelectTask();
    }

}
