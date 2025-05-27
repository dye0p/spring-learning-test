package cholog;

import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;

public class TodoClientWithRestClient {

    private final RestClient restClient;

    public TodoClientWithRestClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<Todo> getTodos() {
        return restClient.get().uri("/todos").retrieve().body(new ParameterizedTypeReference<>() {
        });
        // TODO: restClient의 get 메서드를 사용하여 요청을 보내고 결과를 Todo 리스트로 변환하여 반환
    }

    public Todo getTodoById(Long id) {
        // TODO: restClient의 get 메서드를 사용하여 요청을 보내고 결과를 Todo로 변환하여 반환
        // TODO: 존재하지 않는 id로 요청을 보낼 경우 TodoException.NotFound 예외를 던짐
        return restClient.get().uri("/todos/{id}", id).retrieve().onStatus(HttpStatusCode::is4xxClientError,
                (request, response) -> {
                    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                        throw new TodoException.NotFound(id);
                    }
                    throw new TodoException("Failed to get todo with id: " + id);
                }).body(Todo.class);
    }
}
