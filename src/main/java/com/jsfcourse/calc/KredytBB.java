package com.jsfcourse.calc;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;

@Named
@RequestScoped
//@SessionScoped
public class KredytBB {
	private String amount;
	private String months;
	private String rate;
	private Double result;
	
	@Inject
	FacesContext ctx;

	public String calc() {
		try {
			double amount = Double.parseDouble(this.amount);
			double months = Double.parseDouble(this.months);
			double rate = Double.parseDouble(this.rate);
			
			result = (amount / months) * (1+(rate/100));
			
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
		} catch (Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas przetwarzania parametrów", null));
			return "index";
		}
		
		return "showresult";
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getMonths() {
		return months;
	}

	public void setMonths(String months) {
		this.months = months;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public Double getResult() {
		return result;
	}
	
	public void validateAmount(FacesContext ctx, UIComponent comp, Object value) {
		try {
			double amount = Double.parseDouble((String) value);			

			if(amount < 1000) {
				((UIInput) comp).setValid(false);
				ctx.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wartość kredytu musi wynosić co najmniej 1000!", null));
			}
		} catch(Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas walidacji pola 'amount'. Error:" + e, null));
		}
		
	}
	
	public void validateMonths(FacesContext ctx, UIComponent comp, Object value) {
		try {
			double months = Double.parseDouble((String) value);			

			if(months < 2) {
				((UIInput) comp).setValid(false);
				ctx.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wartość miesięcy musi wynieść co najmniej 2!", null));
			}
		} catch(Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas walidacji pola 'months'. Error:" + e, null));
		}
		
	}
	
	public void validateRate(FacesContext ctx, UIComponent comp, Object value) {
		try {
			double rate = Double.parseDouble((String) value);			

			if(rate < 0) {
				((UIInput) comp).setValid(false);
				ctx.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wartość oprocentowania musi być liczbą dodatnią!", null));
			}
			
			if(rate > 100) {
				((UIInput) comp).setValid(false);
				ctx.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wartość oprocentowania musi być poniżej 100!", null));
			}
			
			
		} catch(Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas walidacji pola 'rate'. Error:" + e, null));
		}
		
	}
}
