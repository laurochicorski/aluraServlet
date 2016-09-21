package br.com.alura.gerenciador.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns="/*")
public class FiltroDeAuditoria implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		
		Cookie cookie = getUsuario(req);
		String usuario = "<deslogado>";
		
		if(cookie != null){
			usuario = cookie.getValue();
		
			System.out.println("Usuário acessando a URI: " + req.getRequestURI());
			chain.doFilter(request, response);
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	private Cookie getUsuario(HttpServletRequest req){
		Cookie[] cookies = req.getCookies();
		
		if(cookies != null){
			for(Cookie cookie : cookies){
				if(cookie.getName().equals("usuario.logado")){
					return cookie;
				}
			}
		}
		
		return null;
	}

	
}
