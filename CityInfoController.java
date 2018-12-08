package com.example.demo1;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class CityInfoController {
	
	@Value("${weatherservice.base_url}")
	private String weatherServcieUrl;
	 HashMap< String, String> cache=new HashMap<>();
	 
	private RestTemplate restTemp= new RestTemplate();
	@GetMapping("/weatherinfo/{city}")
	@HystrixCommand(fallbackMethod="getWeatherInfoFromCache")
	public String getWeatherInfo(@PathVariable String city) {
		System.out.println("--- calling from server");
		String url=weatherServcieUrl +"/"+ city;
		System.out.println("url :"+url);
		ResponseEntity<String> responseEntity=restTemp.getForEntity(url, String.class);
		String output=responseEntity.getBody();
		cache.put(city,output);
		return output;
	}
	
	public String getWeatherInfoFromCache(String City) {
		
		System.out.println("----calling from Cache!");
		
		String message="From Cache get required data of Weather";
		
		if(message==null) {
			message=" Opps! WeatherService is down";
		}else {
			message ="From Cache " +message;
		}
		
		return message;
		
	}

}
