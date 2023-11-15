package com.example.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.PayDto;
import com.example.service.PayService;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

@RestController
@RequestMapping("/payment")
public class PayController {

	private PayService payService;

	public PayController(PayService payService) {
		this.payService = payService;
	}

	private IamportClient api;

	public PayController() {
		this.api = new IamportClient("3766738282401126",
				"cvQ9Bb92gsKkhmUVoAzKJWqZBIN5qL9IS0yNr9X8fQt46FQ7PmkNAddqRVDtqm5AHf2ezN8QtBgOjtU0");
	}

	@PostMapping(value = "")
	public void insert(PayDto insertVO) {
		payService.insert(insertVO);
	}

	@PostMapping(value = "/{imp_uid}")
	public IamportResponse<Payment> paymentByImpUid(HttpSession session, @PathVariable String impUid)
			throws IamportResponseException, IOException {
		return api.paymentByImpUid(impUid);
	}

}
