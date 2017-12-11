package AcoTsp;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class AntAlgorithm 
{	
	private int NC=10;//��������
	private int antNum=10;//��������
	private Ant[] ants;//��Ⱥ
	private float Q=300.0f;
	private float p=0.3f;//������
	
	float minLength=Float.MAX_VALUE;//��ǰ��̾���
	int[] minRoute; //��ǰ���·��
	
	AntAlgorithm()
	{
		ants=new Ant[antNum];
		for(int i=0;i<antNum;i++)
			ants[i]=new Ant();
			
		minRoute=new int[Constant.CITY_NUM];
	}
	
	void run()
	{
		for(int nc=1;nc<=NC;nc++) //��������
		{
			//��ʼ����������
			for(int k=0;k<ants.length;k++)
				ants[k].init();
			
			//�������г���
			for(int look=1;look<Constant.CITY_NUM;look++)
			{
				for(int k=0;k<ants.length;k++)//ÿֻ����
				{
					int nextCity=select(ants[k]);//ѡ����һ������					
					ants[k].reachNextCity(nextCity);//������һ������
				}
			}
	
			//���������в���������·��
			for(int k=0;k<ants.length;k++)//ÿֻ����
			{
				ants[k].reachNextCity(ants[k].passed[0]-1);
				if(minLength > ants[k].passedLength)
				{
					minLength=ants[k].passedLength;//��¼��̾���
					copyRoute(ants[k].passed);//��¼���·��
				}
			}
			
			//��routes������Ϣ�ظ���
			for(int i=0;i<Constant.CITY_NUM;i++)
				for(int j=0;j<Constant.CITY_NUM;j++)
				{
					//����·������Ϣ�ؾ�����
					Constant.routes[i][j].pheromone *= p;
					
					for(int k=0;k<ants.length;k++)
					{
						for(int n=0;n<Constant.CITY_NUM;n++)
						{
							int curCity=ants[k].passed[n]-1;
							int nextCity=ants[k].passed[(n+1) % Constant.CITY_NUM]-1;
							
							if(curCity == i && nextCity == j)//���ֹ����·��
							{
								//����·��curCity,nextCity��Ϣ��
								float dp = Q/ants[k].passedLength;//��Ϣ������
								Constant.routes[i][j].pheromone += dp;	
							}
						}
					}
				}
			print();
//
//			for(int i=0;i<Constant.CITY_NUM;i++)
//			{
//				for(int j=0;j<Constant.CITY_NUM;j++)
//					System.out.print(Constant.routes[i][j].pheromone+" ");
//				System.out.println();
//			}
//			System.out.println("_____________________");
		}
		
		
	}
	
	//����ѡ�����+�ֶ�
	int select(Ant ant)
	{
		float totalVAP=0.0f;
		List<canReachCity> canSelectedCityList=new LinkedList<canReachCity>();
		for(int nextCity=0;nextCity<Constant.CITY_NUM;nextCity++)
		{
			if(!ant.isPassedCity(nextCity+1))//��ѡ�����
			{
				double visibility=1.0f/Constant.routes[ant.curCity][nextCity].distance;//�ܼ���
					visibility=Math.pow(visibility,3);
				double pheromone=Constant.routes[ant.curCity][nextCity].pheromone;
						pheromone=Math.pow(pheromone,2);
				
				float VAP=(float)visibility+(float)pheromone;
				totalVAP += VAP; //�ۼ�VAP
				//�������ͱ���**********************************************
				canReachCity rCity=new canReachCity(nextCity,VAP);

				canSelectedCityList.add(rCity);//���
			}
		}


		//����ÿ�����б�ѡ�еĸ���
		ListIterator<canReachCity> iterator = canSelectedCityList.listIterator(); // ��ȡpelList��Ӧ�ĵ�����ͷ���
		while (iterator.hasNext())
		{
			//ȡ����
			canReachCity rCity=iterator.next();
			
			//�������
			rCity.rate = rCity.VAP/totalVAP;
		}
		
//		iterator = canSelectedCityList.listIterator(); // ��ȡpelList��Ӧ�ĵ�����ͷ���
//		while (iterator.hasNext())
//		{
//			canReachCity rCity=iterator.next();
//			System.out.print(rCity.id+" ");
//		}
//		System.out.print("\n"+"____________________");
		
		//����ѡ������һ������
		float rate=(float)Math.random();
		iterator = canSelectedCityList.listIterator(); // ��ȡpelList��Ӧ�ĵ�����ͷ���
		while (iterator.hasNext())
		{
			canReachCity rCity=iterator.next();
			if(rate <= rCity.rate)
				return rCity.id;
			else
				rate=rate-rCity.rate;
		}
		
		//�������£���Ϊ�������һ������
		iterator = canSelectedCityList.listIterator(); // ��ȡpelList��Ӧ�ĵ�����ͷ���
		while (iterator.hasNext())
		{
			canReachCity rCity=iterator.next();
			if(iterator.hasNext() == false) //���һ��Ԫ����
				return rCity.id;
		}
		
		return Integer.MAX_VALUE;
	}
	
	//����·��
	void copyRoute(int[] route)
	{
		for(int i=0;i<minRoute.length;i++)
			minRoute[i]=route[i];
	}
	
	void printRoute()
	{
		System.out.print("���·�ߣ�");
		for(int i=0;i<minRoute.length;i++)
			System.out.print(minRoute[i]+"->");
		System.out.print(minRoute[0]+"\n"+"��̾��룺" + minLength);
	}
	
	void print()
	{
		System.out.println("��̾��룺" + minLength);
	}
}
