package AcoTsp;

public class Route
{
	float distance;//距离
	float pheromone;//信息素

	Route()
	{
		distance=0.0f;
		pheromone=Constant.C;
	}
}
