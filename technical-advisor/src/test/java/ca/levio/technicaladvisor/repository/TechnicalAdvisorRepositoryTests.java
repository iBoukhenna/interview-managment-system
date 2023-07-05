package ca.levio.technicaladvisor.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.levio.technicaladvisor.model.JobPosition;
import ca.levio.technicaladvisor.model.Skill;
import ca.levio.technicaladvisor.model.TechnicalAdvisor;

import java.util.UUID;

import static ca.levio.technicaladvisor.enums.LevelOfExpertise.*;

@SpringBootTest
public class TechnicalAdvisorRepositoryTests {

    @Autowired
    private TechnicalAdvisorRepository technicalAdvisorRepository;

    @Autowired
    private JobPositionRepository jobPositionRepository;

    @Autowired
    private SkillRepository skillRepository;

    @BeforeEach
    public void setup() {
        technicalAdvisorRepository.deleteAll();
        skillRepository.deleteAll();
        jobPositionRepository.deleteAll();


		List<JobPosition> jobPositions = Arrays.asList(
         new JobPosition(UUID.randomUUID().toString(),"Scrum Master", "Agile", "Scrum",3),
         new JobPosition(UUID.randomUUID().toString(),"Developeur Java", "IL", "Java",1),
         new JobPosition(UUID.randomUUID().toString(),"Team Lead Java", "IL", "Java",2),
         new JobPosition(UUID.randomUUID().toString(),"Architecte Java", "IL", "Java",3),
         new JobPosition(UUID.randomUUID().toString(),"Developeur .NET", "IL", ".NET",1)
		);
		jobPositionRepository.saveAll(jobPositions);

		Skill skillScrumMasterSkilled = new Skill(UUID.randomUUID().toString(), jobPositionRepository.findByJobPositionLabel("Scrum Master").get(), SKILLED);

		Skill skillDevNetSkilled = new Skill(UUID.randomUUID().toString(), jobPositionRepository.findByJobPositionLabel("Developeur .NET").get(), SKILLED);

		Skill skillTeamLeadJavaSkilled = new Skill(UUID.randomUUID().toString(), jobPositionRepository.findByJobPositionLabel("Team Lead Java").get(), JUNIOR);

		Skill skillDevJavaSkilled = new Skill(UUID.randomUUID().toString(), jobPositionRepository.findByJobPositionLabel("Developeur Java").get(), SKILLED);
		Skill skillDevJavaSeniorPlus = new Skill(UUID.randomUUID().toString(), jobPositionRepository.findByJobPositionLabel("Developeur Java").get(), SENIOR_PLUS);
		Skill skillDevJavaSenior = new Skill(UUID.randomUUID().toString(), jobPositionRepository.findByJobPositionLabel("Developeur Java").get(), SENIOR);
		Skill skillDevJavaIntermediate = new Skill(UUID.randomUUID().toString(), jobPositionRepository.findByJobPositionLabel("Developeur Java").get(), INTERMEDIATE);
		Skill skillDevJavaJunior = new Skill(UUID.randomUUID().toString(), jobPositionRepository.findByJobPositionLabel("Developeur Java").get(), JUNIOR);

		List<Skill> skills = Arrays.asList(
		 skillDevJavaSkilled, skillDevJavaSeniorPlus, skillDevJavaSenior, skillDevJavaIntermediate, skillDevJavaJunior, 
		 skillDevNetSkilled, skillTeamLeadJavaSkilled, skillScrumMasterSkilled
		);
		skillRepository.saveAll(skills);

		List<TechnicalAdvisor> technicalAdvisors = Arrays.asList(
			new TechnicalAdvisor(UUID.randomUUID().toString(), "rachad bensalem", "rachad.bensalem@levio.ca",  Arrays.asList(skillDevNetSkilled, skillDevJavaIntermediate)),
			new TechnicalAdvisor(UUID.randomUUID().toString(), "billel belhadj", "billel.belhadj@levio.ca",  Arrays.asList(skillScrumMasterSkilled, skillDevJavaIntermediate)),
			new TechnicalAdvisor(UUID.randomUUID().toString(), "ahmed hamada", "ahmed.hamada@levio.ca",  Arrays.asList(skillDevJavaSenior)),
			new TechnicalAdvisor(UUID.randomUUID().toString(), "amine kabouche", "amine.kabouche@levio.ca",  Arrays.asList(skillDevJavaIntermediate)),
			new TechnicalAdvisor(UUID.randomUUID().toString(), "ibrahim boukhenna", "ibrahim.boukhenna@levio.ca",  Arrays.asList(skillTeamLeadJavaSkilled))
		);
        technicalAdvisorRepository.saveAll(technicalAdvisors);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void selectTechnicalAdvisorsByCriteriaAllTest() {
        List<TechnicalAdvisor> technicalAdvisors = technicalAdvisorRepository.findEligibleTechnicalAdvisors(jobPositionRepository.findByJobPositionLabel("Developeur Java").get().getId(), JUNIOR.ordinal(), "1", 5);
        int interviewActual = technicalAdvisors.size();
        int interviewExpected = 5;
        assertEquals(interviewExpected, interviewActual);
    }

    @Test
    void selectTechnicalAdvisorsByCriteriaLevelTest() {
        List<TechnicalAdvisor> technicalAdvisors = technicalAdvisorRepository.findEligibleTechnicalAdvisors(jobPositionRepository.findByJobPositionLabel("Developeur Java").get().getId(), INTERMEDIATE.ordinal(), "1", 5);
        int interviewActual = technicalAdvisors.size();
        int interviewExpected = 2;
        assertEquals(interviewExpected, interviewActual);
    }
}
