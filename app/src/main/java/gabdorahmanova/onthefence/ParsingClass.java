package gabdorahmanova.onthefence;

import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

import gabdorahmanova.onthefence.Units.PostValue;

/**
 * Created by admin on 18.02.2018.
 */

public class ParsingClass extends DefaultHandler {
    private String URL = "https://www.oblgazeta.ru/culture/ ";


    private ArrayList<PostValue> items = new ArrayList<>();
    private PostValue item = null;
    public String currTagVal = "";
    boolean currTag = false;

}
