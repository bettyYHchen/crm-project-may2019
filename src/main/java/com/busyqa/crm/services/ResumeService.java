package com.busyqa.crm.services;


import com.busyqa.crm.message.request.ResumeRequest;
import com.busyqa.crm.model.user.Mock;
import com.busyqa.crm.model.user.Position;
import com.busyqa.crm.model.user.Resume;
import com.busyqa.crm.repo.InternRepository;
import com.busyqa.crm.repo.MockRepository;
import com.busyqa.crm.repo.PositionRepository;
import com.busyqa.crm.repo.ResumeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ResumeService {
    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private MockRepository mockRepository;

    public List<Resume> listResumes() {
        return resumeRepository.findAll();
    }

    public Resume listResumeByEmail(String email) {
        return resumeRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Error: resume not found!"));
    }

    public ResponseEntity<Resume> updateResume(String email, ResumeRequest resumeRequest) {
        // we can only reset modificationDate, resumeEndDate, and resumeDoc

        return resumeRepository.findByEmail(email).map(recordUpdated -> {
            recordUpdated.setModifiedTime(LocalDateTime.now().toString());
            recordUpdated.setResumeEndDate(resumeRequest.getResumeEndDate());
            recordUpdated.setResumeDoc(resumeRequest.getResumeDoc());
            this.resumeRepository.save(recordUpdated);
            return ResponseEntity.ok().body(recordUpdated);
        }).orElse(ResponseEntity.notFound().build());

    }

    public ResponseEntity<?> deleteResume(String email) {
        return resumeRepository.findByEmail(email).map(
                record -> {
                    resumeRepository.deleteByEmail(email);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());

    }

    public Mock changeResumeToMock(String email) {

        Resume resume = resumeRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Fail! -> Cause: Resume not found."));
        List<Position> positions = positionRepository.findAll();
        for (Position p: positions) {
            resume.removePosition(p);
            positionRepository.save(p);
        }
        Position position = positionRepository.findByRoleNameAndTeamName("ROLE_CLIENT","TEAM_MOCK").
                orElseThrow(() -> new RuntimeException("Error: position not found!"));

        Mock mock = new Mock();
        LocalDate today = LocalDate.now();
        LocalDate tenDaysAfter = today.plus(10, ChronoUnit.DAYS);
        BeanUtils.copyProperties(resume,mock);
        mock.addPosition(position);
        mock.setStatusAsOfDay(LocalDateTime.now().toString());
        mock.setModifiedTime(LocalDateTime.now().toString());
        mock.setInterviewDate(tenDaysAfter.toString()); // default mock interview date is setted to 10 days later
        resumeRepository.deleteByEmail(email);
        mockRepository.save(mock);
        return mock;

    }
}
