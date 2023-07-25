package ca.levio.recruiter.adapter.controller.select;

import lombok.extern.slf4j.Slf4j;
import lombok.AllArgsConstructor;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ca.levio.recruiter.application.selectrecruiter.exception.RecruiterSelectCustomException;
import ca.levio.recruiter.application.selectrecruiter.boundary.input.IRecruiterSelectBoundary;
import ca.levio.recruiter.application.selectrecruiter.model.response.RecruiterSelectResponseModel;

@Slf4j
@AllArgsConstructor
@RestController
public class RecruiterSelectController {


    final IRecruiterSelectBoundary recruiterSelectBoundary;

    @GetMapping("api/v1/recruiters/{id}")
    public RecruiterSelectResponseModel getRecruiter(@PathVariable("id") UUID id) throws RecruiterSelectCustomException {
        log.info("get recruiter by id : " + id);
        return recruiterSelectBoundary.getRecruiterById(id);
    }
}
