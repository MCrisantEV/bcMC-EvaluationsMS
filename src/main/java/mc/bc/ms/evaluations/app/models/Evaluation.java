package mc.bc.ms.evaluations.app.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "evaluations")
public class Evaluation {
	
	@Id
	private String idCourse;
	
	@Indexed
	private String idStudent;
	
	private List<Double> listEvaluation;

}
