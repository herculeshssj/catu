/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.hslife.catu.validate;

import br.com.hslife.catu.util.Util;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Hércules
 */
public class EmailValidate implements Validator{

    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String email = "";
        if (value ==null) return;
        if (value instanceof String) {
            email = value.toString();
        }
        if (!Util.validaEmail(email)) {
            FacesMessage mensagem = new FacesMessage("E-Mail informado é inválido!");
            throw new ValidatorException(mensagem);
        }
    }

}
