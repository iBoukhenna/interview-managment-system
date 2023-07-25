package ca.levio.recruiter.adapter.controller.register;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.levio.recruiter.application.registerrecruiter.boundary.input.IRecruiterRegisterBoundary;
import ca.levio.recruiter.application.registerrecruiter.exception.RecruiterRegisterCustomException;
import ca.levio.recruiter.application.registerrecruiter.model.request.RecruiterRegisterRequestModel;
import ca.levio.recruiter.application.registerrecruiter.model.response.RecruiterRegisterResponseModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
public class RecruiterRegisterController {

    final IRecruiterRegisterBoundary recruiterRegisterBoundary;

    @PostMapping("api/v1/recruiters")
    RecruiterRegisterResponseModel create(@RequestBody RecruiterRegisterRequestModel recruiterRegisterRequestModel) throws RecruiterRegisterCustomException {
        log.info("create Recruiter : " + recruiterRegisterRequestModel);
        return recruiterRegisterBoundary.create(recruiterRegisterRequestModel);
    }
}
