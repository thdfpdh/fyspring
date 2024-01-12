package com.pcwk.ehr.chart.domain;

import com.pcwk.ehr.cmn.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter  //setter
@Setter  //getter
@NoArgsConstructor //default 생성자
@AllArgsConstructor//모든인자 생성자
public class LevelPerMemberVO extends DTO {

	private String year;
	private String levelName;
	private long   count;
	
	
	@Override
	public String toString() {
		return "LevelPerMemberVO [year=" + year + ", levelName=" + levelName + ", count=" + count + ", toString()="
				+ super.toString() + "]";
	}
	
	
	
	
}
