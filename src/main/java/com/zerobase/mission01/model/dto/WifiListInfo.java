package com.zerobase.mission01.model.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@Setter
@ToString
public class WifiListInfo {
    @SerializedName("list_total_count")
    private int listTotalCount;

    private ArrayList<WifiInfo> row;
}