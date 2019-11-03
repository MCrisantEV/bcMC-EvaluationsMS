package mc.bc.ms.evaluations.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mc.bc.ms.evaluations.app.services.EvaluationService;

@RestController
@RequestMapping("/evaluations")
public class EvaluationController {
	
	@Autowired
	private EvaluationService evaServ;
	
}
