package fr.hb.retrouvezmoi.models;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

import fr.hb.retrouvezmoi.util.ImageUtil;

public class Post implements Parcelable {
    private Long id;
    private String title;
    private String description;
    private String lastname;
    private String firstname;
    private String address;
    private CivilityEnum civility;
    private String email;
    private String phoneNumber;
    private Date createdDate;
    private String pictureBase64;

    public Post(String title, String description,  CivilityEnum civility, String lastname,String firstname, String address,String email, String phoneNumber ) {
        this.title = title;
        this.description = description;
        this.civility = civility;
        this.lastname=lastname;
        this.firstname=firstname;
        this.address = address;
        this.email = email;
        this.phoneNumber=phoneNumber;
        this.createdDate = new Date();

    }

    public Post(Long id , String title, String description, String lastname, String firstname, String address, CivilityEnum civility, String email, String phoneNumber, Date createdDate, String pictureBase64) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.lastname = lastname;
        this.firstname = firstname;
        this.address = address;
        this.civility = civility;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.createdDate = createdDate;
        this.pictureBase64 = pictureBase64;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getAddress() {
        return address;
    }

    public CivilityEnum getCivility() {
        return civility;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCivility(CivilityEnum civility) {
        this.civility = civility;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Bitmap getPictureBase64() {
        return pictureBase64 != null ? ImageUtil.convert(pictureBase64) : null;
    }

    public void setPictureBase64(Bitmap pictureBmp) {
        this.pictureBase64 = ImageUtil.convert(pictureBmp);
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.lastname);
        dest.writeString(this.firstname);
        dest.writeString(this.address);
        dest.writeInt(this.civility == null ? -1 : this.civility.ordinal());
        dest.writeString(this.email);
        dest.writeString(this.phoneNumber);
        dest.writeLong(this.createdDate != null ? this.createdDate.getTime() : -1);
        dest.writeString(this.pictureBase64);
    }

    public void readFromParcel(Parcel source) {
        this.title = source.readString();
        this.description = source.readString();
        this.lastname = source.readString();
        this.firstname = source.readString();
        this.address = source.readString();
        int tmpCivility = source.readInt();
        this.civility = tmpCivility == -1 ? null : CivilityEnum.values()[tmpCivility];
        this.email = source.readString();
        this.phoneNumber = source.readString();
        long tmpCreatedDate = source.readLong();
        this.createdDate = tmpCreatedDate == -1 ? null : new Date(tmpCreatedDate);
        this.pictureBase64 = source.readString();
    }

    protected Post(Parcel in) {
        this.title = in.readString();
        this.description = in.readString();
        this.lastname = in.readString();
        this.firstname = in.readString();
        this.address = in.readString();
        int tmpCivility = in.readInt();
        this.civility = tmpCivility == -1 ? null : CivilityEnum.values()[tmpCivility];
        this.email = in.readString();
        this.phoneNumber = in.readString();
        long tmpCreatedDate = in.readLong();
        this.createdDate = tmpCreatedDate == -1 ? null : new Date(tmpCreatedDate);
        this.pictureBase64 = in.readString();
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel source) {
            return new Post(source);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };
    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", address='" + address + '\'' +
                ", civility=" + civility +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
