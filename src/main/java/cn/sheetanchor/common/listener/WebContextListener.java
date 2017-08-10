package cn.sheetanchor.common.listener;

import cn.sheetanchor.sparrow.sys.service.impl.SystemServiceImpl;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

public class WebContextListener extends org.springframework.web.context.ContextLoaderListener {
	
	@Override
	public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
		if (!SystemServiceImpl.printKeyLoadMessage()){
			return null;
		}
		return super.initWebApplicationContext(servletContext);
	}
}
