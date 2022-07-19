import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import config.HttpClientConfig;
import domain.GithubApiRequest;
import domain.repository.RepositoryApi;
import java.io.IOException;
import java.util.List;
import model.Repository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:test-application.properties")
@ContextConfiguration(classes = {HttpClientConfig.class, GithubApiRequest.class})
public class GithubApiRequestTestIT {

  @SpyBean
  private RepositoryApi repositoryApi;

  @Test
  void listRepositories() {
    try {
      List<Repository> repositoryList = repositoryApi.getRepositories();
      assertFalse(repositoryList.isEmpty());
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void createRepository() {
    try {
      boolean created = repositoryApi.createRepository("gtest-project-generate");
      assertTrue(created);
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void createRepositoryFromTemplate() {
    try {
      boolean created = repositoryApi.createRepositoryFromTemplate("diego-repo-from-template");
      assertTrue(created);
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void deleteRepository() {
    try {
      boolean deleted = repositoryApi.deleteRepository("diegopmendes", "teste-diego-criado");
      assertTrue(deleted);
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}