package com.funworks.woof.data;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

/**
 * Created by rahulhariharan on 29/07/18.
 */

public class AllBreeds {
    @SerializedName("status") String status;
    @SerializedName("message") JSONObject message;
}
