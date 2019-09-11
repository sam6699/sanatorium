package uz.kindergarten.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Room {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("type_id")
    @Expose
    private TypeId typeId;
    @SerializedName("price")
    @Expose
    private int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TypeId getTypeId() {
        return typeId;
    }

    public void setTypeId(TypeId typeId) {
        this.typeId = typeId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
