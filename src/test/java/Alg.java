import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Alg {
    public static void main(String[] args) {


        String input = new Scanner(System.in).nextLine();
        String str1 = input;
        str1 = str1.replaceAll("[^-?0-9]+", " ");
        List<String> date = Arrays.asList(str1.trim().split(" "));


        int with = Integer.parseInt(date.get(0));
        int to = Integer.parseInt(date.get(1));
        List<Integer> result = new ArrayList<>();


        Integer tmp = with;
       int  count = 0;

        while (tmp<=to){
            int res = 1;

            String str = tmp.toString();
            int leng = str.length();
            for (int i = 0; i < leng; i++) {
                res  *= (int) Integer.parseInt(String.valueOf(str.charAt(i)));
            }

            if (res == tmp){
                result.add(tmp);

                count++;
            };

            tmp++;
        }

        System.out.println(count);






    }
}
