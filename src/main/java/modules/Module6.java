package modules;

public class Module6 {

    public static void main(String[] args) {

        // Задача 1
        System.out.println(bell(1));
        System.out.println(bell(2));
        System.out.println(bell(3));
        System.out.println(bell(4));
        System.out.println(bell(5));
        System.out.println(bell(6) + "\n");



    }

    // Задача 1
    public static int bell(int num) {

        // Подробнее про алгоритм тут: https://neerc.ifmo.ru/wiki/index.php?title=Числа_Белла
        // и тут: https://www.cyberforum.ru/combinatorics/thread955752.html

        int sum = 0;        // Переменная для результата

        // Вычисляем сумму чисел Стерлинга второго порядка
        for (int i = 0; i <= num; i++) {
            sum = sum + calcStirlingNumbers(num, i);
        }

        // Возвращаем результат
        return sum;
    }

    // Функция для вычисления чисел Стерлинга второго порядка
    public static int calcStirlingNumbers(int n, int k) {
        if (n >= 0 && n == k) return 1;
        if ((n > 0 && k == 0) || (k > 0 && n == 0)) return 0;

        return calcStirlingNumbers(n - 1, k - 1) + k * calcStirlingNumbers(n - 1, k);
    }

    // Задача 2







}
