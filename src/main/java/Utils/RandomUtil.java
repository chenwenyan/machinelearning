package Utils;

import java.util.Random;

/**
 * Random
 * Author： wychen
 * Date: 2017/12/14
 * Time: 19:50
 */
public class RandomUtil {

    //生成min->max之间的数,最小生成的随机数为min，最大生成的随机数为max
    public static int getRandomIntNum(int min, int max){
        Random rdm = new Random();
        return rdm.nextInt(max-min+1)+min;
    }

    //生成min->max之间的数,最小生成的随机数为min，最大生成的随机数为max
    public static double getRandomFloatNum(float min,float max){
        return Math.round(Math.random()*(max-min))+min;
    }

    //生成min->max之间的数,最小生成的随机数为min，最大生成的随机数为max
    public static double getRandomDoubleNum(double min,double max){
        return Math.round(Math.random()*(max-min))+min;
    }
}
