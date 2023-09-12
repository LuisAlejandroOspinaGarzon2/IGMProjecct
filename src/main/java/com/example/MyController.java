package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit; 
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class MyController {

    @GetMapping("/generate-html")
    public String generateHTML() {
        // Simulate generating HTML content in parallel
        int numberOfTasks = Runtime.getRuntime().availableProcessors(); // Use available CPU cores
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfTasks);

        List<CompletableFuture<String>> tasks = new ArrayList<>();
        for (int i = 0; i < numberOfTasks; i++) {
            CompletableFuture<String> task = CompletableFuture.supplyAsync(() -> generateHTMLTask(), executorService);
            tasks.add(task);
        }

        // Wait for all tasks to complete and combine results
        CompletableFuture<Void> allOf = CompletableFuture.allOf(tasks.toArray(new CompletableFuture[0]));

        try {
            allOf.get(); // Wait for all tasks to complete
        } catch (Exception e) {
            e.printStackTrace();
        }

        executorService.shutdown();
        
        // Combine HTML content from all tasks
        String combinedHTML = tasks.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.joining("\n"));

        return combinedHTML;
    }

    private String generateHTMLTask() {
        // Simulate HTML generation task (replace with your logic)
        String html = "<div>This is some generated content.</div>";
        // Simulate a delay (e.g., 1-2 seconds) to mimic real work
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return html;
    }

}
