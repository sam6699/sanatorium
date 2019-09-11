package sanatories.uz.sanatories.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class RoomImage {
    @SerializedName("id")
    @Expose
        private int id;
    @SerializedName("room_id")
    @Expose
    private Room roomId;
    @SerializedName("images")
    @Expose
    private ArrayList<Image> imageList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Room getRoomId() {
        return roomId;
    }

    public void setRoomId(Room roomId) {
        this.roomId = roomId;
    }

    public ArrayList<Image> getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList<Image> imageList) {
        this.imageList = imageList;
    }
}
