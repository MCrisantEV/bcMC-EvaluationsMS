package mc.bc.ms.evaluations.app.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import mc.bc.ms.evaluations.app.models.Evaluation;

public interface EvaluationRepository extends ReactiveMongoRepository<Evaluation, String> {

}