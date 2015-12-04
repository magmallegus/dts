package test.spring.series.mvc.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.spring.series.mvc.rest.model.Player;

public class TestSpringMVCRest {

	public static void main(String args[]) {
		RestTemplate restTemplate = new RestTemplate();

		System.out.println("Executing and Testing method one using RestTemplate");
		String strIplTeamName = restTemplate.getForObject("http://localhost:8080/SpringMVC-REST-4/cricket/getiplteam/MI", String.class);
		System.out.println(strIplTeamName);

		HttpHeaders headers1 = new HttpHeaders();
		List<MediaType> ls = new ArrayList<MediaType>();
		ls.add(MediaType.APPLICATION_JSON);
		headers1.setAccept(ls);
		HttpEntity<String> request1= new HttpEntity<String>(headers1);

		System.out.println("\n\nExecuting and Testing method two using RestTemplate");
		Player player = restTemplate.getForObject("http://localhost:8080/SpringMVC-REST-4/cricket/getplayer/1", Player.class, request1);
		System.out.println(player.getId() + "\t" + player.getName() + "\t" + player.getMatches());

		System.out.println("\n\nExecuting and Testing method third using RestTemplate");
		String requestType = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><player><id>1</id><name>Sachin Tendulkar</name><matches>200</matches></player>";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		HttpEntity<String> request= new HttpEntity<String>(requestType, headers);
		String strPlayerId = restTemplate.postForObject("http://localhost:8080/SpringMVC-REST-4/cricket/createplayer", request, String.class);
		System.out.println(strPlayerId);
	}
}