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

        // Задача 2
        System.out.println(translateWord("flag"));
        System.out.println(translateWord("Apple"));
        System.out.println(translateWord("button"));
        System.out.println(translateWord(""));





    }

    // Задача 1
    // Число Белла - это количество способов, которыми массив из n элементов может быть разбит на непустые подмножества.
    // Создайте функцию, которая принимает число n и возвращает соответствующее число Белла.
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
    // В «поросячей латыни» (свинский латинский) есть два очень простых правила:
    // 1) Если слово начинается с согласного, переместите первую букву (буквы) слова до гласного до конца слова и
    // добавьте «ay» в конец.
    // 2) Если слово начинается с гласной, добавьте "yay" в конце слова.
    // Напишите две функции, чтобы сделать переводчик с английского на свинский латинский. Первая функция
    // translateWord(word) получает слово на английском и возвращает это слово, переведенное на латинский язык.
    // Вторая функция translateSentence(предложение) берет английское предложение и возвращает это предложение,
    // переведенное на латинский язык.
    public static String translateWord(String input) {
        // Если строка пустая, то вернём пустую строку
        if (input.equals("")) return "";

        // Проверяем, является первая буква заглавной, если да, то меняем флаг
        boolean flag = false;           // Переменная-флаг
        if (Character.isUpperCase(input.charAt(0))) {
            flag = true;
        }
        input = input.toLowerCase();    // Все буквы слова в нижний регистр

        // Формируем новое слово
        String vowelsList = "aeiouy";
        String result = "";
        // Если первая буква слова гласная
        if (vowelsList.contains(String.valueOf(input.charAt(0)))) {
            result = input + "yay";     // Формируем слово согласно заданию
        }
        // Если первая буква слова согласная
        else {
            // Цикл для перебора букв в слове
            for (int i = 0; i < input.length(); i++) {
                // Если встречена гласная буква
                if (vowelsList.contains(String.valueOf(input.charAt(i)))) {
                    String temp1 = input.substring(0, i);   // Обрезаем слово до первой гласной
                    String temp2 = input.substring(i);      // Вторая половина слова
                    result = temp2 + temp1 + "ay";          // Формируем слово согласно заданию
                    break;                                  // Выходим из цикла
                }
            }
        }

        // Если флаг в начале оказался true
        if (flag) {
            char first_symbol = Character.toUpperCase(result.charAt(0));    // Первая буква слова в верхний регистр
            result = first_symbol + result.substring(1);    // Добавляем оставшуюся часть слова
        }

        // Возвращаем результат
        return result;
    }

    public static String translateSentence(String input) {



        return "";
    }







}
