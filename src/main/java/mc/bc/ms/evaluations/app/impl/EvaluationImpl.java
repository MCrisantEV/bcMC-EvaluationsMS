package mc.bc.ms.evaluations.app.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import mc.bc.ms.evaluations.app.models.Evaluation;
import mc.bc.ms.evaluations.app.repositories.EvaluationRepository;
import mc.bc.ms.evaluations.app.services.EvaluationService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EvaluationImpl implements EvaluationService {

	@Autowired
	private EvaluationRepository evaRep;
	
	@Autowired
	private Validator validator;

	@Override
	public Mono<Map<String, Object>> saveEvaluation(Evaluation evaluation) {
		Map<String, Object> respuesta = new HashMap<String, Object>();
		if (errors(evaluation) == null) {
			return evaRep.findByIdCourseAndIdStudent(evaluation.getIdCourse(), evaluation.getIdStudent()).map(dbe -> {
				respuesta.put("Error", "No se puede registrar, ya esta registrado.");
				return respuesta;
			}).switchIfEmpty(Mono.just(evaluation).map(ev -> {
				evaRep.save(ev).subscribe();
				respuesta.put("Mensaje", "Se registraron las evaluaicones con éxito.");
				return respuesta;
			}));
		}else {
			return errors(evaluation);
		}
	}
	
	@Override
	public Mono<Evaluation> findCourseStudent(String idCourse, String idStudent) {
		return evaRep.findByIdCourseAndIdStudent(idCourse, idStudent);
	}

	@Override
	public Flux<Evaluation> findStudent(String idStudent) {
		return evaRep.findByIdStudent(idStudent);
	}

	@Override
	public Flux<Evaluation> findCourse(String idCourse) {
		return evaRep.findByIdCourse(idCourse);
	}
	
	@Override
	public Mono<Map<String, Object>> updateEvaluation(String id, Evaluation evaluation) {
		Map<String, Object> respuesta = new HashMap<String, Object>();
		evaluation.setIdCourse(id);
		evaluation.setIdStudent(id);
		if (errors(evaluation) == null) {
			return evaRep.findById(id).map(dbe -> {
				dbe.setListEvaluation(evaluation.getListEvaluation());
				evaRep.save(dbe).subscribe();
				respuesta.put("Mensaje", "Se actualizaron las evaluaicones con éxito.");
				return respuesta;
			}).switchIfEmpty(Mono.just(evaluation).map(ev -> {
				respuesta.put("Error", "No tiene evaluaciones registradas.");
				return respuesta;
			}));
		}else {
			return errors(evaluation);
		}
	}
	
	private Mono<Map<String, Object>> errors(Evaluation objec) {
		Map<String, Object> respuesta = new HashMap<String, Object>();
		Errors errors = new BeanPropertyBindingResult(objec, Evaluation.class.getName());
		validator.validate(objec, errors);

		respuesta.put("status", HttpStatus.BAD_REQUEST.value());
		respuesta.put("Mensaje", "Error, revise los datos");

		if (errors.hasErrors()) {
			return Flux.fromIterable(errors.getFieldErrors()).map(err -> {
				String[] matriz = { err.getField(), err.getDefaultMessage() };
				return matriz;
			}).collectList().flatMap(l -> {
				l.forEach(m -> {
					for (int i = 0; i < m.length; i++) {
						respuesta.put(m[0], m[i]);
					}
				});
				return Mono.just(respuesta);
			});
		}else if (objec.getListEvaluation().size() == 0) {
			respuesta.put("ListEvaluation", "no puede estar vacío");
			return Mono.just(respuesta);
		}else if (objec.getListEvaluation().size() > 3) {
			respuesta.put("Error", "Maximo 3 evaluaciones");
			return Mono.just(respuesta);
		}

		return null;
	}

}
