package org.cloudbus.cloudsim.workflowsimACO;

import org.workflowsim.Task;

import java.util.ArrayList;
import java.util.List;


public class Tool {
	//��Դ����
	public static final int VmNum =10;
	//��������
	public static final int TaskNum = 25;
	//��������
	public static final int IterationNum = 200;
	//�ڵ�����
	public static final int PointNum=25;
	//��������
	public static final int AntNum= 10;	
	//alpha
	public static final double ALPHA = 1.0;
	//beta
	public static final double BETA = 2.0;
	//Q ��Ϣ����ʼֵ
	public static final double Q = 1.0;
	//rho
	public static final double RHO = 0.5;		
	// ͨ����xmlת���õ��������б�	
	public static List<Task> taskList = new ArrayList<Task>();	
	public static List<Task> getTaskList() {
		return taskList;
	}
	public static void setTaskList(List<Task> taskList) {
		Tool.taskList = taskList;
	}	
	//������б�
	public static List<Double> vmlist = new ArrayList<Double>();
	public static List<Double> getVmlist() {
		return vmlist;
	}
	public static void setVmlist(List<Double> vmlist) {
		Tool.vmlist = vmlist;
	}
	//��ǰ����������䷽��
	public static int[] allot= new int[Tool.TaskNum];
	
	

	

}
