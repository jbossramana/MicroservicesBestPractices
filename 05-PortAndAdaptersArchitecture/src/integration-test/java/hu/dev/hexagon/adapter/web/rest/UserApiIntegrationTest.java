package hu.dev.hexagon.adapter.web.rest;

import demo.hexagon.PortAndAdaptersApplication;
import demo.hexagon.adapter.web.rest.exception.handler.ApiError;
import hu.dev.hexagon.rest.model.ModifyUserDTO;
import hu.dev.hexagon.rest.model.SubmitNewUserDTO;
import hu.dev.hexagon.rest.model.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PortAndAdaptersApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserApiIntegrationTest {

    private final String API_URL = "/api/v1/user";

    @LocalServerPort
    private int port;

    private TestRestTemplate testRestTemplate;
    private HttpHeaders httpHeaders;

    @BeforeEach
    void before() {
        testRestTemplate = new TestRestTemplate();
        httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    }

    @Test
    void testRetrieveUsers() {
        final HttpEntity<String> entity = new HttpEntity<>(null, httpHeaders);

        final ResponseEntity<List<UserDTO>> usersResponse = testRestTemplate.exchange(concatAddressWithUriAndPort(API_URL),
                HttpMethod.GET, entity, new ParameterizedTypeReference<>() {
                });
        final HttpStatus statusCode = usersResponse.getStatusCode();
        final List<UserDTO> users = usersResponse.getBody();

        assertThat(statusCode).isEqualTo(HttpStatus.OK);
        assertThat(users).hasSize(5);
    }

    @Test
    void testRetrieveUserByUserName() {
        final String userName = "Tillman";
        final HttpEntity<String> entity = new HttpEntity<>(null, new HttpHeaders());

        final ResponseEntity<UserDTO> usersResponse = testRestTemplate.exchange(concatAddressWithUriAndPort(API_URL + "/" + userName),
                HttpMethod.GET, entity, UserDTO.class);

        final HttpStatus statusCode = usersResponse.getStatusCode();
        final UserDTO user = usersResponse.getBody();

        assertThat(statusCode).isEqualTo(HttpStatus.OK);
        assertThat(user.getUserName()).isEqualTo(userName);
        assertThat(user.getFirstName()).isEqualTo("Winston");
        assertThat(user.getLastName()).isEqualTo("Tillman");
        assertThat(user.getAge()).isEqualTo(54);
    }

    @Test
    void testUserNotFound() {
        final String userName = "test";
        final HttpEntity<String> entity = new HttpEntity<>(null, new HttpHeaders());

        final ResponseEntity<ApiError> apiErrorResponseEntity = testRestTemplate.exchange(concatAddressWithUriAndPort(API_URL + "/" + userName),
                HttpMethod.GET, entity, ApiError.class);

        final HttpStatus statusCode = apiErrorResponseEntity.getStatusCode();
        final ApiError apiError = apiErrorResponseEntity.getBody();

        assertThat(statusCode).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(apiError.getMessage()).isEqualTo("User was not found with the given username: " + userName);
    }


    @Test
    void testUserAlreadyTaken() {
        final SubmitNewUserDTO request = new SubmitNewUserDTO()
                .userName("Tillman")
                .firstName("test")
                .lastName("test")
                .age(25);
        final HttpEntity<SubmitNewUserDTO> entity = new HttpEntity<>(request, new HttpHeaders());

        final ResponseEntity<ApiError> apiErrorResponseEntity = testRestTemplate.exchange(concatAddressWithUriAndPort(API_URL),
                HttpMethod.POST, entity, ApiError.class);

        final HttpStatus statusCode = apiErrorResponseEntity.getStatusCode();
        final ApiError apiError = apiErrorResponseEntity.getBody();

        assertThat(statusCode).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(apiError.getMessage()).isEqualTo("Username: " + request.getUserName() + " is already taken!");
    }

    @Test
    void testUserOperations() {
        // query users
        ResponseEntity<List<UserDTO>> usersResponse = testRestTemplate.exchange(concatAddressWithUriAndPort(API_URL),
                HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });
        HttpStatus statusCode = usersResponse.getStatusCode();
        List<UserDTO> users = usersResponse.getBody();

        assertThat(statusCode).isEqualTo(HttpStatus.OK);
        assertThat(users).hasSize(5);

        // submit 2 user
        final String userName = "morpheus";
        final String userName2 = "testuser";

        final SubmitNewUserDTO submitRequest = new SubmitNewUserDTO()
                .userName(userName)
                .firstName("Alexander")
                .lastName("Ackerman")
                .age(25);

        final SubmitNewUserDTO submitRequest2 = new SubmitNewUserDTO()
                .userName(userName2)
                .firstName("Test")
                .lastName("Test")
                .age(25);

        final HttpEntity<SubmitNewUserDTO> submitRequestEntity = new HttpEntity<>(submitRequest, new HttpHeaders());
        final ResponseEntity<UserDTO> submitResponse = testRestTemplate.exchange(concatAddressWithUriAndPort(API_URL),
                HttpMethod.POST, submitRequestEntity, UserDTO.class);

        statusCode = submitResponse.getStatusCode();
        final UserDTO submittedUser = submitResponse.getBody();

        assertThat(statusCode).isEqualTo(HttpStatus.CREATED);
        assertThat(submittedUser.getUserName()).isEqualTo(submitRequest.getUserName());
        assertThat(submittedUser.getFirstName()).isEqualTo(submitRequest.getFirstName());
        assertThat(submittedUser.getLastName()).isEqualTo(submitRequest.getLastName());
        assertThat(submittedUser.getAge()).isEqualTo(submitRequest.getAge());

        final HttpEntity<SubmitNewUserDTO> submitRequestEntity2 = new HttpEntity<>(submitRequest2, new HttpHeaders());
        testRestTemplate.exchange(concatAddressWithUriAndPort(API_URL),
                HttpMethod.POST, submitRequestEntity2, UserDTO.class);

        // get user by username
        final ResponseEntity<UserDTO> getUserResponse = testRestTemplate.exchange(concatAddressWithUriAndPort(API_URL + "/" + userName),
                HttpMethod.GET, null, UserDTO.class);

        statusCode = getUserResponse.getStatusCode();
        final UserDTO user = getUserResponse.getBody();

        assertThat(statusCode).isEqualTo(HttpStatus.OK);
        assertThat(user.getUserName()).isEqualTo(userName);
        assertThat(user.getFirstName()).isEqualTo(submitRequest.getFirstName());
        assertThat(user.getLastName()).isEqualTo(submitRequest.getLastName());
        assertThat(user.getAge()).isEqualTo(submitRequest.getAge());

        // query users
        usersResponse = testRestTemplate.exchange(concatAddressWithUriAndPort(API_URL),
                HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });
        statusCode = usersResponse.getStatusCode();
        users = usersResponse.getBody();

        assertThat(statusCode).isEqualTo(HttpStatus.OK);
        assertThat(users).hasSize(7);

        // modify user
        final ModifyUserDTO modifyRequest = new ModifyUserDTO()
                .firstName("Lorem")
                .lastName("Ipsum")
                .age(60);
        final HttpEntity<ModifyUserDTO> modifyRequestEntity = new HttpEntity<>(modifyRequest, new HttpHeaders());
        final ResponseEntity<UserDTO> modifyResponse = testRestTemplate.exchange(concatAddressWithUriAndPort(API_URL + "/" + userName),
                HttpMethod.PATCH, modifyRequestEntity, UserDTO.class);

        statusCode = modifyResponse.getStatusCode();
        final UserDTO modifiedUser = modifyResponse.getBody();

        assertThat(statusCode).isEqualTo(HttpStatus.OK);
        assertThat(modifiedUser.getUserName()).isEqualTo(userName);
        assertThat(modifiedUser.getFirstName()).isEqualTo(modifyRequest.getFirstName());
        assertThat(modifiedUser.getLastName()).isEqualTo(modifyRequest.getLastName());
        assertThat(modifiedUser.getAge()).isEqualTo(modifyRequest.getAge());

        // delete user
        final ResponseEntity<Void> deletedResponseEntity = testRestTemplate.exchange(concatAddressWithUriAndPort(API_URL + "/" + userName),
                HttpMethod.DELETE, null, Void.class);

        statusCode = deletedResponseEntity.getStatusCode();
        assertThat(statusCode).isEqualTo(HttpStatus.NO_CONTENT);

        // query users
        usersResponse = testRestTemplate.exchange(concatAddressWithUriAndPort(API_URL),
                HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });
        statusCode = usersResponse.getStatusCode();
        users = usersResponse.getBody();

        assertThat(statusCode).isEqualTo(HttpStatus.OK);
        assertThat(users).hasSize(6);
    }

    private String concatAddressWithUriAndPort(final String uri) {
        return "http://localhost:" + port + uri;
    }
}
