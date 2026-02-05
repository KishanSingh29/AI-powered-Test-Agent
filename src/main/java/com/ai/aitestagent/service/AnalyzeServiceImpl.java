package com.ai.aitestagent.service;

import com.ai.aitestagent.dto.AnalyzeRequest;
import com.ai.aitestagent.workspace.WorkspaceManager;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
public class AnalyzeServiceImpl implements AnalyzeService {

    private final WorkspaceManager workspaceManager;

    public AnalyzeServiceImpl(WorkspaceManager workspaceManager) {
        this.workspaceManager = workspaceManager;
    }

    @Override
    public String analyze(AnalyzeRequest request) {
        Path workspacePath = workspaceManager.createWorkspace();

        return "Project: " + request.getProjectName()
                + " | Workspace: " + workspacePath.toAbsolutePath();
    }
}
