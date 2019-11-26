package com.TR.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.TR.model.EscalationDetails;
import com.TR.model.EscMasterDetails;
import com.TR.service.EscalationImpl;



@RestController

@CrossOrigin(origins = "http://localhost:4200")
public class EscalationController {

	@Autowired

	EscalationImpl escalationImpl;
	 

	@GetMapping("/escalation")
	public List<EscalationDetails> getAllUsers() {
		List<EscalationDetails> result = escalationImpl.getEscalations();

		return result;
	}

	@GetMapping("/escalation/{id}")
	public EscalationDetails getuser(@PathVariable long id) {
		EscalationDetails result = escalationImpl.getescalationid(id);
		return result;
	}

	@PostMapping("/escalation")
	public  EscalationDetails addData(@RequestBody  EscMasterDetails esc) {

		return escalationImpl.saveescalation(esc);
	}

	@PutMapping("/escalation")
	public EscalationDetails updateData(@RequestBody EscalationDetails details) {
		details.setDetailsId(0);
		return escalationImpl.updateescalation(details);
	}
}
