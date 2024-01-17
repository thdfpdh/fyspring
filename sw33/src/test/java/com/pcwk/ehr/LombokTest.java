package com.pcwk.ehr;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LombokTest {

	private String name;
	private int age;
	
	public static void main(String[] args) {
		LombokTest main=new LombokTest();
		
		main.setName("Lombok");
		main.setAge(1);
		
		System.out.println("@Getter:"+main.getName());
		System.out.println("@Getter:"+main.getAge());
	}

}
//@Getter:Lombok
//@Getter:1