package gabdorahmanova.onthefence.Helpers;



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
    private String URL = "http://www.justmedia.ru/news/culture ";


    private ArrayList<PostValue> items = new ArrayList<>();
    private ArrayList<String> times = new ArrayList<>();
    private ArrayList<String> headings = new ArrayList<>();
    private ArrayList<String> links = new ArrayList<>();



    public ArrayList<PostValue> getPostsList(){

        return items;
    }

    public void get() throws Exception{

            Document doc = Jsoup.connect(URL).get();
            Elements content = doc.select(".b-subjects-list__date");
            times.clear();
            for (Element contains : content){
                times.add(contains.text());
            }
            Log.e(Integer.toString(times.size()),times.toString());
            content = doc.select(".b-subjects-list__img").select("img");
            links.clear();
            for (Element contains: content){
                links.add(contains.absUrl("src"));
            }
            Log.e(Integer.toString(links.size()),links.toString());
            content = doc.select(".b-subjects-list__title");
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


    }


}
