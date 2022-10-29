package br.com.activesoft.cadastro;

import javax.annotation.security.RunAs;

import org.apache.tomcat.util.http.parser.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CadastroApplicationTests {

	@Value("${server.port}")
	private int port;
	@Autowired
	RestTemplate restTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	void callGetAllUsers(){

		ResponseEntity<String> response =
				restTemplate.getForEntity("http://localhost:8080/users", String.class);

		assertNotNull(response.getBody());

	}

}
