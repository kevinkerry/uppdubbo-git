package com.csii.upp.paygate.action.common;

import com.csii.pe.action.Action;
import com.csii.pe.action.Executable;
import com.csii.pe.core.Context;
import com.csii.pe.core.PeException;
import com.csii.pe.validation.ValidationException;

/**
 * 图形验证码校验action
 * @author Helm
 *
 */
public class ImageTokenVerifyAction implements Executable, Action {
	private boolean ignoreCase;
	
	/**
	 * 只做验证码是否正确校验，不做超时校验
	 */
	public void execute(Context context) throws PeException {
		String token = (String) context.getSessionAttribute("_ImgTokenName");
		String imageTokenId = (String)context.getData("_vTokenName");
		System.out.println();
		if (token != null && imageTokenId != null) {
			if (this.ignoreCase) {
				if (imageTokenId.equalsIgnoreCase(token)) {
					return;
				}
			} else {
				if (imageTokenId.equals(token)) {
					return;
				}
			}
			
		}
		throw new ValidationException("validation.input_mistake_token");
		
	}

	public boolean isIgnoreCase() {
		return ignoreCase;
	}

	public void setIgnoreCase(boolean ignoreCase) {
		this.ignoreCase = ignoreCase;
	}
	
	
	
	
}
