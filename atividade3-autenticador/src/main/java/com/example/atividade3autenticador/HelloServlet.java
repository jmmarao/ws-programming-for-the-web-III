package com.example.atividade3autenticador;

import java.io.*;
import javax.servlet.ServletContext;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/index.html")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        Integer count = 0;
        ServletContext context = getServletContext();
        context.setAttribute("count", count);
        message = "Contador de Acesso e Autenticador de Usuário";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("<form action=\"index.html\" method=\"POST\">");
        out.println("<label for=\"username\">Usuário</label>");
        out.println("<input id=\"username\" type=\"text\" name=\"username\"><br><br>");
        out.println("<label for=\"password\">Senha</label>");
        out.println("<input id=\"password\" type=\"password\" name=\"password\"><br><br>");
        out.println("<input type=\"submit\" value=\"Enviar\">");
        out.println("</form>");
        out.println("</body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String usernameTest = "usuario";
        String passwordTest = "senha";

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        ServletContext context = getServletContext();
        Integer count = (Integer) context.getAttribute("count");
        count++;
        context.setAttribute("count", count);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (username.equals(usernameTest) && password.equals(passwordTest)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            out.println("<html><body>");
            out.println("<h1>Bem-vindo " + username + "</h1>");
            out.println("<h2>Essa página foi acessada " + count + " vezes</h2>");
            out.println("<a href=\"ServletLogout\">Logout</a>");
            out.println("</body></html>");
        }
        else {
            out.println("<html><body>");
            out.println("<h1>Usuário não encontrado! Tente novamente</h1>");
            out.println("<h2>Essa página foi acessada " + count + " vezes</h2>");
            out.println("<a href=\".\">Voltar</a>");
            out.println("</body></html>");
        }
    }
}