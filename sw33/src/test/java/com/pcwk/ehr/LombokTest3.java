package com.pcwk.ehr;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
public class LombokTest3 {

	private String name;//생성자에 포함 될려면 final
	private int age;
	
	public static void main(String[] args) {
		LombokTest3 main=new LombokTest3();
		
		main.setName("Lombok");
		main.setAge(1);
		
		System.out.println("@Getter:"+main.getName());
		System.out.println("@Getter:"+main.getAge());
		
		System.out.println("main:"+main);
	}

}
//@Getter:Lombok
//@Getter:1
//main:LombokTest3(name=Lombok, age=1)