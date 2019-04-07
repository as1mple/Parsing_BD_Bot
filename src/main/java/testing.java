import java.io.IOException;

public class testing {
    public static void main(String[] args) throws IOException {

        MyTheard n = new MyTheard();
        n.start();

        System.out.println(MyTheard.currentThread().getName());


    }


}
