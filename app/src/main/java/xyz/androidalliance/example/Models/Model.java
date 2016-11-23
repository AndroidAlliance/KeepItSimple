package xyz.androidalliance.example.Models;

import android.util.Log;

public class Model {

    public String getDefaultText(int position, long random) {
        Log.d("Model", "getDefaultText() called with: position = [" + position + "], random = [" + random + "]");
        return String.format("This is Screen %1$s and the persistent random number is %2$s", position, random);
    }

}
