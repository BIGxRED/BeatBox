package com.palarz.mike.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mike on 10/21/16.
 */
public class BeatBox {
    private static final String TAG = "BeatBox";
    private static final String SOUNDS_FOLDER = "sample_sounds";
    private static final int MAX_SOUNDS = 5;

    private AssetManager mAssets;
    private List<Sound> mSounds = new ArrayList<>();
    private SoundPool mSoundPool;

    public BeatBox (Context context){
        mAssets = context.getAssets();
        //This is an old constructor which has been deprecated, but we need to use it for
        //compatibility reasons since the minimum SDK version we're supporting is API 16.
        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);

        loadSounds();
    }

    private void loadSounds(){
        String [] soundNames;

        try{
            soundNames = mAssets.list(SOUNDS_FOLDER);
            Log.i(TAG, "Found " + soundNames.length + " sounds");
        }
        catch (IOException ioe){
            Log.e(TAG, "Could not list assets", ioe);
            return;
        }

        for(String filename : soundNames){
            try {
                String assetPath = SOUNDS_FOLDER + "/" + filename;
                Sound sound = new Sound(assetPath);
                load(sound);
                mSounds.add(sound);
            }
            catch(IOException ioe){
                Log.e(TAG, "Could not load sound " + filename, ioe);
            }
        }
    }

    private void load(Sound sound) throws IOException{
        AssetFileDescriptor afd = mAssets.openFd(sound.getAssetPath());
        int soundID = mSoundPool.load(afd, 1);
        sound.setSoundID(soundID);
    }

    public List<Sound> getSounds(){
        return mSounds;
    }

    public void play(Sound sound){
        Integer soundID = sound.getSoundID();
        if(soundID == null)
            return;
        mSoundPool.play(soundID, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void release(){
        mSoundPool.release();
    }

}
