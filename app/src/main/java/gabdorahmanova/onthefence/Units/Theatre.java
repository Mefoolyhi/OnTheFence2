package gabdorahmanova.onthefence.Units;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 28.02.2018.
 */

public class Theatre{
    long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    String name;
    private Bitmap picture;
    String link;
    String info;
    String history;
    String piclink;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getPiclink() {
        return piclink;
    }

    public void setPiclink(String piclink) {
        this.piclink = piclink;
    }

    public Theatre(long id,String name,
                   String link,
                   String info, String piclink, String history){
        this.name = name;
        this.id = id;
        this.link = link;
        this.info = info;
        this.history = history;
        this.piclink = piclink;
    }



    public void setPicture(Bitmap picture){
        this.picture = picture;
    }
    public Bitmap getPicture(){
        return picture;
    }}


