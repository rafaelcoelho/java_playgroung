package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.Assert;
import org.junit.Test;


public class ClientTest {
	
	@Test
	public void testConnectionAgainstServer() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://www.mocky.io");
		
		String result = target.path("/v2/52aaf5deee7ba8c70329fb7d").request().get(String.class);
		
		Assert.assertTrue(result.contains("<rua>Rua Vergueiro 3185"));
	}
}
