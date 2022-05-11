
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;


public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GregorianCalendar calendar = new GregorianCalendar();

        StepTracker stepTracker = new StepTracker(calendar);
        Converter converter = new Converter(75, 50);

        while (true) {
            printMenu();
            int command = scanner.nextInt();
            if (command == 1) {
                int inputMonth = setMonth(scanner);
                int inputDay = setDay(inputMonth, scanner, calendar);
                int inputStep = setSteps(scanner);
                stepTracker.setCountStepInDay(inputMonth, inputDay, inputStep);

            } else if (command == 2) {
                int inputMonth = setMonth(scanner);
                calendar.set(GregorianCalendar.MONTH, inputMonth);
                String nameMoth = calendar.getDisplayName(GregorianCalendar.MONTH, Calendar.LONG, Locale.getDefault());
                stepTracker.printStatistic(inputMonth, nameMoth, converter);
            } else if (command == 3) {
                int inputStep = setSteps(scanner);
                stepTracker.setPlanStepInDay(inputStep);
            } else if (command == 0) {
                System.out.println("Программа завершена");
                break;
            } else {
                System.out.println("Извините, такой команды пока нет.");
            }
        }

    }

    public static void printMenu() {
        System.out.println("Что вы хотите сделать? ");
        System.out.println("1 - Ввести количество шагов за определённый день");
        System.out.println("2 - Напечатать статистику за определённый месяц");
        System.out.println("3 - Изменить цель по количеству шагов в день");
        System.out.println("0 - Выход");
    }

    public static int setMonth(Scanner scanner) {
        System.out.println("Введите месяц от 1 до 12:");
        int inputMonth = scanner.nextInt();
        while (inputMonth < 0 || inputMonth > 12) {
            System.out.println("Введите месяц от 1 до 12:");
            inputMonth = scanner.nextInt();
        }
        return inputMonth - 1;

    }

    public static int setDay(int inputMonth, Scanner scanner, GregorianCalendar calendar) {
        calendar.set(Calendar.MONTH, inputMonth);
        int countDayInMonth = calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        System.out.println("Введите день от 1 до " + countDayInMonth + ":");
        int inputDay = scanner.nextInt();
        while (inputDay < 0 || inputDay > countDayInMonth) {
            System.out.println("Введите день от 1 до " + countDayInMonth + ":");
            inputDay = scanner.nextInt();
        }
        return inputDay - 1;
    }

    public static int setSteps(Scanner scanner) {
        System.out.println("Введите количество шагов:");
        int inputSteps = scanner.nextInt();
        while (inputSteps < 0) {
            System.out.println("Колчиество шагов не может быть меньше 0");
            System.out.println("Введите количество шагов:");
            inputSteps = scanner.nextInt();
        }

        return inputSteps;
    }

}



