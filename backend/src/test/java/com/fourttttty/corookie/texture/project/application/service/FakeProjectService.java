package com.fourttttty.corookie.texture.project.application.service;

public class FakeProjectService {
    /*
    private final ProjectRepository projectRepository;
    private final TextChannelRepository textChannelRepository;
    private final MemberRepository memberRepository;
    private final ProjectMemberRepository projectMemberRepository;

    public List<ProjectResponse> findAll(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(EntityNotFoundException::new);
        return projectMemberRepository.findByMember(member)
                .stream()
                .map(ProjectResponse::from)
                .toList();
    }

    public ProjectResponse findById(Long projectId) {
        return ProjectResponse.from(findEntityById(projectId));
    }

    public Project findEntityById(Long projectId) {
        return projectRepository.findById(projectId).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public ProjectResponse create(ProjectCreateRequest projectCreateRequest, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(EntityNotFoundException::new);
        String invitationLink = null;
        //To-Do : invitationLink 실제 링크 생성해서 넣기
        Project project = projectRepository.save(projectCreateRequest.toEntity(invitationLink, member));
        project.createDefaultTextChannels().forEach(textChannelRepository::save);
        return ProjectResponse.from(project);
    }

    @Transactional
    public ProjectResponse modify(ProjectUpdateRequest request, Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(EntityNotFoundException::new);
        project.update(request.name(), request.description(), request.invitationLink(), request.invitationStatus());
        return ProjectResponse.from(project);
    }

    @Transactional
    public void deleteById(Long projectId) {
        projectRepository.findById(projectId).orElseThrow(EntityNotFoundException::new).delete();
    }*/
}
