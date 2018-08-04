package com.funworks.woof.data;

import android.arch.lifecycle.LiveData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.util.Optional;

/**
 * Created by rahulhariharan on 29/07/18.
 */

public class AllBreeds extends LiveData {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private Message message;

    public String getStatus() {
        return status;
    }

    public Message getMessage() {
        return message;
    }
}
