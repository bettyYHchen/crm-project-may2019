package com.busyqa.crm.services;

import com.busyqa.crm.message.request.InternRequest;
import com.busyqa.crm.message.response.InternResponse;
import com.busyqa.crm.model.user.Intern;
import com.busyqa.crm.repo.InternRepository;
import com.busyqa.crm.repo.PositionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class InternService {
    @Autowired
    private PositionRepository positionRepository;


    @Autowired
    private InternRepository internRepository;

    public List<InternResponse> listInterns() {
        List<Intern> interns = internRepository.findAll();
        if (interns.isEmpty()) throw new RuntimeException("Empty intern list!");
        List<InternResponse> internResponses = new ArrayList<>();
        System.out.println(interns.size());
        for (Intern s: interns) {
            String[] tmp = s.getName().split(" ");
            String firstName = tmp[0];
            String lastName = tmp[1];
            internResponses.add(new InternResponse(firstName,lastName,
                    s.getPhone(), s.getEmail(), s.getCoopStatus(), s.getaTrainingClassName()));

        }
        return internResponses;
    }

    public InternResponse listInternByEmail(String email) {
        Intern intern = internRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Error: intern not found!"));
        String[] tmp = intern.getName().split(" ");
        String firstName = tmp[0];
        String lastName = tmp[1];
        return new InternResponse(firstName,lastName,
                intern.getPhone(), intern.getEmail(), intern.getCoopStatus(), intern.getaTrainingClassName());
    }

    public ResponseEntity<InternResponse> updateIntern(String email, InternRequest internRequest) {

        return internRepository.findByEmail(email).map(recordUpdated -> {
            String name = internRequest.getFirstName() + " " + internRequest.getLastName();
            recordUpdated.setName(name);
            recordUpdated.setUsername(internRequest.getEmail());
            recordUpdated.setEmail(internRequest.getEmail());
            recordUpdated.setPhone(internRequest.getPhone());
            recordUpdated.setCoopStatus(internRequest.getCoopStatus());
            recordUpdated.setaTrainingClassName(internRequest.getaTrainingClassName());
            recordUpdated.setStatusAsOfDay(LocalDateTime.now().toString());
            this.internRepository.save(recordUpdated);
            InternResponse internResponse = new InternResponse();
            BeanUtils.copyProperties(internRequest,internResponse);
            return ResponseEntity.ok().body(internResponse);
        }).orElse(ResponseEntity.notFound().build());

    }

    public ResponseEntity<?> deleteIntern(String email) {
        return internRepository.findByEmail(email).map(
                record -> {
                    internRepository.deleteByEmail(email);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());

    }
}
