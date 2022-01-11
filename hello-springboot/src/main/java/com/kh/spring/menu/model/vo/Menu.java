package com.kh.spring.menu.model.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String restaurant;
	private String name;
	private int price;
	private Type type; 		// "kr", "ch", "jp"
	private Taste taste; //"mild", "hot"

	
	
	

}
