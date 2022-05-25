package com.example.oop.servlets;

import com.example.oop.exceptions.HttpException;
import com.example.oop.services.AuthorizationService;
import com.example.oop.services.DispatcherService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/current-dispatcher")
public class CurrentDispatcherServlet extends HttpServlet {
    private final DispatcherService dispatcherService = new DispatcherService();
    private final AuthorizationService authorizationService = new AuthorizationService();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        if (authorizationService.notAuthorised(request, "dispatcher")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        try {
            Object result = dispatcherService.getCurrentDispatcher(request);
            response.getWriter().print(objectMapper.writeValueAsString(result));
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (HttpException e) {
            response.setStatus(e.getHttpCode());
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
