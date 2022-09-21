import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
 
public class Main {
 
    private static ArrayList<MarkOfStudent> list = new ArrayList<>();
 
    public static void main(String[] args) {
 
        String fileStudents = "C:\\Users\\Zhulimov\\Desktop\\test.txt";    // Названия файлов
        String fileResult = "C:\\Users\\Zhulimov\\Desktop\\test.txt";
 
        try {       // Блок для проверки ошибок ввода
            Scanner in = new Scanner(System.in);   // Для ввода с консоли
            System.out.println("Введите количество студентов:");
            while (!in.hasNextInt()) { 
                System.out.println("Количество студентов должно быть целым числом. Повторите ввод!");
                in.next();
                }
            int count = in.nextInt();      // количество записей
            MarkOfStudent markOfStudent;        // переменная новой записи
            for (int i = 0; i < count; i++) {
                markOfStudent = new MarkOfStudent();    // создание новой записи
                in.nextLine();                     // Очищаем буфер ввода
                System.out.println("Введите фамилию студента");
                markOfStudent.secondName = in.nextLine();  // ввод фамилии
                System.out.println("Введите имя студента");
                markOfStudent.firstName = in.nextLine();
                System.out.println("Введите название предмета");
                //String subject = new subject.replaceAll("[a-zA-Z]");
                markOfStudent.subject = in.nextLine();
                System.out.println("Введите оценку");
                while (!in.hasNextInt()) { 
                    System.out.println("Оценка должна быть целым числом. Повторите ввод!");
                    in.next();
                    }
                markOfStudent.mark = in.nextInt();   
 
                
                list.add(markOfStudent);                    // заносим новую запись в список
            }
        }
        catch (Exception e) {       // обработка неправильного ввода
            System.out.println("Вы ввели неправильное значение. Программа завершена");
        }
 
        writeToFile(fileStudents);  // Записываем все введенное в файл
 
        String result = "Абсолютная успеваемость: "     // формируем строку с результатами
                + abs()
                + "\r\nКачество знаний: "
                + quality()
                + "\r\nСредний бал: "
                + average()
                + "\r\nОтличники:\r\n"
                + bestStudents();
        System.out.println(result);     // выводим результат анализа в консоль
        try(FileWriter writer = new FileWriter(fileResult, false)) {
            writer.write(result);       // Записываем резултат в файл
        }
        catch (Exception e)     // Обработка ошибки с файлом
        {
            System.out.println(e.getMessage());
        }
    }
 
 
    // Класс, содержащий одну запись
    public static class MarkOfStudent {
        String secondName;  // Фамилия
        String firstName;   // Имя
        String subject;     // Предмет
        int mark;           // оценка
    }
 
    private static void writeToFile(String fileName){   // Записывает список записей в файл
        try(FileWriter writer = new FileWriter(fileName, false))
        {
            for (MarkOfStudent markOfStudent : list) {      // Проходим по всему списку
                writer.write(markOfStudent.secondName   // Записываем в файл каждую запись
                        + " "
                        + markOfStudent.firstName
                        + " "
                        + markOfStudent.subject
                        + " "
                        + markOfStudent.mark
                        + "\r\n");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
 
 
    private static double abs() { //расчет абсолютной успеваемости
        int num = 0;
        for (MarkOfStudent markOfStudent : list) {  // Проходим по всему списку
            if ((markOfStudent.mark == 5) || (markOfStudent.mark == 4) || (markOfStudent.mark == 3))
                num++;      // Считаем количество положительных оценок
        }
        return (((double)(num) * 100)/ (list.size())); // Возвращаем процент положительных от общего числа
 
    }
 
    private static double average(){ //расчет среднего балла
        double sum = 0;
        for (MarkOfStudent markOfStudent : list) {  // Проходим по всему списку
            sum += markOfStudent.mark;  // Считаем сумму оценок
        }
 
        return (sum / list.size()); // Возвращаем среднее
    }
 
    private static double quality() { //расчет качества знаний
        double num = 0;
        for (MarkOfStudent markOfStudent : list) {  // Проходим по всему списку
            if ((markOfStudent.mark == 5) || (markOfStudent.mark == 4))
                num++;  // Считаем количество хороших и отличных оценок
        }
        return (num / list.size()) * 100;   // Возвращаем их процент от общего числа
    }
 
    private static String bestStudents() { //Список отличников
        Map<String, Boolean> map = new HashMap<>();     // Словарь для хранения студентов и их статуса(отличник-нет)
        for (MarkOfStudent markOfStudent : list) {
            String name = markOfStudent.secondName + " " + markOfStudent.firstName; // имя студента
            if (map.containsKey(name)){     // если уже встречался
                if (map.get(name)) {        // если пока отличник
                    map.put(name, markOfStudent.mark != 5); // заменяем статус: на не отличника, для оценки != 5
                }
            }
            else {      // если не встречался
                map.put(name, markOfStudent.mark == 5);// добавляем студента в словарь, даем статус отличника, если оценка == 5
            }
        }
        String result = ""; // строка результата
        for (String name : map.keySet()) {  // Проверям всех студентов
            if (map.get(name))              // Если он отличник
                result += name + "\r\n";      // Заносим его в строку
        }
        return  result;
    }
}
