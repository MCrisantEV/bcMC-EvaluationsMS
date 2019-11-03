package mc.bc.ms.evaluations.app.models;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "evaluations")
public class Evaluation {
	
	@Id
	private String id;
	
	@NotBlank
	private String idCourse;
	@NotBlank
	private String idStudent;
	@NotNull
	private List<Double> listEvaluation;

}
