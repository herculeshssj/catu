/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.hslife.catu.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 *
 * @author hercules
 */
public class SimNaoConverter implements Converter{

    @Override
	public Object getAsObject(FacesContext contexto, UIComponent componente,
			String valor) {
		// TODO Auto-generated method stub
		try {
			if (valor.equalsIgnoreCase("Sim")) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new ConverterException(e);
		}
	}

	@Override
	public String getAsString(FacesContext contexto, UIComponent componente,
			Object valor) {
		// TODO Auto-generated method stub
		try {
			if ((Boolean)valor) {
				return "Sim";
			} else {
				return "Não";
			}
		} catch (Exception e) {
			throw new ConverterException(e);
		}
	}

}
