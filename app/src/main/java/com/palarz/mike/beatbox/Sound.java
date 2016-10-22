package com.palarz.mike.beatbox;

/**
 * Created by mike on 10/21/16.
 */
public class Sound {
    private String mAssetPath;
    private String mName;
    private Integer mSoundID;

    public Sound (String assetPath){
        mAssetPath = assetPath;
        String [] components = assetPath.split("/");
        String filename = components[components.length - 1];
        mName = filename.replace(".wav","");
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getAssetPath() {
        return mAssetPath;
    }

    public void setAssetPath(String assetPath) {
        this.mAssetPath = assetPath;
    }

    public Integer getSoundID() {
        return mSoundID;
    }

    public void setSoundID(Integer soundID) {
        this.mSoundID = soundID;
    }
}
