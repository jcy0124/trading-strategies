package com.jcy.tradingstrategies.service;

import com.jcy.tradingstrategies.domain.dto.YouZiDto;

import java.util.List;

public interface IYouZiService {

    List<YouZiDto> convert(String response);
}
