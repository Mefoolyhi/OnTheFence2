package gabdorahmanova.onthefence.Units;

/**
 * Created by admin on 20.03.2018.
 */

public class Performance {
    String name, cost,type,place,time,link;

    public Performance(String name, String cost, String place, String type, String time,String link){
        this.name = name;
        this.cost = cost;
        this.type = type;
        this.place = place;
        this.time = time;
        this.link = link;
    }

    @Override
    public String toString() {
        return name+" "+type+" "+place+" "+cost+ " "+time + "\n";
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
