import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Parsing {


    private Document doc = connect();

    private String title = ("\n" + doc.title() + "\n"); // заголовок страницы;

    private ArrayList<Article> list = pars();


    public ArrayList<Article> getList() {
        return list;
    }

    public void res() {
        ArrayList<Article> list = this.list;

        list.forEach(System.out::println);
    }

    public String getTitle() {
        return title;
    }

    private Document connect() {

        Document doc = null;
        boolean flag = true;
        while (flag) {

            try {
               // String place = new Scanner(System.in).nextLine();
                String place = "винница";

                doc = Jsoup.connect("https://sinoptik.ua/погода-" + place).get();
                flag = false;


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return doc;

    }


    public ArrayList<Article> pars() {




        ArrayList<Article> list = new ArrayList<Article>();

        Elements elements = doc.getElementsByAttributeValue("class", "tabs"); //анализ считаного документа
        elements.forEach(element -> {

            for (int i = 1; i <= 14; i++) {
                String day = element.child(i).text();
                list.add(new Article(day));
            }
        });
        /*list.forEach(System.out::println);*/

        return list;
    }

    public List<String> date_int(){
        String str = getList().toString();
        str = str.replaceAll("[^-?0-9]+", " ");
        List<String> date = Arrays.asList(str.trim().split(" "));


        return date;
    }




}
