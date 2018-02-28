package gabdorahmanova.onthefence;

import android.graphics.Bitmap;

import java.net.URL;

/**
 * Created by admin on 28.02.2018.
 */

public class Theatre {
    String name;
    private Bitmap picture;
    String link;
    String info;
    String history;
    String piclink;

    Theatre( String name,
            String link,
            String info, String piclink, String history){
        this.name = name;
        this.link = link;
        this.info = info;
        this.history = history;
        this.piclink = piclink;
    }

    void setPicture(Bitmap picture){
        this.picture = picture;
    }
    Bitmap getPicture(){
        return picture;
    }

}
