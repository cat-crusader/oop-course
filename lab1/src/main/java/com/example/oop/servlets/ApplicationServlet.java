package com.example.oop.servlets;

import com.example.oop.exceptions.HttpException;
import com.example.oop.services.ApplicationService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/applications")
public class ApplicationServlet extends HttpServlet {
    private final ApplicationService applicationService = new ApplicationService();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            Object result = applicationService.findAll();
            response.getWriter().print(objectMapper.writeValueAsString(result));
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (HttpException e) {
            response.setStatus(e.getHttpCode());
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            applicationService.insert(request);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (HttpException e) {
            response.setStatus(e.getHttpCode());
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
