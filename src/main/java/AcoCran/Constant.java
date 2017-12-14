package AcoCran;

/**
 * Constant
 * Author： wychen
 * Date: 2017/12/14
 * Time: 20:42
 */
public class Constant {

    //时延限制
    static float T = 3;

    static float Fc = (float)Math.pow(10,10);

    //数据量
    static float D = (float) Utils.RandomUtil.getRandomDoubleNum( Math.pow(10,0),Math.pow(10,3));

    //
    static float Ft = (float) Utils.RandomUtil.getRandomDoubleNum( Math.pow(10,0),Math.pow(10,3));
    //FEC计算频率
    static float fc = (float) Utils.RandomUtil.getRandomDoubleNum( Math.pow(10,5),Math.pow(10,6));
    //NEC计算频率
    static float fe = (float) Utils.RandomUtil.getRandomDoubleNum( Math.pow(10,3),Math.pow(10,4));
    //前传网络传输速率
    static float rf = (float) Utils.RandomUtil.getRandomDoubleNum( Math.pow(10,3),Math.pow(10,4));
    //无线网络传输速率
    static float rw = (float) Utils.RandomUtil.getRandomDoubleNum( Math.pow(10,3),Math.pow(10,4));
    //
    static float Fe = (float) Utils.RandomUtil.getRandomDoubleNum( Math.pow(10,5),Math.pow(10,6));
    //C-RAN
    static float fee = 0;
    //前传容量
    static float R = (float) Utils.RandomUtil.getRandomDoubleNum( Math.pow(10,6),Math.pow(10,7));


}
