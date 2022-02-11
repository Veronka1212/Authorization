package controllers;

import dto.CreateUserDTO;
import entity.Role;
import exeption.DaoException;
import exeption.ValidationException;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(value = "/registration", name = "RegistrationServlet")

public class RegistrationServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", Role.values());

        req.getRequestDispatcher(ConstantsJSP.REGISTRATION).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        CreateUserDTO createUserDTO = CreateUserDTO.builder()
                .name(req.getParameter("name"))
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .role(req.getParameter("role"))
                .build();
        try {
            userService.create(createUserDTO);
            resp.sendRedirect("/index");

        } catch (DaoException exception) {
            req.setAttribute("errorsDao", exception.getMessage());
            doGet(req, resp);
        } catch (ValidationException e) {
            req.setAttribute("errors", e.getErrors());
            doGet(req, resp);
        }
    }

}