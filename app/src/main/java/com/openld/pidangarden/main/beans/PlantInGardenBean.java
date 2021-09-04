package com.openld.pidangarden.main.beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * author: lllddd
 * created on: 2021/8/10 9:32
 * description:
 */
public class PlantInGardenBean implements Serializable, Parcelable {
    // 植物名称
    private String plantName;

    // 植物描述
    private String plantDescription;

    // 本地路径
    private String localImgPath;

    // 远端路径
    private String remoteImgPath;

    public PlantInGardenBean() {

    }

    protected PlantInGardenBean(Parcel in) {
        plantName = in.readString();
        plantDescription = in.readString();
        localImgPath = in.readString();
        remoteImgPath = in.readString();
    }

    public static final Creator<PlantInGardenBean> CREATOR = new Creator<PlantInGardenBean>() {
        @Override
        public PlantInGardenBean createFromParcel(Parcel in) {
            return new PlantInGardenBean(in);
        }

        @Override
        public PlantInGardenBean[] newArray(int size) {
            return new PlantInGardenBean[size];
        }
    };

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getPlantDescription() {
        return plantDescription;
    }

    public void setPlantDescription(String plantDescription) {
        this.plantDescription = plantDescription;
    }

    public String getLocalImgPath() {
        return localImgPath;
    }

    public void setLocalImgPath(String localImgPath) {
        this.localImgPath = localImgPath;
    }

    public String getRemoteImgPath() {
        return remoteImgPath;
    }

    public void setRemoteImgPath(String remoteImgPath) {
        this.remoteImgPath = remoteImgPath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(plantName);
        dest.writeString(plantDescription);
        dest.writeString(localImgPath);
        dest.writeString(remoteImgPath);
    }
}
