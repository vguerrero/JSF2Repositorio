package org.vmgs.com.ManagedBeans;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

@ManagedBean(name = "lenguaje")
@SessionScoped
public class LenguajeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String localeCode;

	private static Map<String, Object> contries;

	static {
		contries = new LinkedHashMap<String, Object>();
		contries.put("Español", new Locale("ES"));
		contries.put("English", Locale.ENGLISH);
		

	}

	/**
	 * @return the localeCode
	 */
	public String getLocaleCode() {
		return localeCode;
	}

	/**
	 * @param localeCode
	 *            the localeCode to set
	 */
	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}

	/**
	 * @return the contries
	 */
	public Map<String, Object> getContries() {
		return contries;
	}

	// value change event listener
	public void countryLocaleCodeChanged(ValueChangeEvent e) {

		String newLocaleValue = e.getNewValue().toString();

		// loop country map to compare the locale code
		for (Map.Entry<String, Object> entry : contries.entrySet()) {

			if (entry.getValue().toString().equals(newLocaleValue)) {

				FacesContext.getCurrentInstance().getViewRoot()
						.setLocale((Locale) entry.getValue());

			}
		}
	}
}
