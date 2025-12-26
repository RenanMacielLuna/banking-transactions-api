package com.renan.bankingtranscationsapi.mapper;

import com.renan.bankingtranscationsapi.dto.BankAccountDTO;
import com.renan.bankingtranscationsapi.model.BankAccount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BankAccountMapper {

  BankAccountMapper INSTANCE = Mappers.getMapper(BankAccountMapper.class);

  BankAccountDTO entityToDto(BankAccount entity);
}
