package com.zerobase.fastlms.admin.service;

import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.entity.Banner;
import com.zerobase.fastlms.admin.mapper.BannerMapper;
import com.zerobase.fastlms.admin.model.BannerInput;
import com.zerobase.fastlms.admin.model.BannerParam;
import com.zerobase.fastlms.admin.repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BannerServiceImpl implements BannerService{

    private final BannerRepository bannerRepository;
    private final BannerMapper bannerMapper;

    private Sort getSortBySortValueDesc(){
        return Sort.by(Sort.Direction.DESC, "sortValue");
    }

    @Override
    public boolean add(BannerInput parameter) {

        Banner banner = Banner.builder()
                .bannerName(parameter.getBannerName())
                .filename(parameter.getFilename())
                .regDt(LocalDateTime.now())
                .showYn(parameter.isShowYn())
                .url(parameter.getUrl())
                .openMethod(parameter.getOpenMethod())
                .sortValue(parameter.getSortValue())
                .urlFilename(parameter.getUrlFilename())
                .build();

        bannerRepository.save(banner);

        return true;
    }

    @Override
    public boolean del(String idList) {
        if (idList != null && idList.length()>0){
            String[] ids = idList.split(",");
            for (String x : ids) {
                long id = 0L;
                try {
                    id = Long.parseLong(x);
                } catch (Exception e) {
                }
                if (id > 0) {
                    bannerRepository.deleteById(id);
                }
            }
        }

        return true;
    }

    @Override
    public List<BannerDto> list(BannerParam parameter) {
        long totalCount = bannerMapper.selectListCount(parameter);

        List<BannerDto> list = bannerMapper.selectList(parameter);
        if(!CollectionUtils.isEmpty(list)){
            int i = 0;
            for(BannerDto x : list){
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }
        return list;
    }

    @Override
    public BannerDto getById(long id) {
        return bannerRepository.findById(id).map(BannerDto::of).orElse(null);
    }

    @Override
    public boolean set(BannerInput parameter) {
        Optional<Banner> optionalBanner = bannerRepository.findById(parameter.getId());
        if(!optionalBanner.isPresent()){
            return false;
        }
        Banner banner = optionalBanner.get();
        banner.setBannerName(parameter.getBannerName());
        banner.setOpenMethod(parameter.getOpenMethod());
        banner.setRegDt(parameter.getRegDt());
        banner.setShowYn(parameter.isShowYn());
        banner.setUrl(parameter.getUrl());
        banner.setFilename(parameter.getFilename());
        banner.setUrlFilename(parameter.getUrlFilename());

        bannerRepository.save(banner);
        return true;
    }

    @Override
    public List<BannerDto> frontList(BannerParam parameter) {
        Optional<List<Banner>> optionalBanners = Optional.of(bannerRepository.findAll());
        if(optionalBanners.isPresent()){
            return BannerDto.of(optionalBanners.get());
        }
        return null;
    }

    @Override
    public boolean updateOpenMethode(long id, String openMethod) {
        Optional<Banner> optionalBanner = bannerRepository.findById(id);
        if(!optionalBanner.isPresent()){
            throw new NullPointerException("배너정보가 존재하지 않습니다.");
        }
        Banner banner = optionalBanner.get();
        banner.setOpenMethod(openMethod);
        bannerRepository.save(banner);
        return true;
    }
}
