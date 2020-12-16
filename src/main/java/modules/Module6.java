package modules;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

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
        System.out.println(translateSentence("I like to eat honey waffles."));
        System.out.println(translateSentence("Do you think it is going to rain today?") + "\n");

        // Задача 3
        System.out.println(validColor("rgb(0,0,0)"));
        System.out.println(validColor("rgb(0,,0)"));
        System.out.println(validColor("rgb(255,256,255)"));
        System.out.println(validColor("rgba(0,0,0,0.123456789)") + "\n");

        // Задача 4
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2"));
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2", new String[] {"b"}));
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&c=3", new String[] {"b", "c"}));
        System.out.println(stripUrlParams("https://edabit.com", new String[] {"b"}) + "\n");

        // Задача 5
        System.out.println(Arrays.toString(getHashTags("How the Avocado Became the Fruit of the Global Trade")));
        System.out.println(Arrays.toString(getHashTags("Why You Will Probably Pay More for Your Christmas Tree This Year")));
        System.out.println(Arrays.toString(getHashTags("Hey Parents, Surprise, Fruit Juice Is Not Fruit")));
        System.out.println(Arrays.toString(getHashTags("Visualizing Science")) + "\n");

        // Задача 6
        System.out.println(ulam(1));
        System.out.println(ulam(2));
        System.out.println(ulam(3));
        System.out.println(ulam(4));
        System.out.println(ulam(9));
        System.out.println(ulam(206));


    }

    // Задача 1
    // Число Белла - это количество способов, которыми массив из n элементов может быть разбит на непустые
    // подмножества. Создайте функцию, которая принимает число n и возвращает соответствующее число Белла.
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

    // Первая функция (для слов)
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
                    // Если последний символ не буква (знак препинания)
                    if (!Character.isAlphabetic(input.charAt(input.length() - 1))) {
                        String temp1 = input.substring(0, i);                  // Обрезаем слово до первой гласной
                        String temp2 = input.substring(i, input.length() - 1); // Вторая половина слова
                        char endSymbol = input.charAt(input.length() - 1);     // Символ в конце слова
                        result = temp2 + temp1 + "ay" + endSymbol;          // Формируем слово согласно заданию
                        break;
                    }
                    // В противном случае следуем по стандартному алгоритму
                    else {
                        String temp1 = input.substring(0, i);   // Обрезаем слово до первой гласной
                        String temp2 = input.substring(i);      // Вторая половина слова
                        result = temp2 + temp1 + "ay";          // Формируем слово согласно заданию
                        break;                                  // Выходим из цикла
                    }
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

    // Вторая функция (для предложений)
    public static String translateSentence(String input) {
        String[] arr = input.split(" ");        // Разделяем строку на слова через пробел
        String result = "";                           // Переменная для получения результатов

        // Для каждого слова из массива вызываем функцию translateWord
        for (int i = 0; i < arr.length; i++) {
            result = result + translateWord(arr[i]) + " ";
        }

        // Возвращаем результат
        return result;
    }

    // Задача 3
    // Учитывая параметры RGB(A) CSS, определите, является ли формат принимаемых значений допустимым
    // или нет. Создайте функцию, которая принимает строку (например, "rgb(0, 0, 0)") и возвращает
    // true, если ее формат правильный, в противном случае возвращает false.
    public static boolean validColor(String input) {

        // Переменная для хранения цвета
        String color = input.substring(0, input.indexOf('('));
        color = color.toLowerCase();    // в нижний регистр
        // Переменная для хранения данных о цвете
        String data = input.substring(input.indexOf('(') + 1, input.length() - 1);
        // Разбиваем данные на массив, содержащий числа
        String[] arrData = data.split(",");

        // Формируем действия в зависимости от полученной цветовой схемы
        if (color.equals("rgb")) {          // Если rgb
            // Если получено больше трёх значений для задания цвета rgb
            if (arrData.length != 3) return false;

            // Цикл: проверяем каждый элемент из массива arrData (всего их 3)
            for (String k: arrData) {
                int num;
                try {
                    num = Integer.parseInt(k);              // Конвертируем строку в число
                } catch (NumberFormatException ignored) {   // Если не получилось сконвертировать
                    return false;                           // возвращаем сразу false
                }
                // Если число не удовлятворяет заданному диапазону для значений RGB возвращаем false
                if (num < 0 || num > 255) return false;
            }
        }

        else if(color.equals("rgba")) {     // Если rgba
            // Если получено больше четырёх значений для задания цвета rgb
            if (arrData.length != 4) return false;

            // Цикл: проверяем каждый элемент из массива arrData (всего их 4)
            for (int i = 0; i < arrData.length - 1; i++) {
                int num;
                try {
                    num = Integer.parseInt(arrData[i]);     // Конвертируем строку в число
                } catch (NumberFormatException ignored) {   // Если не получилось сконвертировать
                    return false;                           // возвращаем сразу false
                }
                // Если число не удовлятворяет заданному диапазону для значений RGB, возвращаем false
                if (num < 0 || num > 255) return false;
            }

            // Проделываем аналогичные действия для последнего числа
            double num;
            try {
                num = Double.parseDouble(arrData[3]);   // Конвертируем строку в число с плавающей точкой
            } catch(NumberFormatException ignored) {    // Если не получилось сконвертировать
                return false;                           // возвращаем сразу false
            }
            // Если число не удовлятворяет заданному диапазону для значения прозрачности, RGBA возвращаем false
            if (num < 0 || num > 1) return false;
        }

        // Формируем исключение, если цветовая схема не совпала с одной из двух представленных
        else {
            throw new IllegalArgumentException("Получено некорректное значение данных!");
        }

        // Если дошли до этого пункты, то были проведены все проверки, возвращаем true
        return true;
    }

    // Задача 4
    // Создайте функцию, которая принимает URL (строку), удаляет дублирующиеся параметры запроса и
    // параметры, указанные во втором аргументе (который будет необязательным массивом).

    // Перегружаем функцию
    public static String stripUrlParams(String input) {
        return stripUrlParams(input, new String[] {""});
    }

    // Функция с необязательным параметром
    public static String stripUrlParams(String input, String[] paramsToStrip ) {

        // Если input не является ссылкой
        String https = input.substring(0, 8);
        if (!https.equals("https://")) return "Введена неверная ссылка!";

        // Объявление и инициализация переменных, массивов и списка
        String URL;      // Строка для разделения input на подстроку ссылки
        final String[] params = new String[1];  // Строка для разделения input на подстроку параметров
        String[] arrParams;     // Массив строк с параметрами
        int delimiterIndex = input.indexOf('?');        // Индекс разделителя ссылки и параметров
        if (delimiterIndex == -1) return input;         // Если не найден, возвращаем input
        else {
            URL = input.substring(0, delimiterIndex + 1);   // Задаём строку для ссылки из input
            params[0] = input.substring(delimiterIndex + 1);   // Задаём строку для параметров из input
            arrParams = params[0].split("&");            // Создаём массив строк-параметров из строки параметров
        }
        HashMap<String, String> List = new HashMap<>();     // Список для хранения данных

        // Формируем список формата <Имя параметра, значение параметра>
        for (int i = 0; i < arrParams.length; i++) {
            int index = arrParams[i].indexOf('=');          // Указываем индекс символа присвоения значения

            // Если параметр не принимает значение, то он удаляется
            if (index != -1) {
                String key = arrParams[i].substring(0, index);      // Ключ - имя параметра
                String value = arrParams[i].substring(index + 1);   // Значение - значение параметра

                // Если параметра нет в списке, то добавляем его в список
                if (!List.containsKey(key)) List.put(key, value);
                // Если параметр есть в списке
                else {
                    List.remove(key);           // Удаляем старое старое значение
                    List.put(key, value);       // Добавляем новое значение
                }
            }
        }

        // Если не было передано параметров или был передан параметр пустой строки
        // (соответствует вызову без необязательного параметра)
        // Переопределяем значение строки URL, она будет возвращаемой переменной
        // Обрезаем последний лишний символ
        if (paramsToStrip.length == 1 && paramsToStrip[0].equals("")) {
            params[0] = "";        // Обнуляем значение строки старых параметров
            // Цикл типа "для каждой пары ключ-значение"
            List.forEach((k,v)-> {
                // Формируем новую строку параметров
                params[0] = params[0] + k + "=" + v + "&";
            } );
        }

        // Необязательный параметр задан
        else {
            // Цикл для удаления заданных параметров из списка
            for (String str : paramsToStrip) {
                List.remove(str);
            }
            params[0] = "";        // Обнуляем значение строки старых параметров

            // Цикл типа "для каждой пары ключ-значение"
            List.forEach((k,v)-> {
                // Формируем новую строку параметров
                params[0] = params[0] + k + "=" + v + "&";
            } );
        }

        params[0] = params[0].substring(0, params[0].length() - 1);   // Обрезаем последний лишний символ
        URL = URL + params[0];   // Переопределяем значение строки URL, она будет возвращаемой переменной

        // Возвращаем результат
        return URL;
    }

    // Задача 5
    // Напишите функцию, которая извлекает три самых длинных слова из заголовка газеты и преобразует их в хэштеги.
    // Если несколько слов одинаковой длины, найдите слово, которое встречается первым.
    public static String[] getHashTags(String input) {
        String[] result;                              // Массив для результата
        input = input.toLowerCase();                  // Входную строку в нижний регистр
        String[] arrWords = input.split(" ");   // Массив слов из входной строки

        // Цикл для удаления из слов знаков препинания и прочих символов
        for (int i = 0; i < arrWords.length; i++) {      // Для каждого слова из массива
            String temp = "";              // Временная переменная
            // Цикл проходит по каждому символу слова
            for (int j = 0; j < arrWords[i].length(); j++) {
                // Если символ является буквой, то он добавляется к временной строке
                if (Character.isAlphabetic(arrWords[i].charAt(j)) && arrWords[i].charAt(j) != ',')
                    temp = temp + arrWords[i].charAt(j);
            }
            arrWords[i] = temp;
        }

        // Сортировка массива строк по длине каждой строки (сортировка вставками)
        for (int i = 1; i < arrWords.length; i++) {
            String temp = arrWords[i];
            // Вставляем s[j] в правильное положение
            int j = i - 1;
            while (j >= 0 && temp.length() < arrWords[j].length()) {
                arrWords[j + 1] = arrWords[j];
                j--;
            }
            arrWords[j + 1] = temp;
        }

        int n;                     // Переменная, указывающая размер результирующего массива
        // Если слов 3 и менее, то n равняется количество слов во входной строке
        if (arrWords.length < 4) n = arrWords.length;
        // Если слов больше, то n всегда равен трём
        else n = 3;                // Число выводимых слов
        result = new String[n];    // Инициализируем массив числом элементов n

        // Копируем все элементы из отсортированного массива с конца (в конце хранятся слова наибольшей длины)
        for (int i = 0; i < n; i++) {
            result[i] = "#" + arrWords[arrWords.length - (1 + i)];      // Добавляем к словам хештег слева
        }

        // Возвращаем результат
        return result;
    }

    // Задача 6
    // Создайте функцию, которая принимает число n и возвращает n-е число в последовательности Улама.
    public static int ulam(int num) {
        Vector<Integer> vec = new Vector<>(2);   // Инициализируем вектор
        int count = 0;                                      // Переменная-счётчик
        vec.add(1);                                         // Добавляем первый элемент
        vec.add(2);                                         // Добавляем второй элемент

        // Граничные условия: возвращаем первые элементы при надобности
        if (num == 1 || num == 2) return vec.get(num - 1);

        // Основной алгоритм
        int max = Integer.MAX_VALUE;                        // Максимально возможное число для int
        for (int i = 3; i < max; i++) {                     // Ищем число по индексу в числах до max
            for (int j = 0; j < vec.size() - 1; j++) {      // Через два цикла ищем совпадения при сложении
                for (int k = j+1; k < vec.size(); k++) {
                    if (vec.get(j)+vec.get(k)==i) count++;  // Если из прошлых чисел можно создать третье увелич. счётчик
                    if (count > 1) break;                   // Если вариантов создания больше одного
                }
                if (count > 1) break;                       // Если вариантов создания больше одного
            }
            if (count == 1) vec.add(i);                     // После завершения циклов добавляем число, если count == 1
            count = 0;                                      // Обнуляем переменную-счётчик
            if (vec.size() == num) return vec.get(num - 1); // Если размер вектора равен num, возвращаем послдений элемент
        }
        return 1;                                           // Возвращаем 1, если не смогли вернуть другое число
    }

    // Задача 7



}
