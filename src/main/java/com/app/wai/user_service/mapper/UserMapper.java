package com.app.wai.user_service.mapper;

import com.app.wai.user_service.model.dto.JwtTokenDto;
import com.app.wai.user_service.model.dto.RegisterDto;
import com.app.wai.user_service.model.entity.Account;
import com.app.wai.user_service.model.request.RegisterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {


    Account mappingRegisterToAccount(RegisterRequest request);
    @Mapping(source = "id",target = "userId")
    RegisterDto mappingAccountToDto(Account account);
    @Mapping(source = "id",target = "userId")
    JwtTokenDto mappingAccountToJwtDto(Account account);
}
