package com.renan.bankingtranscationsapi.mapper;

import com.renan.bankingtranscationsapi.dto.TransactionDTO;
import com.renan.bankingtranscationsapi.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionMapper {

  TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

  TransactionDTO entityToDto(Transaction entity);
}
