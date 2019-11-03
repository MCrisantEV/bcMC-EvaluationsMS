package mc.bc.ms.evaluations.app.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mc.bc.ms.evaluations.app.repositories.EvaluationRepository;
import mc.bc.ms.evaluations.app.services.EvaluationService;

@Service
public class EvaluationImpl implements EvaluationService{
	
	@Autowired
	private EvaluationRepository evaRep;
}
