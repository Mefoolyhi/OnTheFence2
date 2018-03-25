package gabdorahmanova.onthefence.Units;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 28.02.2018.
 */

public class Theatre {
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    String fav;
    String name;
    String link;
    String info;
    String piclink;

    String number, site;

    public String getFav() {
        return fav;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @Override
    public String toString() {
        return name + " " + id + " " + fav;
    }

    public void setFav(String fav) {
        this.fav = fav;
    }

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


    public String getPiclink() {
        return piclink;
    }

    public void setPiclink(String piclink) {
        this.piclink = piclink;
    }

    public Theatre(int id, String name,
                   String link,
                   String info, String piclink, String number, String site, String fav) {
        this.name = name;
        this.id = id;
        this.link = link;
        this.info = info;
        this.piclink = piclink;
        this.fav = fav;
        this.site = site;
        this.number = number;
    }


}


