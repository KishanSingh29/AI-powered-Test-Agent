package com.ai.aitestagent.workspace;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Files;


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

    public void saveSourceCode(Path workspacePath, String sourceCode) {
        try {
            Path sourceDir = workspacePath.resolve("src/main/java");
            Path javaFile = sourceDir.resolve("UserCode.java");

            Files.writeString(javaFile, sourceCode);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save source code", e);
        }
    }

    public void copyPomToWorkspace(Path workspacePath) {
        try {
            Path sourcePom = Path.of("pom.xml");
            Path targetPom = workspacePath.resolve("pom.xml");

            Files.copy(sourcePom, targetPom);
        } catch (IOException e) {
            throw new RuntimeException("Failed to copy pom.xml to workspace", e);
        }
    }


}
