package com.fwwb.back_end.utils.webclientUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UrlEnum {
    TEST("/api/test/getString"),
    TEST_STATION_LIST("/api/station/getLineStationInfo");

    private String url;
}
