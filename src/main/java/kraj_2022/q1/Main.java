package kraj_2022.q1;

public class Main {
    
    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            String ans = "";
            if (i % 3 == 0) {
                ans += "bum";
            }
            if (i % 5 == 0) {
                ans += "bác";
            }
            if (i % 3 != 0 && i % 5 != 0) {
                ans = Integer.toString(i);
            }
            System.out.println(ans);
        }
    }

}
