package com.provedcode.user.mapper;

import com.provedcode.user.model.entity.Authority;
import com.provedcode.user.model.entity.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserInfoMapper {
    default UserDetails toUserDetails(UserInfo user) {
        return User.withUsername(user.getLogin())
                   .password(user.getPassword())
                   .authorities(user.getAuthorities()
                                    .stream()
                                    .map(Authority::getAuthority)
                                    .toList())
                   .build();
    }
}