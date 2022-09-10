/*Задание 1
Напишите программу, записывающую 100 раз слово ”ТЕST” в файл. 
Слова должны чередоваться по формату – четная итерация большими буквами, 
нечетные – маленькими. Пример: TESTtestTESTtestTEST…
*/


public class Solution {
    public static void main(String[] args) {
        for (int i = 0; i < 101; i++){
            System.out.println("TESTtestTESTtestTEST");
        }
    }
}

        