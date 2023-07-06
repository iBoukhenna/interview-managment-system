package ca.levio.technicaladvisor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ca.levio.technicaladvisor.model.JobPosition;
import ca.levio.technicaladvisor.model.Skill;
import ca.levio.technicaladvisor.model.TechnicalAdvisor;
import ca.levio.technicaladvisor.service.JobPositionService;
import ca.levio.technicaladvisor.service.SkillService;
import ca.levio.technicaladvisor.service.TechnicalAdvisorService;

import static ca.levio.technicaladvisor.enums.LevelOfExpertise.*;

@SpringBootApplication
public class TechnicalAdvisorApplication implements CommandLineRunner {

	@Autowired
	private TechnicalAdvisorService technicalAdvisorService;
	@Autowired
	private JobPositionService jobPositionService;

	@Autowired
	private SkillService skillService;

	public static void main(String[] args) {
		SpringApplication.run(TechnicalAdvisorApplication.class, args);
	}

	 @Override
    public void run(String... args) throws Exception {
		List<JobPosition> jobPositions = Arrays.asList(
         new JobPosition(UUID.randomUUID().toString(),"Scrum Master", "Agile", "Scrum",3),
         new JobPosition(UUID.randomUUID().toString(),"Developeur Java", "IL", "Java",1),
         new JobPosition(UUID.randomUUID().toString(),"Team Lead Java", "IL", "Java",2),
         new JobPosition(UUID.randomUUID().toString(),"Architecte Java", "IL", "Java",3),
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
			new TechnicalAdvisor(UUID.randomUUID().toString(), "rachad bensalem", "ibrahim.9007@gmail.com",  Arrays.asList(skillDevNetSkilled, skillDevJavaIntermediate)),
			new TechnicalAdvisor(UUID.randomUUID().toString(), "billel belhadj", "ibrahim.boukhenna@gmail.com",  Arrays.asList(skillScrumMasterSkilled, skillDevJavaIntermediate)),
			new TechnicalAdvisor(UUID.randomUUID().toString(), "ahmed hamada", "boukhenna.ibrahim@gmail.com",  Arrays.asList(skillDevJavaSenior)),
			new TechnicalAdvisor(UUID.randomUUID().toString(), "amine kabouche", "boukhenna.ibrahim@outlook.com",  Arrays.asList(skillDevJavaIntermediate)),
			new TechnicalAdvisor(UUID.randomUUID().toString(), "ibrahim boukhenna", "ibrahim.boukhenna@outlook.com",  Arrays.asList(skillTeamLeadJavaSkilled))
		);
        technicalAdvisorService.saveAllTechnicalAdvisors(technicalAdvisors);
    }
}
