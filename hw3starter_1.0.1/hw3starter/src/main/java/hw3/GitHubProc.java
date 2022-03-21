package hw3;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GitHubProc {
  public static long wordsInComment(String commentBody, String word) {
    String[] seperatedWords = Util.getWords(commentBody);
    long wordCount = Arrays.stream(seperatedWords).filter(x -> x.equals(word)).count();
    return wordCount;
  }

  public static boolean commentContainsUrl(String commentBody) {
    String[] seperatedWords = Util.getWords(commentBody);
    boolean containsHttp = Arrays.stream(seperatedWords).anyMatch(x -> x.contains("http://"));
    boolean containsHttps = Arrays.stream(seperatedWords).anyMatch(x -> x.contains("https://"));
    return (containsHttp || containsHttps);
  }

  public static Long getWordCount(Stream<GitHubComment> stream, String word) {

    long totalWordsCount = stream.mapToLong(x -> wordsInComment(x.body(), word)).sum();

    return totalWordsCount;
  }

  public static Map<String, Long> getPerProjectCount(Stream<GitHubComment> stream) {
    Map<String, Long> urlToCommentCount = stream
        .collect(Collectors.groupingBy(x -> Util.getProject(x), Collectors.counting()));
    return urlToCommentCount;
  }

  public static Map<String, Long> getAuthorActivity(Stream<GitHubComment> stream) {
    Map<String, Long> authorToCommentCount = stream
        .collect(Collectors.groupingBy(x -> x.author(), Collectors.counting()));

    return authorToCommentCount;
  }

  public static Map<String, Long> getCommentUrlAuthorCount(Stream<GitHubComment> stream) {

    Stream<GitHubComment> onlyUrlStream = filterCommentsWithUrl(stream);

    Map<String, Long> authorToCommentCount = onlyUrlStream
        .collect(Collectors.groupingBy(x -> x.author(), Collectors.counting()));
    return authorToCommentCount;
  }

  public static Stream<GitHubComment> filterCommentsWithUrl(Stream<GitHubComment> comments) {
    // return a stream that contains a URL in the body: "http:" or "https:"

    return comments.filter(x -> commentContainsUrl(x.body()));
  }

  public static long bodyLength(String commentBody) {
    String[] seperatedWords = Util.getWords(commentBody);
    return seperatedWords.length;

  }

  public static Map<String, Double> getAuthorAverageVerbosity(Stream<GitHubComment> stream) {
    Map<String, Double> averageVerbosity = stream
        .collect(Collectors.groupingBy(x -> x.author(), 
        Collectors.averagingDouble(x -> bodyLength(x.body()))));

    return averageVerbosity;
  }

  public static Map<String, Map<String, Long>> 
  getAuthorWordCountPerProject(Stream<GitHubComment> stream, String word) {
    Map<String, Map<String, Long>> authorWordsPerProject = 
    stream.collect(Collectors.groupingBy(x -> Util.getProject(x),
        Collectors.groupingBy(x -> x.author(), 
        Collectors.summingLong(x -> wordsInComment(x.body(), word)))));

    return authorWordsPerProject;
  }
}
