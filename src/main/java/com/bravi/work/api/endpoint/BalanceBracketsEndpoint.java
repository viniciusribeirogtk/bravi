package com.bravi.work.api.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bravi.work.api.service.BalanceBracketsSolutionService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/balance-brackets")
public class BalanceBracketsEndpoint {
	
	private static final Logger log = LoggerFactory.getLogger(BalanceBracketsEndpoint.class);
	
	@Autowired
	private BalanceBracketsSolutionService balanceBracketsSolutionService; 
	
    @GetMapping(value = "/v1/solveBalanceBrackets/{bracket}")
    @ApiOperation(value = "Solve first chalenge of Balance Bracket")
    public @ResponseBody ResponseEntity<String> getByNameAndId(@PathVariable("bracket")String bracket) {
        log.info("\nReceiving a bracket to solve if have closing brackets:  " + bracket );
        Boolean response = balanceBracketsSolutionService.solve(bracket.trim());
        return new ResponseEntity<>("response: " +response, HttpStatus.OK);
    }

}
