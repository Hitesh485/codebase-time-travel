import com.codebase.time_traveller.git.GitCheckoutService;
import com.codebase.time_traveller.testexecution.TestExecutionFacade;
import com.codebase.time_traveller.testexecution.TestExecutionService;
import com.codebase.time_traveller.testexecution.TestResult;
import org.junit.jupiter.api.Test;

import java.util.List;

public class Day12ExecutionTest {
    @Test
    void runTestsForEachCommit() throws Exception {

        // 1️⃣ List commits in chronological order (old → new)
        List<String> commits = List.of(
                "1463b42d73d36f7bb06cde7e47c47afa9af26331",
                "f431baf098e48b69b7653a72a1ddd0b983c6990e",
                "579a79a5e0c080b65ecc54a43c3731b8c8156c60",
                "1038b3c5163f3353fe619078e59bfe58b70f07b2",
                "5de91ff2453f9958472376a88703e7d774e7d4a7"
        );

        // 2️⃣ Initialize services
        GitCheckoutService checkoutService =
                new GitCheckoutService(
                        System.getProperty("user.dir") // project root
                );

        TestExecutionFacade facade =
                new TestExecutionFacade(
                        checkoutService,
                        new TestExecutionService()
                );

        // 3️⃣ Execute tests per commit
        List<TestResult> results = facade.executeForCommits(commits);

        // 4️⃣ Print results (temporary console output)
        System.out.println("\n===== DAY 12 TEST RESULTS =====");
        results.forEach(result ->
                System.out.println(
                        "Commit " + result.getCommitId() +
                                " → " + (result.isPassed() ? "PASS" : "FAIL")
                )
        );
        System.out.println("================================");
    }
}
