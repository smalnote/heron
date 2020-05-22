package com.github.smalnote.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.smalnote.app.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class AuthenticationController {
	private static final Logger LOG = LogManager.getLogger(AuthenticationController.class);

	private AtomicLong sessionID = new AtomicLong(201810000000000L);

	private Map<String, User> sessionManager = new ConcurrentHashMap<>();

	private static final String COOKIE_KEY = "X-Xsrf-Header";

	@PostMapping("/login")
	public void login(@RequestBody String body, HttpServletResponse response)
			throws IOException {
		LOG.info(body);
		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(body, User.class);

		String id = String.valueOf(sessionID.getAndIncrement());

		sessionManager.put(id, user);

		Cookie cookie = new Cookie(COOKIE_KEY, id);
		cookie.setPath("/");
		cookie.setMaxAge(30);
		response.addCookie(cookie);
	}

	@PostMapping("/greet")
	public void greet(@CookieValue(COOKIE_KEY) String sessionID, HttpServletResponse response) throws IOException {
		LOG.info("{} : {}", COOKIE_KEY, sessionID);
		if (sessionID == null || !sessionManager.containsKey(sessionID)) {
			response.setStatus(401);
			return;
		}

		User user = this.sessionManager.get(sessionID);
		OutputStream os = response.getOutputStream();
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(os, user);
	}

}
