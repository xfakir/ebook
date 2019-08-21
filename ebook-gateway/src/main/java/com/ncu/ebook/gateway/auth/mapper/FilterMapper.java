package com.ncu.ebook.gateway.auth.mapper;

import com.ncu.ebook.gateway.auth.pojo.UrlFilter;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @InterfaceName : FilterMapper
 * @Description :
 * @Author : xfakir
 * @Date : 2019-08-15 14:05
 * @Version : 1.0
 */
@Mapper
public interface FilterMapper {
    List<UrlFilter> getAllUrlFilters();

    void addUrlFilter(UrlFilter urlFilter);

    //void deleteUrlFilterById(Long urlFilterId);
}
