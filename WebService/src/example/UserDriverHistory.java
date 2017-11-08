package example;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserDriverHistory {
    private long dateInMills;
    private String name;
    private int id;
    private String picking_point;
    private String destination;
    private int rating;
    private String comment;
    private int order_id;
    private String date;

    public void setDateInMills(long dateInMills) {
        this.dateInMills = dateInMills;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPickingPoint(String picking_point) {
        this.picking_point = picking_point;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDateInMills() {

        return dateInMills;
    }
    public void setOrderId(int order_id) {
        this.order_id = order_id;
    }

    public String getName() {
        return name;
    }

    public String getPickingPoint() {
        return picking_point;
    }

    public String getDestination() {
        return destination;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public int getId() {
        return id;
    }

    public int getOrderId() {

        return order_id;
    }

    public String getDate() {
        return date;
    }

    public UserDriverHistory(int id, long dateInMills, String name, String picking_point, String destination, int rating,
                             String comment, int order_id) {
        this.id = id;
        this.dateInMills = dateInMills;
        this.name = name;
        this.picking_point = picking_point;
        this.destination = destination;
        this.rating = rating;
        this.comment = comment;
        this.order_id = order_id;
        Date date = new Date(dateInMills);
        DateFormat formatter = new SimpleDateFormat("EEEE, MMMM d yyyy");
        this.date = formatter.format(date);
    }
}
