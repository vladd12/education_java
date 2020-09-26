package modules;

// Модуль 3/6

public class Module3 {
    public static void main(String[] args) {

        // Задача 1
        System.out.println("Задача 1.");
        System.out.println(solutions(1, 0, -1));
        System.out.println(solutions(1, 0, 0));
        System.out.println(solutions(1, 0, 1) + "\n");

        // Задача 2
        System.out.println("Задача 2.");
        System.out.println(findZip("all zip files are zipped"));
        System.out.println(findZip("all zip files are compressed") + "\n");

        // Задача 3
        System.out.println("Задача 3.");
        System.out.println(checkPerfect(6));
        System.out.println(checkPerfect(28));
        System.out.println(checkPerfect(496));
        System.out.println(checkPerfect(12));
        System.out.println(checkPerfect(97) + "\n");

        // Задача 4
        System.out.println("Задача 4.");
        System.out.println("");
        System.out.println(flipEndChars("ada"));
        System.out.println("");
        System.out.println(flipEndChars("z"));
    }

    // Задача 1
    public static int solutions(double a, double b, double c) {
        double D = b * b - 4 * a * c;
        if (D < 0) return 0;
        else if (D == 0) return 1;
        else return 2;
    }

    // Задача 2
    public static int findZip(String str) {
        int indexFirstZip = new StringBuilder(str).indexOf("zip");
        int indexSecondZip = new StringBuilder(str).indexOf("zip", indexFirstZip + 3);
        return indexSecondZip;
    }

    // Задача 3
    public static boolean checkPerfect(int num) {
        if (num == 1) return true;
        int sum = 0;
        for (int i = 1; i < num; i++) {
            if (num % i == 0) sum = sum + i;
        }
        return (sum == num);
    }

    // Задача 4
    public static String flipEndChars(String str) {
        if (str.length() < 2) return "Incompatible.";
        else {
            if (str.charAt(0) == str.charAt(str.length() - 1)) return "Two is a pair.";
            else {
                char first = str.charAt(0);
                return "";
            }
        }
    }
}
