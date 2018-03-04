package gabdorahmanova.onthefence;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.net.URL;

/**
 * Created by admin on 28.02.2018.
 */

public class Theatre implements Parcelable{
    String name;
    private Bitmap picture;
    String link;
    String info;
    String history;
    String piclink;

    public Theatre(String name,
                   String link,
                   String info, String piclink, String history){
        this.name = name;
        this.link = link;
        this.info = info;
        this.history = history;
        this.piclink = piclink;
    }

    protected Theatre(Parcel in) {
        name = in.readString();
        picture = in.readParcelable(Bitmap.class.getClassLoader());
        link = in.readString();
        info = in.readString();
        history = in.readString();
        piclink = in.readString();
    }

    public static final Creator<Theatre> CREATOR = new Creator<Theatre>() {
        @Override
        public Theatre createFromParcel(Parcel in) {
            return new Theatre(in);
        }

        @Override
        public Theatre[] newArray(int size) {
            return new Theatre[size];
        }
    };

    void setPicture(Bitmap picture){
        this.picture = picture;
    }
    Bitmap getPicture(){
        return picture;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeParcelable(picture, flags);
        dest.writeString(link);
        dest.writeString(info);
        dest.writeString(history);
        dest.writeString(piclink);
    }
}
