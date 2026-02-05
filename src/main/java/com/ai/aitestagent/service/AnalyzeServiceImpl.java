package com.ai.aitestagent.service;

import com.ai.aitestagent.dto.AnalyzeRequest;
import com.ai.aitestagent.workspace.WorkspaceManager;
import org.springframework.stereotype.Service;
import com.ai.aitestagent.executor.CommandExecutor;


import java.nio.file.Path;

@Service
public class AnalyzeServiceImpl implements AnalyzeService {

    private final WorkspaceManager workspaceManager;

    private final CommandExecutor commandExecutor;

    public AnalyzeServiceImpl(WorkspaceManager workspaceManager,
                              CommandExecutor commandExecutor) {
        this.workspaceManager = workspaceManager;
        this.commandExecutor = commandExecutor;
    }



    @Override
    public String analyze(AnalyzeRequest request) {
        Path workspacePath = workspaceManager.createWorkspace();

        workspaceManager.copyPomToWorkspace(workspacePath);
        workspaceManager.saveSourceCode(
                workspacePath,
                request.getSourceCode()
        );

        String compileOutput =
                commandExecutor.runCommand(workspacePath, "mvn compile");

        return "Project: " + request.getProjectName()
                + "\nWorkspace: " + workspacePath.toAbsolutePath()
                + "\n\n=== Maven Compile Output ===\n"
                + compileOutput;
    }



}
