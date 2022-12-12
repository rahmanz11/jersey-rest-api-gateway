package rest.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import rest.api.payload.Credentials;
import rest.api.payload.User;

@Path("/")
public class RESTServer {

    @POST
    @Path("/login")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_JSON)
    public String login(Credentials credential) {
        com.sun.jersey.api.client.config.ClientConfig clientConfig = new com.sun.jersey.api.client.config.DefaultClientConfig();
		clientConfig.getFeatures().put(com.sun.jersey.api.json.JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		Client client = Client.create(clientConfig);

		String target = "http://localhost:8080/auth-service/auth/users";
		
		WebResource webResource = client.resource(target)
        .queryParam("name", credential.getNickname())
        .queryParam("password", credential.getPassword());
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		String output = response.getEntity(String.class);
		return output;
    }

    @POST
    @Path("/register")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_JSON)
    public String register(Credentials credential) {
        com.sun.jersey.api.client.config.ClientConfig clientConfig = new com.sun.jersey.api.client.config.DefaultClientConfig();
		clientConfig.getFeatures().put(com.sun.jersey.api.json.JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		Client client = Client.create(clientConfig);

		String target = "http://localhost:8080/auth-service/auth/users";
		
		WebResource webResource = client.resource(target);
		User input = new User();
        input.setName(credential.getNickname());
        input.setPassword(credential.getPassword());
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, input);
		String output = response.getEntity(String.class);
        return output;
    }
}
