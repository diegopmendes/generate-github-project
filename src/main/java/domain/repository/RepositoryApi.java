package domain.repository;

import com.google.gson.Gson;
import domain.GithubApiRequest;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import model.Repository;
import org.springframework.http.HttpStatus;

public class RepositoryApi extends GithubApiRequest {

  private static final String USER_REPO_PATH = "/user/repos";

  private static final String REPO_PATH = "/repos";

  private static final String REPOSITORY_TEMPLATE_NAME = "template_padrao_diego";

  private static final String OWNER_TEMPLATE_NAME = "diegopmendes";

  public List<Repository> getRepositories() throws IOException, InterruptedException {
    HttpRequest httpRequest = createHttpRequestBuilder(USER_REPO_PATH).GET().build();
    HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    return new Gson().fromJson(response.body(), type(Repository.class));
  }

  public boolean createRepository(final String repositoryName) throws IOException, InterruptedException {
    RepositoryRequest repositoryRequest = new RepositoryRequest();
    repositoryRequest.setName(repositoryName);
    HttpRequest httpRequest =
        createHttpRequestBuilder(USER_REPO_PATH).POST(HttpRequest.BodyPublishers.ofString(new Gson().toJson(repositoryRequest))).build();
    HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    return response.statusCode() == HttpStatus.CREATED.value();
  }

  public boolean createRepositoryFromTemplate(final String repositoryName) throws IOException, InterruptedException {
    final String uriCreateRepositoryFromTemplate = "/%s/%s/generate";
    RepositoryRequest repositoryRequest = new RepositoryRequest();
    repositoryRequest.setName(repositoryName);
    repositoryRequest.setOwner(OWNER_TEMPLATE_NAME);
    HttpRequest httpRequest =
        createHttpRequestBuilder(REPO_PATH + String.format(uriCreateRepositoryFromTemplate, OWNER_TEMPLATE_NAME, REPOSITORY_TEMPLATE_NAME))
            .POST(HttpRequest.BodyPublishers.ofString(new Gson().toJson(repositoryRequest))).build();
    HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    return response.statusCode() == HttpStatus.CREATED.value();
  }

  public boolean deleteRepository(final String ownerRepository, final String repositoryName) throws IOException, InterruptedException {
    final String uriDelete = "/%s/%s";
    RepositoryRequest repositoryRequest = new RepositoryRequest();
    repositoryRequest.setName(repositoryName);
    HttpRequest httpRequest =
        createHttpRequestBuilder(REPO_PATH + String.format(uriDelete, ownerRepository, repositoryName)).DELETE().build();
    HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    return response.statusCode() == HttpStatus.NO_CONTENT.value();
  }
}
