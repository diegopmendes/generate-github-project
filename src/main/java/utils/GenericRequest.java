//package utils;
//
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//import java.io.IOException;
//import java.lang.reflect.Type;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.util.ArrayList;
//import java.util.List;
//
//public class GenericRequest<T> {
//
//  public List<T> executeRequestList(final HttpClient httpClient, final String githubToken, final URI uri) throws IOException, InterruptedException {
//    HttpRequest request = HttpRequest.newBuilder()
//        .GET()
//        .uri(uri)
//        .setHeader("Accept", "application/vnd.github+json")
//        .setHeader("Authorization", "token " + githubToken)
//        .build();
//    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//    return new Gson().fromJson(response.body(), type());
//  }
//
//  private Type type(final Class<?> typeArgument) {
//    return TypeToken.getParameterized(ArrayList.class, typeArgument).getType();
//  }
//}
