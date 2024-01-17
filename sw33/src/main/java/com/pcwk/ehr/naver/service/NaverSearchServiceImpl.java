package com.pcwk.ehr.naver.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.PcwkLogger;

@Service
public class NaverSearchServiceImpl implements NaverSearchService, PcwkLogger {

	//8gJoErzwHfZSgfDXYb09/Upe9nocqdf

	//blog: https://openapi.naver.com/v1/search/blog.json
	//news: https://openapi.naver.com/v1/search/news.json
	//영화  : https://openapi.naver.com/v1/search/movie.json
	
	//https://openapi.naver.com/v1/search +blog+.json
	final String BASE_URL = "https://openapi.naver.com/v1/search/";
	final String SUFFIX   = ".json";
	
	final String CLIENT_ID = "8gJoErzwHfZSgfDXYb09";
	final String CLIENT_SECRET = "Upe9nocqdf";
	
	public NaverSearchServiceImpl() {}
	
	
	@Override
	public String doNaverSearch(DTO inVO) throws IOException {
		String responseBody = "";//json 데이터 
		String blog = BASE_URL+"blog"+SUFFIX;//URL
		String news = BASE_URL+"news"+SUFFIX;//URL	
		String book = BASE_URL+"book"+SUFFIX;
		
		
		String text   = "";//검색어(UTF-8)
		String apiURL = "";
		
		try {
			//검색어. UTF-8로 인코딩되어야 합니다.
			text = URLEncoder.encode(inVO.getSearchWord(),"UTF-8");
			
			//검색 결과 정렬 방법
			//- sim: 정확도순으로 내림차순 정렬(기본값)
			//- date: 날짜순으로 내림차순 정렬
			String sort = "date";//default 날짜, 정확도:sim
			
			//GET /v1/search/blog.json?query=%EB%A6%AC%EB%B7%B0&display=10&start=1&sort=sim
			String queryString = "?query="+text+"&display="+inVO.getPageSize()+"&start="+inVO.getPageNo()+"&sort="+sort;
			
			LOG.debug("blog url: "+blog);
			LOG.debug("queryString: "+queryString);
			
			switch (inVO.getSearchDiv()) {
			case "10":
				apiURL = blog+queryString;
				break;
			case "20":
				apiURL = news+queryString;
				break;	
			case "30":
				apiURL = book+queryString;
				break;					

			}
			LOG.debug("apiURL: "+apiURL);
			
			
			Map<String, String> requestHeaders=new HashMap<String, String>();
			requestHeaders.put("X-Naver-Client-Id", CLIENT_ID);
			requestHeaders.put("X-Naver-Client-Secret", CLIENT_SECRET);
			
			responseBody = get(apiURL, requestHeaders);
			LOG.debug("┌───────────────────────────────────┐");
			LOG.debug("│ responseBody                      │\n"+responseBody);
			LOG.debug("└───────────────────────────────────┘");				
			
		}catch(IOException e) {
			LOG.debug("┌───────────────────────────────────┐");
			LOG.debug("│ updateReadCnt                     │"+e.getMessage());
			LOG.debug("└───────────────────────────────────┘");					
		}
		
		
		return responseBody;
	}
	
	/**
	 * naver search 요청
	 * @param apiUrl
	 * @param requestHeaders
	 * @return String(json)
	 */
	private String get(String apiUrl, Map<String, String> requestHeaders) {
		HttpURLConnection  con = connect(apiUrl);
		String resultStr = "";
		try {
			con.setRequestMethod("GET");
			
			for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}
			
			
			int  responseCode= con.getResponseCode();
			LOG.debug("│ responseCode                     │"+responseCode);
			
			if(responseCode == HttpURLConnection.HTTP_OK) {//접속 성공
				resultStr = readBody(con.getInputStream());
			}else {//접속 실패
				return readBody(con.getErrorStream());
			}
			
		}catch(IOException e) {
			throw new RuntimeException("API 응답을 읽는데 실패!", e);
		}finally {
			con.disconnect();
		}
		return resultStr;
	}
	
	/**
	 * InputStream
	 * @param body
	 * @return String(json)
	 */
	private String readBody(InputStream body) {
		String retStr = "";
		InputStreamReader  streamReader=new InputStreamReader(body);
		
		try(BufferedReader  lineReader=new BufferedReader(streamReader)){
			StringBuilder  sb=new StringBuilder(2000);
			
			String line = "";
			while( (line=lineReader.readLine()) !=null) {
				sb.append(line);
			}
			
			retStr = sb.toString();
		}catch(IOException e) {
			throw new RuntimeException("API 응답을 읽는데 실패!", e);
		}
		
		return retStr;
	}
	
	
	/**
	 * naver접근 HttpURLConnection객체 생성
	 * @param apiUrl
	 * @return HttpURLConnection
	 */
	private HttpURLConnection connect(String apiUrl) {
		try {
			URL  url =new URL(apiUrl);
			return (HttpURLConnection)url.openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다.:"+apiUrl,e);
		} catch (IOException e) {
			throw new RuntimeException("연결 실패.:"+apiUrl,e);
		}
	}

}

















