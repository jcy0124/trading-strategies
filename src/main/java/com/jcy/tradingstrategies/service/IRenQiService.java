package com.jcy.tradingstrategies.service;

import com.jcy.tradingstrategies.domain.dto.RenQiDto;

import java.util.List;

public interface IRenQiService {
    List<RenQiDto> convert(String response);
}
