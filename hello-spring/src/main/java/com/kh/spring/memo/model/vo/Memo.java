package com.kh.spring.memo.model.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString

@Data //@Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Memo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int no;
	private String memo;
	private String password;
	private Date regDate;

}
