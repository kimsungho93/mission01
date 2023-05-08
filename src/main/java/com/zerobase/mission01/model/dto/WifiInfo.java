package com.zerobase.mission01.model.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

@Getter
@Setter
@ToString
public class WifiInfo {
    private double distance;            // 거리

    @FieldNameConstants.Exclude
    private String X_SWIFI_MGR_NO;      // "관리번호"
    @FieldNameConstants.Exclude
    private String X_SWIFI_WRDOFC;      // "자치구"
    @FieldNameConstants.Exclude
    private String X_SWIFI_MAIN_NM;     // "와이파이명"
    @FieldNameConstants.Exclude
    private String X_SWIFI_ADRES1;      // "도로명주소"
    @FieldNameConstants.Exclude
    private String X_SWIFI_ADRES2;      // "상세주소"
    @FieldNameConstants.Exclude
    private String X_SWIFI_INSTL_FLOOR; // "설치위치(층)"
    @FieldNameConstants.Exclude
    private String X_SWIFI_INSTL_TY;    // "설치유형"
    @FieldNameConstants.Exclude
    private String X_SWIFI_INSTL_MBY;   // "설치기관"
    @FieldNameConstants.Exclude
    private String X_SWIFI_SVC_SE;      // "서비스구분"
    @FieldNameConstants.Exclude
    private String X_SWIFI_CMCWR;       // "망종류"
    @FieldNameConstants.Exclude
    private String X_SWIFI_CNSTC_YEAR;  // "설치년도"
    @FieldNameConstants.Exclude
    private String X_SWIFI_INOUT_DOOR;  // "실내외구분"
    @FieldNameConstants.Exclude
    private String X_SWIFI_REMARS3;     // "wifi접속환경"

    @FieldNameConstants.Exclude
    @SerializedName("LNT")
    private double LAT;                 // "X좌표"

    @FieldNameConstants.Exclude
    @SerializedName("LAT")
    private double LNT;                 // "Y좌표"

    @FieldNameConstants.Exclude
    private String WORK_DTTM;           // "작업일자"
}
