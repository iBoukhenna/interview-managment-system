package ca.levio.recruiter.adapter.gateway.db;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import ca.levio.recruiter.adapter.gateway.mapper.RecruiterRegisterModelMapper;
import ca.levio.recruiter.adapter.gateway.mapper.RecruiterSelectModelMapper;
import ca.levio.recruiter.adapter.gateway.model.Recruiter;
import ca.levio.recruiter.adapter.repository.IRecruiterRepository;
import ca.levio.recruiter.application.registerrecruiter.boundary.output.IRecruiterRegisterGateway;
import ca.levio.recruiter.application.registerrecruiter.model.data.RecruiterRegisterDataModel;
import ca.levio.recruiter.application.selectrecruiter.boundary.output.IRecruiterSelectGateway;
import ca.levio.recruiter.application.selectrecruiter.model.data.RecruiterSelectDataModel;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class RecruiterGateway implements IRecruiterRegisterGateway, IRecruiterSelectGateway {
    
    final IRecruiterRepository recruiterRepository;
    final RecruiterRegisterModelMapper recruiterRegisterModelMapper;
    final RecruiterSelectModelMapper recruiterSelectModelMapper;

    @Override
    public RecruiterRegisterDataModel save(RecruiterRegisterDataModel recruiterRegisterDataModel) {
        Recruiter recruiter = new Recruiter(recruiterRegisterDataModel.getId(), recruiterRegisterDataModel.getName(), recruiterRegisterDataModel.getEmail(), recruiterRegisterDataModel.getCreationTime());
        recruiter = recruiterRepository.save(recruiter);
        RecruiterRegisterDataModel recruiterSelectDataModel = recruiterRegisterModelMapper.recruiterToDataModel(recruiter);
        return recruiterSelectDataModel;
    }

    @Override
    public boolean existsByEmail(String email) {
        return recruiterRepository.existsByEmail(email);
    }

    @Override
    public RecruiterSelectDataModel getRecruiterById(UUID id) {
        Optional<Recruiter> recruiterOptional  = recruiterRepository.findById(id);
        if (recruiterOptional.isPresent()) {
            Recruiter recruiter = recruiterOptional.get();
            RecruiterSelectDataModel recruiterSelectDataModel = recruiterSelectModelMapper.recruiterToDataModel(recruiter);
            return recruiterSelectDataModel;
        }
        return null;
    }
}
