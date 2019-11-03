package mc.bc.ms.evaluations.app.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import mc.bc.ms.evaluations.app.models.Evaluation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EvaluationRepository extends ReactiveMongoRepository<Evaluation, String> {

	public Mono<Evaluation> findByIdCourseAndIdStudent(String idCourse, String idStudent);

	public Flux<Evaluation> findByIdStudent(String idStudent);

	public Flux<Evaluation> findByIdCourse(String idCourse);

}
