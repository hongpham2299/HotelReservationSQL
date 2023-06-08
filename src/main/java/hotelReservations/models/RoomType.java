package hotelReservations.models;

public class RoomType {

    private String roomTypeID;
    private String roomTypeName;

    public RoomType(){}

    public RoomType(String roomTypeID, String roomTypeName) {
        this.roomTypeID = roomTypeID;
        this.roomTypeName = roomTypeName;
    }

    public String getRoomTypeID() {
        return roomTypeID;
    }

    public void setRoomTypeID(String roomTypeID) {
        this.roomTypeID = roomTypeID;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

}
