package com.pcwk.ehr.naver.service;

import java.io.IOException;

import com.pcwk.ehr.cmn.DTO;

public interface NaverSearchService {

    /**
     * Naver blog	
     * @param inVO
     * @return String(json)
     * @throws IOException
     */
	String doNaverSearch(DTO inVO) throws IOException;
}
