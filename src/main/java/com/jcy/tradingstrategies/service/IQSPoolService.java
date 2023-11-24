package com.jcy.tradingstrategies.service;

import com.jcy.tradingstrategies.domain.dto.QSPoolDto;

import java.util.List;

public interface IQSPoolService {

    void insert(String response,String date);

    List<QSPoolDto> selectByDate(String date);
}
