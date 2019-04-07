import java.util.Scanner;

public class main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int num1 = scanner.nextInt(); // 1 number


        int blowing;
        int rezult = 0;
        do {
            System.out.println("Виберіть дію: (+)=1, (-)=2 , (*)=3, (/)=4 , (=)=5");

            blowing = scanner.nextInt(); // 

          /*  if (blowing > 5) {
                System.out.println("Помилка, введіть число від 1 до 5");
                System.exit(0);
            }
            if (blowing == 5) {
                break;
            }*/

            System.out.println("Введіть число");
            int num2 = scanner.nextInt();

            switch (blowing) {
                case 1:
                    rezult = Kalkulator.soum(num1, num2);
                    break;
                case 2:
                    rezult = Kalkulator.difference(num1, num2);
                    break;
                case 3:
                    rezult = Kalkulator.multiplication(num1, num2);
                    break;
                case 4:
                    rezult = Kalkulator.division(num1, num2);
                    break;


            }

        } while (blowing != 5);
        System.out.println(rezult);
    }

}
