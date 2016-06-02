package org.luisyang.integration.webservice.basic.server;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService
public class SayHello {

	private static final String SALUTATION = "Hello";

	@WebMethod
	public String getGreeting(String name) {
		return SALUTATION + " " + name;
	}

	public static void main(String[] args) {
		// create and publish an endPoint
		SayHello hello = new SayHello();
		Endpoint endPoint = Endpoint.publish("http://localhost:8080/helloService", hello);
	}
}
