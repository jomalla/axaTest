package com.axa.jordi.test.portlet;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.BaseFilter;


@Component(
		immediate = true,
		property = {
				"servlet-context-name=",
				"servlet-filter-name=LogNameFilter",
				"url-pattern=/web/*",
				"url-pattern=/group/*",
				"url-pattern=/user/*",
		},
		service = Filter.class
)
public class LogNameFilter extends BaseFilter {

	private static final String LAST_PAGE_VISITED_KEY = "lastPageVisited";

	@Override
	protected void processFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws Exception {

		//Obtain the current URL and where we're pointing at
		final String originPage = request.getHeader(WebKeys.REFERER);
		final String destinationPage = request.getRequestURL().toString();

		//Look if there's any page visited in the session
		String sessionPage = "";
		if (request.getSession().getAttribute(LAST_PAGE_VISITED_KEY) != null){
			sessionPage = request.getSession().getAttribute(LAST_PAGE_VISITED_KEY).toString();
		}

		//Define the last page we've visited: if we're at the same as we know or if we navigated
		String lastPageVisited = "";

		if ((originPage == null) || (!originPage.equalsIgnoreCase(destinationPage) && !sessionPage.equalsIgnoreCase(destinationPage))) {
			lastPageVisited = destinationPage;
		}

		//If the page that we're looking at is not the same as we had, print in the log and save in session
		if (!"".equalsIgnoreCase(lastPageVisited) && !lastPageVisited.equalsIgnoreCase(sessionPage)){
			request.getSession().setAttribute(LAST_PAGE_VISITED_KEY, lastPageVisited);
			_log.info("Pagina visitada: " + lastPageVisited);
		}

		super.processFilter(request, response, filterChain);
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	private static final Log _log = LogFactoryUtil.getLog(LogNameFilter.class);
}