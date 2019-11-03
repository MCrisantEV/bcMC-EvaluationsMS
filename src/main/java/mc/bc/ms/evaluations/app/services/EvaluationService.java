package mc.bc.ms.evaluations.app.services;

import java.util.Map;

import mc.bc.ms.evaluations.app.models.Evaluation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EvaluationService {

	public Mono<Map<String, Object>> saveEvaluation(Evaluation evaluation);

	public Mono<Evaluation> findCourseStudent(String idCourse, String idStudent);

	public Flux<Evaluation> findStudent(String idStudent);

	public Flux<Evaluation> findCourse(String idCourse);

	public Mono<Map<String, Object>> updateEvaluation(String id, Evaluation evaluation);
	
	public Mono<Map<String, Object>> deleteEvaluation(String id);

}
