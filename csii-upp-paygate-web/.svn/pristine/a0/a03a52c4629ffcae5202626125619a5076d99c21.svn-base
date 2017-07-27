package com.csii.upp.paygate.action.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.csii.ibs.util.ImageTokenGenAction;
import com.csii.ibs.util.TokenImgGenerator;
import com.csii.pe.accesscontrol.token.Token;
import com.csii.pe.accesscontrol.token.TokenManager;
import com.csii.pe.core.Context;
import com.csii.pe.core.SessionUpdatableContext;

public class ImageTokenGenExtAction extends ImageTokenGenAction {
	
	@Override
	protected Object getData(Context context) {
		
		Token token = tokenManager.createToken(context);
		if (context instanceof  SessionUpdatableContext) {
			((SessionUpdatableContext)context).setSessionAttribute("_ImgTokenName", token.getUniqueId());
		}
        try
        {
            ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
            TokenImgGenerator.createPic(bytearrayoutputstream, token.getUniqueId());
            return bytearrayoutputstream.toByteArray();
        } catch(IOException ioexception) {
            return token.getUniqueId();
        }
	}
	
	public void setTokenManager(TokenManager tokenmanager)
    {
        this.tokenManager = tokenmanager;
    }

    private TokenManager tokenManager;
}
