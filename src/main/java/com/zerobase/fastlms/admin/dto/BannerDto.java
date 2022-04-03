package com.zerobase.fastlms.admin.dto;

import com.zerobase.fastlms.admin.entity.Banner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BannerDto {
    Long id;
    String bannerName;
    String filename;
    String urlFilename;
    String url;
    String openMethode;
    boolean showYn;
    LocalDateTime regDt;
    int sortValue;
    long totalCount;
    long seq;


    public static BannerDto of(Banner banner){
        return BannerDto.builder()
                .id(banner.getId())
                .bannerName(banner.getBannerName())
                .filename(banner.getFilename())
                .urlFilename(banner.getUrlFilename())
                .url(banner.getUrl())
                .openMethode(banner.getOpenMethod())
                .showYn(banner.isShowYn())
                .regDt(banner.getRegDt())
                .sortValue(banner.getSortValue())
                .build();
    }

    public static List<BannerDto> of (List<Banner> banners){
        if (banners == null){
            return null;
        }
        List<BannerDto> bannerDtoList = new ArrayList<>();
        for( Banner x : banners){
            bannerDtoList.add(BannerDto.of(x));
        }
        return bannerDtoList;
    }
}
