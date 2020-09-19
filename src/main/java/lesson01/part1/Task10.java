package lesson01.part1;

// Класс времени
class time {
    private int hours;
    private int minutes;

    // Конструктор класса
    public time(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    // Метод получения секунд, на которые экземпляр класса отличается от другого экземпляра
    public int get_seconds(time t) {
        return Math.abs(((this.hours - t.hours)*60 + (this.minutes - t.minutes))*60);
    }
}

public class Task10 {

    /**
     * Не думать о секундах…
     * Напиши код, который считает сколько секунд прошло с 15:00, если на часах 15:30. Выведи результат на экран.
     *
     * Требования:
     * 1. Программа должна выводить текст.
     * 2. Выведенный текст должен быть целым положительным числом.
     * 3. Выведенное число должно быть кратно 60.
     * 4. Выводимое число должно соответствовать заданию.
     */

    public static void main(String[] args) {
        time t1 = new time(15, 0);
        time t2 = new time(15, 30);
        System.out.println(t1.get_seconds(t2)); // работает так
        System.out.println(t2.get_seconds(t1)); // и так (если поменять их местами)
        // так как возвращается абсолютное значение различия времени
    }
}
