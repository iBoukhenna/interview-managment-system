package ca.levio.technicaladvisor.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.levio.commonbean.dto.EligibleTechnicalAdvisorDto;
import ca.levio.technicaladvisor.model.JobPosition;
import ca.levio.technicaladvisor.model.Skill;
import ca.levio.technicaladvisor.model.TechnicalAdvisor;
import ca.levio.technicaladvisor.repository.JobPositionRepository;
import ca.levio.technicaladvisor.repository.SkillRepository;
import ca.levio.technicaladvisor.repository.TechnicalAdvisorInterviewRepository;
import ca.levio.technicaladvisor.repository.TechnicalAdvisorRepository;

import java.util.UUID;

import static ca.levio.technicaladvisor.enums.LevelOfExpertise.*;

@SpringBootTest
public class TechnicalAdvisorServiceTests {

    @Autowired
    private TechnicalAdvisorInterviewService technicalAdvisorInterviewService;

    @Autowired
    private TechnicalAdvisorService technicalAdvisorService;

    @Autowired
    private JobPositionService jobPositionService;

    @Autowired
    private SkillService skillService;

    @BeforeEach
    public void setup() {
        technicalAdvisorInterviewService.deleteAll();
        technicalAdvisorService.deleteAll();
        skillService.deleteAll();
        jobPositionService.deleteAll();


		List<JobPosition> jobPositions = Arrays.asList(
         new JobPosition(UUID.randomUUID().toString(),"Scrum Master", "Agile", "Scrum",3),
         new JobPosition(UUID.randomUUID().toString(),"Developeur Java", "IL", "Java",1),
         new JobPosition(UUID.randomUUID().toString(),"Team Lead Java", "IL", "Java",2),
         new JobPosition(UUID.randomUUID().toString(),"Developeur .NET", "IL", ".NET",1)
		);
		jobPositionService.saveAllJobPositions(jobPositions);

		Skill skillScrumMasterSkilled = new Skill(UUID.randomUUID().toString(), jobPositionService.getJobPosition("Scrum Master"), SKILLED);

		Skill skillDevNetSkilled = new Skill(UUID.randomUUID().toString(), jobPositionService.getJobPosition("Developeur .NET"), SKILLED);

		Skill skillTeamLeadJavaSkilled = new Skill(UUID.randomUUID().toString(), jobPositionService.getJobPosition("Team Lead Java"), JUNIOR);

		Skill skillDevJavaSkilled = new Skill(UUID.randomUUID().toString(), jobPositionService.getJobPosition("Developeur Java"), SKILLED);
		Skill skillDevJavaSeniorPlus = new Skill(UUID.randomUUID().toString(), jobPositionService.getJobPosition("Developeur Java"), SENIOR_PLUS);
		Skill skillDevJavaSenior = new Skill(UUID.randomUUID().toString(), jobPositionService.getJobPosition("Developeur Java"), SENIOR);
		Skill skillDevJavaIntermediate = new Skill(UUID.randomUUID().toString(), jobPositionService.getJobPosition("Developeur Java"), INTERMEDIATE);
		Skill skillDevJavaJunior = new Skill(UUID.randomUUID().toString(), jobPositionService.getJobPosition("Developeur Java"), JUNIOR);

		List<Skill> skills = Arrays.asList(
		 skillDevJavaSkilled, skillDevJavaSeniorPlus, skillDevJavaSenior, skillDevJavaIntermediate, skillDevJavaJunior, 
		 skillDevNetSkilled, skillTeamLeadJavaSkilled, skillScrumMasterSkilled
		);
		skillService.saveAllSkills(skills);

		List<TechnicalAdvisor> technicalAdvisors = Arrays.asList(
			new TechnicalAdvisor(UUID.randomUUID().toString(), "rachad bensalem", "rachad.bensalem@levio.ca",  Arrays.asList(skillDevNetSkilled, skillDevJavaIntermediate)),
			new TechnicalAdvisor(UUID.randomUUID().toString(), "billel belhadj", "billel.belhadj@levio.ca",  Arrays.asList(skillScrumMasterSkilled, skillDevJavaIntermediate)),
			new TechnicalAdvisor(UUID.randomUUID().toString(), "ahmed hamada", "ahmed.hamada@levio.ca",  Arrays.asList(skillDevJavaSenior)),
			new TechnicalAdvisor(UUID.randomUUID().toString(), "amine kabouche", "amine.kabouche@levio.ca",  Arrays.asList(skillDevJavaIntermediate)),
			new TechnicalAdvisor(UUID.randomUUID().toString(), "ibrahim boukhenna", "ibrahim.boukhenna@levio.ca",  Arrays.asList(skillTeamLeadJavaSkilled))
		);
        technicalAdvisorService.saveAllTechnicalAdvisors(technicalAdvisors);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void selectTechnicalAdvisorsByCriteriaAllTest() {
        List<EligibleTechnicalAdvisorDto> eligibleTechnicalAdvisorDtos = technicalAdvisorService.selectEligibleTechnicalAdvisors("Developeur Java", JUNIOR, "1", 5);
        int interviewActual = eligibleTechnicalAdvisorDtos.size();
        int interviewExpected = 5;
        assertEquals(interviewExpected, interviewActual);
    }

    @Test
    void selectTechnicalAdvisorsByCriteriaLevelTest() {
        List<EligibleTechnicalAdvisorDto> eligibleTechnicalAdvisorDtos = technicalAdvisorService.selectEligibleTechnicalAdvisors("Developeur Java", INTERMEDIATE, "1", 5);
        int interviewActual = eligibleTechnicalAdvisorDtos.size();
        int interviewExpected = 2;
        assertEquals(interviewExpected, interviewActual);
    }

    @Test
    void selectTechnicalAdvisorsByCriteriaLimitTest() {
        List<EligibleTechnicalAdvisorDto> eligibleTechnicalAdvisorDtos = technicalAdvisorService.selectEligibleTechnicalAdvisors("Developeur Java", JUNIOR, "1", 3);
        eligibleTechnicalAdvisorDtos = technicalAdvisorService.selectEligibleTechnicalAdvisors("Developeur Java", JUNIOR, "1", 3);
        int interviewActual = eligibleTechnicalAdvisorDtos.size();
        int interviewExpected = 2;
        assertEquals(interviewExpected, interviewActual);
    }
}
