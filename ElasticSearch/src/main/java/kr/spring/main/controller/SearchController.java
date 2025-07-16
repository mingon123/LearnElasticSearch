package kr.spring.main.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpHost;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class SearchController {
	
	@GetMapping("get.do")
	public String get() throws IOException {
		RestClient restClient = RestClient.builder(
				new HttpHost("localhost",9200,"http"),
				new HttpHost("localhost",9201,"http")).build();
		Request request = new Request("GET", "/movie_search/_search?q=prdtYear:2018");
		Response response = restClient.performRequest(request);
		
		restClient.close();
		
		return EntityUtils.toString(response.getEntity());
	}
	
	@GetMapping("/get2.do")
	public String get2()throws IOException{
		RestClient restClient = RestClient.builder(
				new HttpHost("localhost",9200,"http"),
				new HttpHost("localhost",9201,"http")).build();
		Request request = new Request("GET","/movie_search/_search?q=movieNm:럭키"); // movieNm 변경하면서 데이터 확인
		Response response = restClient.performRequest(request);
		
		restClient.close();
		
		return EntityUtils.toString(response.getEntity());
	}
	
	// 등록
	@GetMapping("/put.do")
	public String put() throws IOException{
		RestClient restClient = RestClient.builder(
				new HttpHost("localhost",9200,"http"),
				new HttpHost("localhost",9201,"http")).build();
		
		Request request = new Request("PUT","/movie_search/_doc/12");
		Map<String,String> map = new HashMap<String, String>();
		map.put("movieCd", "12");
		map.put("movieNm", "파묘");
		map.put("movieNmEn", "영화");
		map.put("prdtYear", "2024");
		map.put("openDt", "2024-12-22");
		map.put("typeNm", "장편");
		map.put("prdtStatNm", "기타");
		map.put("nationAlt", "한국");
		map.put("genreAlt", "미스터리");
		map.put("repNationNm", "한국");
		map.put("repGenreNm", "미스터리");
		
		ObjectMapper mapper = new ObjectMapper();
		String body = mapper.writeValueAsString(map);
		
		request.setJsonEntity(body);
		
		Response response = restClient.performRequest(request);
		
		restClient.close();		
		
		return EntityUtils.toString(response.getEntity());
	}
	
	// 수정
	@GetMapping("/post.do")
	public String post() throws IOException{
		RestClient restClient = RestClient.builder(
				new HttpHost("localhost",9200,"http"),
				new HttpHost("localhost",9201,"http")).build();
		
		Request request = new Request("PUT","/movie_search/_doc/1");
		Map<String,String> map = new HashMap<String, String>();
		map.put("movieCd", "1");
		map.put("movieNm", "럭키");
		map.put("movieNmEn", "Lucky");
		map.put("prdtYear", "2018");
		map.put("openDt", "2018-01-23");
		map.put("typeNm", "장편");
		map.put("prdtStatNm", "기타");
		map.put("nationAlt", "한국");
		map.put("genreAlt", "코미디");
		map.put("repNationNm", "한국");
		map.put("repGenreNm", "코미디");
		
		ObjectMapper mapper = new ObjectMapper();
		String body = mapper.writeValueAsString(map);
		
		request.setJsonEntity(body);
		
		Response response = restClient.performRequest(request);
		
		restClient.close();		
		
		return EntityUtils.toString(response.getEntity());
	}
	
	
	// 삭제
	@GetMapping("/delete.do")
	public String delete() throws IOException{
		RestClient restClient = RestClient.builder(
				new HttpHost("localhost",9200,"http"),
				new HttpHost("localhost",9201,"http")).build();
		
		Request request = new Request("delete", "/movie_search/_doc/1"); // 삭제
		Response response = restClient.performRequest(request); // JSON 문자열 호출(데이터 보여줌)
		
		restClient.close();
		
		return EntityUtils.toString(response.getEntity());
	}
}

// 경로를 GetMapping URL에 맞춰서 (ex) localhost:8080/get.do ) 로 호출하면 데이터를 읽어옴(설정 필요X)

