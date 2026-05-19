package com.report_engine.api.controller;

import com.report_engine.api.infrastructure.TaskTracker;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskStatusController {

    private final TaskTracker taskTracker;


}
