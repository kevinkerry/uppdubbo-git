package com.csii.upp.paygate.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.csii.hercules.password.html.HTMLMaker;
import com.csii.hercules.password.image.ImageInfo;
import com.csii.hercules.password.image.maker.AlphabetPasswordImageMaker;
import com.csii.hercules.password.image.maker.NumberPasswordImageMaker;
import com.csii.hercules.password.utils.Utils;

/**
 * Servlet implementation class PasswordServlet
 */
public class PasswordServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private int width=640;
       
    /**
     * @see javax.servlet.http.HttpServlet#HttpServlet()
     */
    public PasswordServlet() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		String w = req.getParameter("width");
		if(w!=null){
			try {
				width=Integer.parseInt(w);
			} catch (NumberFormatException e) {
				
			}
		}
		try {
	//		String fullkbdhtml = makeFullKeyboard(session);
			String numkbdhtml = makeNumberKeyboard(session);
//			String jj = numkbdhtml;
			
			resp.getOutputStream().write(numkbdhtml.getBytes());
			resp.getOutputStream().flush();
			resp.getOutputStream().close();
		} catch (Exception e) {
			throw new ServletException(e);
		}
		
	}

	private String makeFullKeyboard(HttpSession session) throws Exception {

		char[] alphabet=Utils.getChars();
		
		AlphabetPasswordImageMaker lowercaseMaker=new AlphabetPasswordImageMaker();
		lowercaseMaker.setUppercase(false);
		ImageInfo lowercaseImage = lowercaseMaker.make(width, false,alphabet);
		HTMLMaker lowercaseHtmlMaker=new HTMLMaker(width, false,lowercaseImage, "LowercaseDiv");
		String lowercaseHtml=lowercaseHtmlMaker.toHtml();
		session.setAttribute("Lowercase", lowercaseHtmlMaker.getKeyIdnex());
		
		AlphabetPasswordImageMaker uppercaseMaker=new AlphabetPasswordImageMaker();
		uppercaseMaker.setUppercase(true);
		ImageInfo uppercaseImage = uppercaseMaker.make(width, false,alphabet);
		HTMLMaker uppercaseHtmlMaker=new HTMLMaker(width, false,uppercaseImage, "UppercaseDiv");
		String uppercaseHtml=uppercaseHtmlMaker.toHtml();
		session.setAttribute("Uppercase", uppercaseHtmlMaker.getKeyIdnex());
		
		char[] alphabetnum=Utils.getNumbers();
		NumberPasswordImageMaker maker=new NumberPasswordImageMaker();
		ImageInfo numberImage = maker.make(width, false, alphabetnum);
		HTMLMaker numberHtmlMaker=new HTMLMaker(width, false,numberImage, "NumberDiv");
		String numberHtml=numberHtmlMaker.toHtml();
		session.setAttribute("Number", numberHtmlMaker.getKeyIdnex());
		
		return lowercaseHtml+uppercaseHtml+numberHtml;
	}
	
	private String makeNumberKeyboard(HttpSession session) throws Exception {
		char[] alphabet=Utils.getNumbers();
		NumberPasswordImageMaker maker=new NumberPasswordImageMaker();
		ImageInfo image = maker.make(width, true, alphabet);
		HTMLMaker htmlMaker=new HTMLMaker(width, true,image, "NumberDiv");
		String html=htmlMaker.toHtml();
		session.setAttribute("Number", htmlMaker.getKeyIdnex());
		return html;
	}
}
