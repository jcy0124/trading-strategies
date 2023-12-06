package com.jcy.tradingstrategies.service;

import com.jcy.tradingstrategies.domain.dto.FSCJLDto;

import java.util.List;

public interface IFSCJLService {

    List<FSCJLDto> getFSCJLResp(String response);
}
