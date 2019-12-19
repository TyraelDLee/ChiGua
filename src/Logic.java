import java.util.Random;

public class Logic {
    private int lastS = 0;
    private int S = 0;
    private int totalGet = 0;

    /**
     * 随机从0-999内抽取一个数x。 当base >= x < base+5 则为S
     * 自上一个S开始60抽后保底
     * */
    private void rollS(int base){
        totalGet++;
        Random roll = new Random();
        int charIndex = roll.nextInt(1000);
        if(lastS==59){
            S++;
            lastS = 0;
        }
        else{
            if(charIndex >= base && charIndex < base+5){
                S++;
                lastS = 0;
            }else
                lastS++;
        }
        //System.out.println(totalGet+" "+S+" "+lastS);
    }

    private int[] getStat(){
        return new int[]{totalGet, S, lastS};
    }

    public static void main(String[] args) {
        Logic logic = new Logic();
        for (int i = 0; i <= 10_000_000; i++) {
            Random doubleRandom = new Random();
            logic.rollS(doubleRandom.nextInt(995));
            //为保证数据足够分散采用双重随机。
        }
        double prob = (double)logic.getStat()[1]/(double)logic.getStat()[0];
        System.out.println("综合概率（S数量/一共抽取）："+prob);
        System.out.println("共抽取："+logic.getStat()[0]+"次 获得的S级构造体："+logic.getStat()[1]+" 距离上一个S级构造体"+logic.getStat()[2]);
    }
}
//TODO：做个GUI（无限咕咕咕）