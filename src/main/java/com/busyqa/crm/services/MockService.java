package com.busyqa.crm.services;

import com.busyqa.crm.message.request.MockRequest;
import com.busyqa.crm.model.user.Alumni;
import com.busyqa.crm.model.user.Mock;
import com.busyqa.crm.model.user.Position;
import com.busyqa.crm.repo.AlumniRepository;
import com.busyqa.crm.repo.MockRepository;
import com.busyqa.crm.repo.PositionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MockService {
    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private MockRepository mockRepository;

    @Autowired
    private AlumniRepository alumniRepository;

    public List<Mock> listMocks() {
        return mockRepository.findAll();
    }

    public Mock listMockByEmail(String email) {
        return mockRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Error: mock not found!"));
    }

    public ResponseEntity<Mock> updateMock(String email, MockRequest mockRequest) {
        // we can only reset modificationDate, and interviewDate

        return mockRepository.findByEmail(email).map(recordUpdated -> {
            recordUpdated.setModifiedTime(LocalDateTime.now().toString());
            recordUpdated.setInterviewDate(mockRequest.getInterviewDate());
            this.mockRepository.save(recordUpdated);
            return ResponseEntity.ok().body(recordUpdated);
        }).orElse(ResponseEntity.notFound().build());

    }

    public ResponseEntity<?> deleteMock(String email) {
        return mockRepository.findByEmail(email).map(
                record -> {
                    mockRepository.deleteByEmail(email);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());

    }

    public Alumni changeMockToAlumni(String email) {

        Mock mock = mockRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Fail! -> Cause: Mock not found."));
        List<Position> positions = positionRepository.findAll();
        for (Position p: positions) {
            mock.removePosition(p);
            positionRepository.save(p);
        }


        Alumni alumni = new Alumni();
        BeanUtils.copyProperties(mock,alumni);
        alumni.setPlacementStatus("");
        alumni.setCompanyName("");
        alumni.setEmploymentType("");
        alumni.setDesignation("");
        alumni.setComment("");
        mockRepository.deleteByEmail(email);
        alumniRepository.save(alumni);
        return alumni;

    }
}
