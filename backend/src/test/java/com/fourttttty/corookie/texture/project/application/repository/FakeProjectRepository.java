package com.fourttttty.corookie.texture.project.application.repository;

import com.fourttttty.corookie.project.application.repository.ProjectRepository;
import com.fourttttty.corookie.project.domain.Project;
import jakarta.persistence.EntityNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FakeProjectRepository implements ProjectRepository {

    private long autoIncrementId = 1L;
    private final Map<Long, Project> store = new HashMap<>();

    @Override
    public Project save(Project project) {
        store.put(autoIncrementId++, project);
        return project;
    }

    @Override
    public Optional<Project> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Project> findByInvitationLink(String invitationLink) {
        for (Map.Entry<Long, Project> item : store.entrySet()) {
            Project project = item.getValue();
            if(project.getInvitationLink().equals(invitationLink)){
                return Optional.of(project);
            }
        }
       throw new EntityNotFoundException();
    }

    @Override
    public List<Project> findAll() {
        return store.values().stream()
                .toList();
    }
}
