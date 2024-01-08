package com.jcy.tradingstrategies.business.service;

import com.jcy.tradingstrategies.business.domain.dto.RenQiDto;

import java.util.List;

public interface IRenQiService {
    List<RenQiDto> convert(String response);
}
