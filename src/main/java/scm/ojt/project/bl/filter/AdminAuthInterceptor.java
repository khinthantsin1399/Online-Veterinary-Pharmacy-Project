package scm.ojt.project.bl.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import scm.ojt.project.bl.service.AuthService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <h2>AdminAuthInterceptor Class</h2>
 * <p>
 * Process for Displaying AdminAuthInterceptor
 * </p>
 * 
 * @author KyawHtet
 *
 */
@SuppressWarnings("deprecation")
public class AdminAuthInterceptor extends HandlerInterceptorAdapter {
	/**
	 * <h2>authService</h2>
	 * <p>
	 * authService
	 * </p>
	 */
	@Autowired
	private AuthService authService;

	/**
	 * <h2>preHandle</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	        throws Exception {
		if (!authService.isLoggedIn()) {
			response.sendRedirect(request.getContextPath() + "/login");
		} else if (authService.getLoggedInUser().getType() == "0") {
			System.out.println(authService.getLoggedInUser().getType());
			response.sendRedirect(request.getContextPath() + "/denied");
		}
		return true;
	}
}