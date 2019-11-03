package mc.bc.ms.evaluations.app.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mc.bc.ms.evaluations.app.models.Evaluation;
import mc.bc.ms.evaluations.app.services.EvaluationService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/evaluations")
public class EvaluationController {
	
	@Autowired
	private EvaluationService evaServ;
	
	@PostMapping
	public Mono<Map<String, Object>> createEvaluation(@RequestBody Evaluation evaluation){
		return evaServ.saveEvaluation(evaluation);
	}
	
	@GetMapping("/{course}/{student}")
	public Mono<Evaluation> findCourseStudent(@PathVariable String course, @PathVariable String student){
		return evaServ.findCourseStudent(course, student);
	}
	
	@GetMapping("/students/{student}")
	public Flux<Evaluation> findStudent(@PathVariable String student){
		return evaServ.findStudent(student);
	}
	
	@GetMapping("/courses/{course}")
	public Flux<Evaluation> findCourse(@PathVariable String course){
		return evaServ.findCourse(course);
	}
	
	@PutMapping("/{id}")
	public Mono<Map<String, Object>> editEvaluation(@PathVariable String id, @RequestBody Evaluation evaluation){
		return evaServ.updateEvaluation(id, evaluation);
	}
}
