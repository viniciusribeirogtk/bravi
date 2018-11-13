package com.bravi.work.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BalanceBracketsSolutionService {

	private static final String CLOSURE_PARENTHESES = ")";
	private static final String CLOSURE_KEY = "}";
	private static final String CLOSURE_BRACKET = "]";

	
	public Boolean solve(String bracketToValidate) {
		boolean isValidBracket = true;
		char[] charArray = bracketToValidate.toCharArray();
		List<String> listValidBracket = new ArrayList<>();
		for (char c : charArray) {
			listValidBracket.add(String.valueOf(c));
		}
		for (int i = 0; i < listValidBracket.size(); i++) {
			String bracket = listValidBracket.get(i);
			if(bracket.equals("(") || bracket.equals("[") || bracket.equals("{")){
				String closureBracketToFind = findBracketType(bracket);
				isValidBracket = checkClosureBracket(listValidBracket.subList(i+1, listValidBracket.size()), closureBracketToFind);
				if (!isValidBracket) {
					break;
				}
			}
		}
		
		return isValidBracket;

	}	
	
	private boolean checkClosureBracket(List<String> subList, String closureBracketToFind) {
		for (String bracket : subList) {
			if (bracket.equals(closureBracketToFind)) {
				return true;
			}
		}
		return false;
	}

	private String findBracketType(String bracket) {
		if (bracket.equals("(")) {
			return CLOSURE_PARENTHESES;
		} else if (bracket.equals("{")) {
			return CLOSURE_KEY;
		}
		return CLOSURE_BRACKET;
	}

}
