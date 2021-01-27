package Utilities;

public class RandomId {

    public static String getId(){
        StringBuilder sB = new StringBuilder();
        for (int i = 0; i < 11; i++) {
            long num = (int)Math.round(Math.random()*9);
            sB.append(num);
        }
        return sB.toString();
    }

}
