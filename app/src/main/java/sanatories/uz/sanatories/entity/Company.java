package sanatories.uz.sanatories.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Company {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("main_id")
    @Expose
    private int mainId;
    @SerializedName("days")
    @Expose
    private int days;


    public int getMainId() {
        return mainId;
    }

    public void setMainId(int mainId) {
        this.mainId = mainId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}
