package com.ilyag9.sh.server;

import java.util.Date;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ilyag9.sh.client.GreetingService;
import com.ilyag9.sh.server.dao.RemarkDAO;
import com.ilyag9.sh.server.dao.entity.RemarkEntity;
import com.ilyag9.sh.shared.FieldVerifier;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
    GreetingService {

	
	RemarkDAO remarkDAO;
	
  public String greetServer(String input) throws IllegalArgumentException {
	  
	  if(remarkDAO==null) {
		  WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		  remarkDAO= applicationContext.getBean(RemarkDAO.class);
	  }
	  
    // Verify that the input is valid.
    if (!FieldVerifier.isValidName(input)) {
      // If the input is not valid, throw an IllegalArgumentException back to
      // the client.
      throw new IllegalArgumentException(
          "Name must be at least 4 characters long");
    }

    String serverInfo = getServletContext().getServerInfo();
    String userAgent = getThreadLocalRequest().getHeader("User-Agent");

    // Escape data from the client to avoid cross-site script vulnerabilities.
    input = escapeHtml(input);
    userAgent = escapeHtml(userAgent);
    RemarkEntity obj=new RemarkEntity();
    obj.setId(1L);
    obj.setText("yr");
    obj.setTags("ds");
    obj.setTitle("ss");
    obj.setPublishedDate(new Date());
    remarkDAO.create(obj);
    return "created, " + remarkDAO.get(obj.getId()).getText() + "!";
  }

  /**
   * Escape an html string. Escaping data received from the client helps to
   * prevent cross-site script vulnerabilities.
   *
   * @param html the html string to escape
   * @return the escaped string
   */
  private String escapeHtml(String html) {
    if (html == null) {
      return null;
    }
    return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(
        ">", "&gt;");
  }
}
