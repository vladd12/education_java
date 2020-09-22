package modules;

// Модуль 2/6

public class Module2 {
    public static void main(String[] args) {
        // Задача 1
        System.out.println(repeat("mice",5));
        System.out.println(repeat("hello",3));
        System.out.println(repeat("stop",1) + "\n");

        // Задача 2

    }

    // Задача 1
    public static String repeat(String str, int num) {
        String repStr = new String("");
        for (int i = 0; i < str.length(); i++) {
            int n = num;
            while(n > 0) {
                repStr = repStr + str.charAt(i);
                n = n - 1;
            }
        }
        return repStr;
    }
}
