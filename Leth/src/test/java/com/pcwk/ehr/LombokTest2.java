package com.pcwk.ehr;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
//@Setter
//@RequiredArgsConstructor
@ToString
@NoArgsConstructor  //Default생성자
@AllArgsConstructor
public class LombokTest2 {

	private String name;//생성자에 포함 될려면 final
	private int age;
	
	public static void main(String[] args) {
		LombokTest2 main2=new LombokTest2();
		LombokTest2 main=new LombokTest2("Lombok",1);
		
		//main.setName("Lombok");
		//main.setAge(1);
		
		System.out.println("@Getter:"+main.getName());
		System.out.println("@Getter:"+main.getAge());
		
		System.out.println("main:"+main);
	}

}
//@Getter:Lombok
//@Getter:1