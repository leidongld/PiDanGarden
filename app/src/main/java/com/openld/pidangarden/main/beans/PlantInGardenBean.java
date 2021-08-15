package com.openld.pidangarden.main.beans;

import java.io.Serializable;

/**
 * author: lllddd
 * created on: 2021/8/10 9:32
 * description:
 */
public class PlantInGardenBean implements Serializable {
    // 植物名称
    private String plantName;

    // 植物描述
    private String plantDescription;

    // 本地路径
    private String localImgPath;

    // 远端路径
    private String remoteImgPath;

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
}
