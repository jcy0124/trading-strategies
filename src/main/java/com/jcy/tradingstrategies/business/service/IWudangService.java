package com.jcy.tradingstrategies.business.service;

import com.jcy.tradingstrategies.business.domain.dto.SSGPDto;
import org.aspectj.apache.bcel.classfile.Code;

public interface IWudangService {

    SSGPDto getSSGP(String code);
}
