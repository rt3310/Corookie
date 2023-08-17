package com.fourttttty.corookie.plan.application.service;

import com.fourttttty.corookie.global.exception.ProjectNotFoundException;
import com.fourttttty.corookie.plan.application.repository.PlanCategoryRepository;
import com.fourttttty.corookie.plan.domain.PlanCategory;
import com.fourttttty.corookie.plan.dto.request.PlanCategoryCreateRequest;
import com.fourttttty.corookie.plan.dto.response.PlanCategoryResponse;
import com.fourttttty.corookie.project.application.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlanCategoryService {
    private final PlanCategoryRepository planCategoryRepository;
    private final ProjectRepository projectRepository;

    public List<PlanCategoryResponse> findByProjectId(Long projectId) {
        return planCategoryRepository.findByProjectId(projectId).stream()
                .map(PlanCategoryResponse::from)
                .toList();
    }

    @Transactional
    public PlanCategory create(PlanCategoryCreateRequest request, Long projectId) {
        return planCategoryRepository.findByContent(request.content())
            .orElse(planCategoryRepository.save(PlanCategory.of(request.content(), request.color(),
                    projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new))));
    }

    public PlanCategory findById(Long id){
        return planCategoryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public PlanCategoryResponse findByContent(String content) {
        return PlanCategoryResponse.from(planCategoryRepository.findByContent(content).orElseThrow(EntityNotFoundException::new));
    }
}
