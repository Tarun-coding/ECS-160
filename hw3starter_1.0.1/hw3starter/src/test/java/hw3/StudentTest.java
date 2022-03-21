package hw3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

// If you are using VS code, you should see a run button
//  on the left to run these tests. You can also go to
//  View>Testing to view the tests and run from there.

public class StudentTest {

  @Test
  public void shouldReadEmptyStringsWithAsZeroWords() {
    var tinyTest = Stream.of(new GitHubComment("", "", "", " ", "", " "), 
    new GitHubComment("", "", "", " ", "", "a"));

    var testMap = GitHubProc.getAuthorAverageVerbosity(tinyTest);
    var expectedMap = new HashMap<String, Double>();
    expectedMap.put(" ", 0.5);
    assertEquals(expectedMap, testMap);

   
  }
}