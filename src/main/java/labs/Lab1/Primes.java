package labs.Lab1;

public class Primes {
    public static void main(String[] args) {
        for (int i = 2; i <= 100; i++) {
            if(isPrime(i)) System.out.println(i);
        }
    }

    /**
     * Функция, которая определяет, является ли входное число простым или нет
     * @param num входное число
     * @return возвращает true, если число является простым, или false, если не число не является таковым
     */
    public static boolean isPrime(int num) {
        for (int i = 2; i < num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
