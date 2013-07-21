package es.algonz.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import es.algonz.domain.ConsignatarioVO;

public class ConsignatarioValidator implements Validator {

	@Override
	public boolean supports(Class<?> clase) {
		return ConsignatarioVO.class.equals(clase);
	}

	@Override
	public void validate(Object target, Errors errors) {	
		ConsignatarioVO o=(ConsignatarioVO) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nomFiscal", "error.requerido",new Object[]{"Nom Fiscal"});
		if(o.getAlias()!=null){
			//vslidas que otro campo sea numérico (campo edad)
			//errors.rejectValue("edad", errorCode, errorArgs, defaultMessage)
		}
	}
}
