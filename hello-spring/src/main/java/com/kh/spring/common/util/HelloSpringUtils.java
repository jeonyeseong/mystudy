package com.kh.spring.common.util;

public class HelloSpringUtils {

	
	/**
	 * 
	 * @param cPage
	 * @param numPerPage
	 * @param totalContent
	 * @param url
	 * 
	 * totalPage 전체페이지
	 * pagebarSize 페이지바 크기 5
	 * pageNo
	 * pageStart - pageEnd
	 * 
	 * @return
	 */
	public static String getPagebar(int cPage, int numPerPage, int totalContent, String url) {
		StringBuilder pagebar = new StringBuilder(); 
		url = url + "?cPage="; // pageNo 추가전 상태
		
		final int pagebarSize = 5;
		final int totalPage = (int) Math.ceil((double) totalContent / numPerPage);
		final int pageStart = (cPage - 1) / pagebarSize * pagebarSize + 1;
		int pageEnd = pageStart + pagebarSize - 1;
		pageEnd = totalPage < pageEnd ? totalPage : pageEnd;
		int pageNo = pageStart;

		// [이전]
		if(pageNo == 1) {
			// cPage = 1, 2, 3, 4, 5
			/*
				<li class="page-item disabled">
			      <a class="page-link" href="#" tabindex="-1">Previous</a>
			    </li>
 
			 */
		}
		else {
			/*
				<li class="page-item">
			      <a class="page-link" href="#" tabindex="-1">Previous</a>
			    </li>
			 */
			pagebar.append("<a href='" + url + (pageNo - 1) + "'>prev</a>\n");
		}
		
		// pageNo
		while(pageNo <= pageEnd) {
			if(pageNo == cPage) {
				pagebar.append("<span class='cPage'>" + cPage + "</span>\n");
			}
			else {
				pagebar.append("<a href='" + url + pageNo + "'>" + pageNo + "</a>\n");
			}
			
			pageNo++;
		}
		
		
		// [다음]
		if(pageNo > totalPage) {
			
		}
		else {
			pagebar.append("<a href='" + url + pageNo + "'>next</a>\n");
		}
		
		return pagebar.toString();
	}

}
