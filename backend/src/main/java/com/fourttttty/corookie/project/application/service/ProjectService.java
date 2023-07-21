package com.fourttttty.corookie.project.application.service;

import com.fourttttty.corookie.project.application.repository.ProjectRepository;
import com.fourttttty.corookie.project.domain.Project;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final ProjectRepository projectRepository;

    public Project findById(Long id) {
        return projectRepository.findById(id).orElseThrow(EntityExistsException::new);
    }
}
