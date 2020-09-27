package modules;

// Модуль 4/6

public class Module4 {
    public static void main(String[] args) {

        // Задача 1
        System.out.println("Задача 1.");


    }

    // Задача 1
    public static String bessie(int n, int k, String str) {
        if ((n < 1 || n > 100) || (k < 1 || k > 80)) return "Error input params!"; // Некорректные входные данные для функции
        String result = "";
        int indexStart = 0, indexEnd = 0, k_save = k;
        while (n > 0) { // Пока количество слов не равно нулю
            for (int i = indexStart; i < str.length() - 1; i++) {
                if (i > 15) return "Error! A word containing more than 15 characters was found."; // Найдено слово, длина которого больше 15 символов
                if (str.charAt(i) == ' ') {
                    indexEnd = i;
                    break;
                }
            }
            String word = str.substring(indexStart, indexEnd);
            indexStart = indexEnd;
            if (k_save > word.length() + 1) {
                result = result + word + " ";
                k_save = k_save - (word.length() + 1);
            }
            else {
                k_save = k;

            }

            n = n - 1;
        }
        return result;
    }
}
