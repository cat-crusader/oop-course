package com.example.oop.servlets;

import com.example.oop.exceptions.HttpException;
import com.example.oop.services.ApplicationService;
import com.example.oop.services.AuthorizationService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/disable")
public class DisableServlet extends HttpServlet {
    private final ApplicationService applicationService = new ApplicationService();
    private final AuthorizationService authorizationService = new AuthorizationService();

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) {
        if (authorizationService.notAuthorised(request, "dispatcher")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        try {
            applicationService.disable(request);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (HttpException e) {
            response.setStatus(e.getHttpCode());
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
