package danilchanka.aliaksandr.dota2proteam.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ProTeam implements Parcelable {

    /**
     * team_id : 2163
     * rating : 1572.66
     * wins : 630
     * losses : 389
     * last_match_time : 1519235445
     * name : Team Liquid
     * tag : Liquid
     * logo_url : https://steamusercontent-a.akamaihd.net/ugc/858347654776522964/E70F0E063879154A1982B3C907D6A5DFDA183BF9/
     */

    @SerializedName("team_id")
    private int teamId;
    private double rating;
    private int wins;
    private int losses;
    @SerializedName("last_match_time")
    private int lastMatchTime;
    private String name;
    private String tag;
    private String logo_url;

    protected ProTeam(Parcel in) {
        teamId = in.readInt();
        rating = in.readDouble();
        wins = in.readInt();
        losses = in.readInt();
        lastMatchTime = in.readInt();
        name = in.readString();
        tag = in.readString();
        logo_url = in.readString();
    }

    public static final Creator<ProTeam> CREATOR = new Creator<ProTeam>() {
        @Override
        public ProTeam createFromParcel(Parcel in) {
            return new ProTeam(in);
        }

        @Override
        public ProTeam[] newArray(int size) {
            return new ProTeam[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(teamId);
        parcel.writeDouble(rating);
        parcel.writeInt(wins);
        parcel.writeInt(losses);
        parcel.writeInt(lastMatchTime);
        parcel.writeString(name);
        parcel.writeString(tag);
        parcel.writeString(logo_url);
    }

    public int getTeamId() {
        return teamId;
    }

    public double getRating() {
        return rating;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getLastMatchTime() {
        return lastMatchTime;
    }

    public String getName() {
        return name;
    }

    public String getTag() {
        return tag;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public void setLastMatchTime(int lastMatchTime) {
        this.lastMatchTime = lastMatchTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }
}
