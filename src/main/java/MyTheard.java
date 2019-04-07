public class MyTheard extends Thread {

    @Override
    public void run() {

        System.out.println("hello");
        System.out.println(Thread.currentThread().getName());

        //exep();

    }


    private void exep() {
        throw new RuntimeException();
    }
}
