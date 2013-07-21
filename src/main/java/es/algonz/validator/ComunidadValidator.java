package es.algonz.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.algonz.domain.ComunidadVO;

public class ComunidadValidator implements Validator {

	@Override
	public boolean supports(Class<?> clase) {
		return ComunidadVO.class.equals(clase);
	}

	@Override
	public void validate(Object target, Errors errors) {	
//		VO o=(ConsignatarioVO) target;
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nomFiscal", "error.requerido",new Object[]{"Nom Fiscal"});
//		if(o.getAlias()!=null){
//			//vslidas que otro campo sea numérico (campo edad)
//			//errors.rejectValue("edad", errorCode, errorArgs, defaultMessage)
//		}
	}
}
