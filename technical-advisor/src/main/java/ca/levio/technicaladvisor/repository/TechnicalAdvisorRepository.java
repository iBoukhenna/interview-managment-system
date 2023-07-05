package ca.levio.technicaladvisor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ca.levio.technicaladvisor.model.TechnicalAdvisor;

public interface TechnicalAdvisorRepository extends JpaRepository<TechnicalAdvisor, String> {
    
    @Query(
            value = "SELECT ta.* FROM technical_advisor ta " +
                    "INNER JOIN technical_advisor_skills tas ON ta.id = tas.technical_advisor_id " +
                    "INNER JOIN skill s ON s.id = tas.skills_id " +
                    "INNER JOIN job_position jp ON s.job_position_id = jp.id " +
                    "INNER JOIN job_position jpap ON jpap.id = :jobPosition " +
                    "WHERE (" +
                        "(jp.id = jpap.id AND s.level_of_expertise > :levelOfExpertise)" +
                        " OR " +
                        "(jp.id != jpap.id AND jp.hierarchical_line = jpap.hierarchical_line AND jp.hierarchical_level > jpap.hierarchical_level)" +
                    ")" +
                    "AND ta.id NOT IN " +
                    "(SELECT jai.technical_advisor_id FROM technical_advisor_interview jai " + 
                    " WHERE jai.interview = :interview) " +
                    " LIMIT :numberOfTechnicalAdvisorByBatch",
            nativeQuery = true)
    List<TechnicalAdvisor> findEligibleTechnicalAdvisors(@Param("jobPosition") String jobPosition, @Param("levelOfExpertise") Integer levelOfExpertise, @Param("interview") String interview, @Param("numberOfTechnicalAdvisorByBatch") Integer numberOfTechnicalAdvisorByBatch);
}
