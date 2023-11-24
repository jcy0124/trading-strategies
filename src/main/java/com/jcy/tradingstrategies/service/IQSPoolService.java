package com.jcy.tradingstrategies.service;

import com.jcy.tradingstrategies.dto.QSPoolDto;
import com.jcy.tradingstrategies.dto.ZTPoolDto;

import java.util.List;

public interface IQSPoolService {

    void insert(String response,String date);

    List<QSPoolDto> selectByDate(String date);
}
