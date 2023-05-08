package com.zerobase.mission01.model.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Result {
    @SerializedName("TbPublicWifiInfo")
    private WifiListInfo wifiListInfo;
}
