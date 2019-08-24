package com.ncu.ebook.mapper;

import com.ncu.ebook.pojo.po.user.LocalAuthorization;
import org.apache.ibatis.annotations.Mapper;

/**
 * @InterfaceName : LocalAuthorizationMapper
 * @Description :
 * @Author : xfakir
 * @Date : 2019-08-20 20:17
 * @Version : 1.0
 */
@Mapper
public interface LocalAuthorizationMapper {

    LocalAuthorization getByPrincipal(String principal);

    void addRole(Long userId, Integer roleId);
}
