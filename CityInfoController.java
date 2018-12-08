package com.example.demo1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CityInfoController {
	
	@Value("$weatherservice.base_url")
	private String weatherServcieUrl;
	
	private RestTemplate restTemp= new RestTemplate();
	@GetMapping("/weatherinfo/{city}")
	public String getWeatherInfo(@PathVariable String city) {
		String url=weatherServcieUrl+"/"+city;
		ResponseEntity<String> responseEntity=restTemp.getForEntity(url, String.class);
		return responseEntity.getBody();
	}

}
