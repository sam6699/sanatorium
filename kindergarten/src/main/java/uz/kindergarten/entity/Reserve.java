package uz.kindergarten.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reserve {
    @SerializedName("fio")
    @Expose
    private String name;
    @SerializedName("data")
    @Expose
    private String data;
    @SerializedName("inn")
    @Expose
    private int inn;
    @SerializedName("telefon")
    @Expose
    private String phone;
    @SerializedName("email")
    @Expose
    private String mail;
    @SerializedName("foto")
    @Expose
    private String path;
    @SerializedName("room_id")
    @Expose
    private int room_id;
    @SerializedName("days")
    @Expose
    private int days;
    @SerializedName("total")
    @Expose
    private int total;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getInn() {
        return inn;
    }

    public void setInn(int inn) {
        this.inn = inn;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


}
