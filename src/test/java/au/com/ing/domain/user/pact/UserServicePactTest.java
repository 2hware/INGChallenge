package au.com.ing.domain.user.pact;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import au.com.ing.domain.customers.model.User;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class UserServicePactTest {

    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("test_provider", "localhost", 9999, this);

    @Pact(consumer = "getuserdetails_consumer")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        Map<String, String> getheaders = new HashMap<>();
        getheaders.put("Content-Type", "application/json");
        getheaders.put("Authentication","Basic aW5ndXNlcjpwYXNzd29yZA==");
        return builder
                .given("test GET")
                .uponReceiving("GET REQUEST")
                .path("/api/userdetails")
                .method("GET")
                .body("{\"userID\": \"1\"}")
                .willRespondWith()
                .status(200)
                .headers(getheaders)
                .body("{\n" +
                        "    \"id\": 1,\n" +
                        "    \"title\": \"Male\",\n" +
                        "    \"firstName\": \"Mr\",\n" +
                        "    \"lastName\": \"Hakak\",\n" +
                        "    \"gender\": \"Hussein\",\n" +
                        "    \"address\": {\n" +
                        "        \"id\": 1,\n" +
                        "        \"street\": \"2000\",\n" +
                        "        \"city\": \"1 F ST\",\n" +
                        "        \"state\": \"NSW\",\n" +
                        "        \"postcode\": \"Sydney\"\n" +
                        "    }\n" +
                        "}")
                .given("test PUT")
                .uponReceiving("PUT REQUEST")
                .method("PUT")
                .headers(getheaders)
                .body("{\n" +
                        "    \"id\": 1,\n" +
                        "    \"title\": \"Male\",\n" +
                        "    \"firstName\": \"Mr\",\n" +
                        "    \"lastName\": \"Hakak\",\n" +
                        "    \"gender\": \"Hussein\",\n" +
                        "    \"address\": {\n" +
                        "        \"id\": 1,\n" +
                        "        \"street\": \"2000\",\n" +
                        "        \"city\": \"1 F ST\",\n" +
                        "        \"state\": \"NSW\",\n" +
                        "        \"postcode\": \"Sydney\"\n" +
                        "    }\n" +
                        "}")
                .path("/api/updateuserdetail")
                .willRespondWith()
                .status(201)
                .toPact();
    }

    @Test
    @PactVerification()
    public void givenGet_whenGetUserDetailsRequest_shouldReturn200WithProperHeaderAndBody() {
        // when
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authentication","Basic aW5ndXNlcjpwYXNzd29yZA==");

        ResponseEntity<User> response = new RestTemplate().exchange(mockProvider.getUrl() + "/api/userdetails", HttpMethod.GET, new HttpEntity<>("{\"userID\": \"1\"}", httpHeaders), User.class);
        assertThat(response.getStatusCode().value()).isEqualTo(200);
    }

}