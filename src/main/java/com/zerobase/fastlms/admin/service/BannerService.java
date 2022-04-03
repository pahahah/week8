package com.zerobase.fastlms.admin.service;

import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.model.BannerInput;
import com.zerobase.fastlms.admin.model.BannerParam;

import java.util.List;

public interface BannerService {
    /**
     * 배너 등록
     * @param parameter
     * @return
     */
    boolean add(BannerInput parameter);

    /**
     * 배너삭제
     * @param idList
     * @return
     */
    boolean del (String idList);

    List<BannerDto> list(BannerParam parameter);

    BannerDto getById(long id);

    /**
     * 배너 내용 수정
     * @param parameter
     * @return
     */
    boolean set(BannerInput parameter);


    List<BannerDto> frontList(BannerParam parameter);

    boolean updateOpenMethode(long id, String openMethod);
}
