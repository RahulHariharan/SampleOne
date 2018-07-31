package com.funworks.woof.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rahulhariharan on 29/07/18.
 */

public class RandomDogs {
    @SerializedName("status") String status;
    @SerializedName("message") List<String> messages;
}
