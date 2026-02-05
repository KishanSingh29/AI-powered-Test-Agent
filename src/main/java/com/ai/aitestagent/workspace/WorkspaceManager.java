package com.ai.aitestagent.workspace;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Component
public class WorkspaceManager {

    public Path createWorkspace() {
        try {
            String folderName = "workspace-" + UUID.randomUUID();
            Path workspacePath = Files.createTempDirectory(folderName);

            createProjectStructure(workspacePath);

            return workspacePath;
        } catch (IOException e) {
            throw new RuntimeException("Failed to create workspace", e);
        }
    }

    private void createProjectStructure(Path workspacePath) throws IOException {
        Files.createDirectories(workspacePath.resolve("src/main/java"));
        Files.createDirectories(workspacePath.resolve("src/test/java"));
    }
}
