package gabdorahmanova.onthefence;



import android.net.Uri;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import gabdorahmanova.onthefence.Units.PostValue;

/**
 * Created by admin on 18.02.2018.
 */

public class ParsingClass {
    private String URL = "https://www.oblgazeta.ru/culture/ ";


    private ArrayList<PostValue> items = new ArrayList<>();
    private ArrayList<String> times = new ArrayList<>();
    private ArrayList<String> headings = new ArrayList<>();
    private ArrayList<String> links = new ArrayList<>();



    public ArrayList<PostValue> getPostsList(){

        return items;
    }

    public void get(){
        try{
            Document doc = Jsoup.connect(URL).get();
            Elements content = doc.select(".effect-sarah desctop_view");
            times.clear();
            for (Element contains : content){
                times.add(contains.text());
            }
            Log.e(Integer.toString(times.size()),times.toString());
            content = doc.select(".lazy");
            links.clear();
            for (Element contains: content){
                links.add(contains.text());
            }
            Log.e(Integer.toString(links.size()),links.toString());
            content = doc.select(".miko-wrapper");
            headings.clear();
            for (Element contains: content){
                headings.add(contains.text());
            }
            Log.e(Integer.toString(headings.size()),headings.toString());

            if(links.size() == times.size() && times.size() == headings.size()){
                items.clear();
                for (int i = 0; i < headings.size();i++){
                    PostValue pv = new PostValue(times.get(i),headings.get(i),links.get(i));
                    items.add(pv);
                }

            }
            else{
                Log.e("PARsING","Разные размеры, ё маё");
            }

        }catch (Exception e){
            e.getMessage();
        }
    }


}
