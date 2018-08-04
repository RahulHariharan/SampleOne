package com.funworks.woof.data;

import com.google.gson.annotations.SerializedName;

import java.util.Optional;

/**
 * Created by rahulhariharan on 29/07/18.
 */

public class RandomDog {
    @SerializedName("status")
    public String status;
    @SerializedName("message")
    public String message;
}
