package com.zerobase.fastlms.admin.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BannerInput {
    long id;
    String bannerName;
    String url;
    String openMethod;
    boolean showYn;
    LocalDateTime regDt;

    int sortValue;
    String idList;

    String filename;
    String urlFilename;
}
