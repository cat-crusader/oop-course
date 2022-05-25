package com.example.oop.servlets;

import com.example.oop.services.AuthorizationService;
import com.example.oop.services.DispatcherService;
import com.example.oop.services.TenantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.oop.exceptions.HttpException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/current-tenant")
public class CurrentTenantServlet extends HttpServlet {
    private final TenantService tenantService = new TenantService();
    private final AuthorizationService authorizationService = new AuthorizationService();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        if (authorizationService.notAuthorised(request, "tenant")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        try {
            Object result = tenantService.getCurrentTenant(request);
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
