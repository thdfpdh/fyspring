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
public class PizzaVO extends DTO {

	private String topping;
	private int    slices;
	
	@Override
	public String toString() {
		return "PizzaVO [topping=" + topping + ", slices=" + slices + ", toString()=" + super.toString() + "]";
	}
	
	
}
  