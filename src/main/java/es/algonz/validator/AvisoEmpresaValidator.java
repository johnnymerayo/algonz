package es.algonz.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.algonz.domain.AvisoEmpresaVO;

public class AvisoEmpresaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clase) {
		return AvisoEmpresaVO.class.equals(clase);
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
