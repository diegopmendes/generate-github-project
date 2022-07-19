package domain;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GithubApiRequest {

  public static final String ROOT_PATH = "https://api.github.com";

  @Autowired
  protected HttpClient httpClient;

  @Value("${github.token}")
  private String githubToken;

  public HttpRequest.Builder createHttpRequestBuilder(final String uriPath) {
    return HttpRequest.newBuilder()
        .uri(URI.create(ROOT_PATH + uriPath))
        .setHeader("Accept", "application/vnd.github+json")
        .setHeader("Authorization", "token " + githubToken);
  }

  protected Type type(final Class<?> typeArgument) {
    return TypeToken.getParameterized(ArrayList.class, typeArgument).getType();
  }
}
