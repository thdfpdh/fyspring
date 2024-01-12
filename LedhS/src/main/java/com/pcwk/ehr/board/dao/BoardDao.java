package com.pcwk.ehr.board.dao;

import java.sql.SQLException;

import com.pcwk.ehr.board.domain.BoardVO;
import com.pcwk.ehr.cmn.WorkDiv;

public interface BoardDao extends WorkDiv<BoardVO> {

	int getBoardSeq() throws SQLException;

}
