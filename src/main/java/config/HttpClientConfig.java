package config;

import java.net.http.HttpClient;
import java.time.Duration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClientConfig {

  @Bean
  public HttpClient httpClient() {
    return HttpClient.newBuilder().version(java.net.http.HttpClient.Version.HTTP_1_1).connectTimeout(Duration.ofSeconds(10)).build();
  }
}
