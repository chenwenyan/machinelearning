package AcoTsp;

import java.util.Random;

//蚂蚁
public class Ant 
{
	int[] passed;//已经经过的城市列表（禁忌表）
	float passedLength=0.0f;//经过的路径长度
	int curCity;//蚂蚁当前所在城市
	int curIndex;//蚂蚁当前所在城市下标
	
	//��ʼ����������
	void init()
	{
		initPassed();
		passedLength=0.0f;
		curIndex=0;
		initBeginCity();
	}
	
	//��ʼ�����ɱ�
	void initPassed()
	{
		passed=new int[Constant.CITY_NUM+1];
		for(int i=0;i<passed.length;i++)
			passed[i]=Integer.MIN_VALUE;
	}
	
	//��ʼ���������ڳ���
	void initBeginCity()
	{
		Random rand=new Random();
		int beginCity=rand.nextInt(Constant.CITY_NUM);
		reachNextCity(beginCity);
	}
	
	//������һ������
	void reachNextCity(int nextCity)
	{
		//�ۼ����ξ���
		passedLength += Constant.routes[curCity][nextCity].distance;
		
		//ǰ��
		curCity=nextCity;
		passed[curIndex++]=nextCity+1;
	}
	
	//�жϳ���nCity�Ƿ��ڽ��ɱ���
	boolean isPassedCity(int nCity)
	{
		for(int i=0;passed[i] != Integer.MIN_VALUE;i++)
		{
			if(passed[i] == nCity) //���ڵĳ���
				return true;
		}
		return false;
	}
}
