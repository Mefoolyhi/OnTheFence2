package gabdorahmanova.onthefence;

import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by admin on 18.02.2018.
 */

public class ParsingClass extends DefaultHandler {
    private String URL = "http://культура.екатеринбург.рф/news/564/";


    private ArrayList<PostValue> items = new ArrayList<>();
    private PostValue item = null;
    public String currTagVal = "";
    boolean currTag = false;

}
