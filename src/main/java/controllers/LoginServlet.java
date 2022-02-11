package controllers;

import dto.UserDTO;
import lombok.SneakyThrows;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/index")
public class LoginServlet extends HttpServlet {
    private static final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(ConstantsJSP.INDEX).forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Optional<UserDTO> userDTO = userService
                .login(
                        req.getParameter(ConstantsJSP.EMAIL),
                        req.getParameter(ConstantsJSP.PASSWORD)
                );
        if (userDTO.isPresent()) {
            req.getSession().setAttribute("user", userDTO);
            resp.sendRedirect("/WEB-INF/home.jsp");//не точно
        } else {
            resp.sendRedirect(ConstantsJSP.ERROR_MESSAGE + req.getParameter(ConstantsJSP.EMAIL));
        }
    }
}


