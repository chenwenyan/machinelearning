package org.cloudbus.cloudsim.workflowsimACO;

import java.util.Random;


public class ACO {
	Random rand = new Random();
	double ETC[][] = new double[Tool.TaskNum][Tool.VmNum];
	
	 // ����ʱ�����ETC[i][j]=workLoad[i]/computingPower[j]
	
	public void calETC()
	{
		for (int i = 0; i < Tool.TaskNum; i++) {
			for (int j = 0; j < Tool.VmNum; j++) {
				ETC[i][j] = Tool.taskList.get(i).getCloudletLength()/Tool.vmlist.get(j);
			}
		}
	}
	//����ÿֻ���ϵ�makespan
	public double calMakespan(int assignment[])
	{
		Tool.allot = assignment.clone();		
		double makespan = AcoplanningAlgorithmexample.calMakespan();
		return makespan;
	}
	//��ʼ����Ⱥ
	Ant[] ants = new Ant[Tool.AntNum];	
	public void initAnts()
	{
		for (int i = 0; i < Tool.AntNum; i++) {
			ants[i] = new Ant();			
			ants[i].assignment[0] = rand.nextInt(Tool.VmNum);
		}
	}
	
	//������Ⱥ
	public void resetAnts()
	{
		for (int i = 0; i < Tool.AntNum; i++) {
			for (int j = 0; j < Tool.TaskNum; j++) {
				ants[i].assignment[j] = 0;
			}
			ants[i].makespan = 0.0;
			ants[i].assignment[0] = rand.nextInt(Tool.VmNum);
		}
	}
	//��ʼ����Ϣ�ؾ���
	public double pheromone[][] = new double[Tool.TaskNum][Tool.VmNum];
	
	public void initPheromone()
	{
		for (int i = 0; i < Tool.TaskNum; i++) {
			for (int j = 0; j < Tool.VmNum; j++) {
				pheromone[i][j] = 0.01;
			}
		}
	}
	
	//ÿֻ�������һ������
	public void onTour()
	{
		//ÿֻ�����ƶ��Ĺ���
		for (int i = 0; i <Tool.AntNum; i++) {
			//ÿ������������з���ķ���
			Ant cAnt = ants[i];
			for (int j = 1; j < Tool.PointNum; j++) {
				cAnt.assignment[j] = selectNextRes(cAnt,j);
			}
			cAnt.makespan = calMakespan(cAnt.assignment);
		}
	}
	
	//������Ϣ��
	public void updatePheromone()
	{
		//��Ϣ�صĻӷ�
		for (int i = 0; i < Tool.TaskNum; i++) {
			for (int j = 0; j < Tool.VmNum; j++) {
				pheromone[i][j] *= (1.0-Tool.RHO);
			}
		}
		
		//��Ϣ�صĸ��£����Ž�ģ�
		Ant bAnt = findBest();
		for (int j = 0; j < Tool.VmNum; j++) {
			pheromone[j][bAnt.assignment[j]] += Tool.Q/bAnt.makespan;
		}
	}
	
	//ѡ����һ�����ʵĽڵ�
	public int selectNextRes(Ant ant,int PointIndex)
	{
		double denominator = 0.0;
		//�ȼ��㹫ʽ�ķ�ĸ����
		for (int i = 0; i < Tool.VmNum; i++) {
			denominator += Math.pow(pheromone[PointIndex][i], Tool.ALPHA)*
					Math.pow((Tool.Q/ETC[PointIndex][i]), Tool.BETA);
		}
		//������ڸ�������˵ÿ����Դ��ѡ��ĸ���
		double p[] = new double[Tool.VmNum];
		for (int i = 0; i < Tool.VmNum; i++) {
			p[i] = (Math.pow(pheromone[PointIndex][i], Tool.ALPHA)*
					Math.pow((Tool.Q/ETC[PointIndex][i]), Tool.BETA))/denominator;
		}
		//���̶�ѡ��һ����Դ
		int selectRes = -1;
		double sump = 0.0;
		double val = rand.nextDouble();
		for (int i = 0; i < Tool.VmNum; i++) {
			sump += p[i];
			if (val <= sump) {
				selectRes = i;
				break;
			}
		}
		
		return selectRes;
	}
	
	//Ѱ�����Ž�
	public Ant findBest()
	{
		double temp = ants[0].makespan;
		int loc = 0;
		for (int i = 0; i < Tool.AntNum; i++) {
			if (ants[i].makespan<temp) {
				temp = ants[i].makespan;
				loc = i;
			}
		}
		return ants[loc];
	}

	

}
