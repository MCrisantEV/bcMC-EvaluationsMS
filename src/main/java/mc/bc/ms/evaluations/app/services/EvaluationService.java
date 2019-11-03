package mc.bc.ms.evaluations.app.services;

import java.util.Map;

import mc.bc.ms.evaluations.app.models.Evaluation;
import reactor.core.publisher.Mono;

public interface EvaluationService {
	
	public Mono<Map<String, Object>> saveEvaluation(Evaluation evaluation);
}
