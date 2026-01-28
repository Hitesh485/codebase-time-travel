package com.codebase.time_traveller.testexecution;

import org.springframework.stereotype.Service;

@Service
public class TestExecutionService {

    public boolean runTests() {
        try {
            Process process = new ProcessBuilder("mvn", "test")
                    .inheritIO()
                    .start();

            int exitCode = process.waitFor();
            return exitCode == 0;
        } catch (Exception e) {
            return false;
        }
    }
}
