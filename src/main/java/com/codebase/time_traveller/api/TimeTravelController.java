package com.codebase.time_traveller.api;

import com.codebase.time_traveller.api.dto.TimeTravelRequest;
import com.codebase.time_traveller.api.dto.TimeTravelResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/timetravel")
public class TimeTravelController {
    private final TimeTravelFacade facade;

    public TimeTravelController(TimeTravelFacade facade) {
        this.facade = facade;
    }

    @PostMapping("/analyze")
    public TimeTravelResponse analyze(@RequestBody TimeTravelRequest request)
            throws Exception {

        // Temporary: commits hardcoded (later dynamic)
        List<String> commits = List.of(
                "1463b42d",
                "f431baf0",
                "579a79a5"
        );

        return facade.analyze(commits);
    }
}
