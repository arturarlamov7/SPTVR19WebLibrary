/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Book;
import entity.Reader;
import entity.User;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.BookFacade;
import session.ReaderFacade;
import session.UserFacade;

/**
 *
 * @author artur
 */
@WebServlet(name = "LoginServlet", urlPatterns = {
    "/showLoginForm",
    "/logout",
    "/login",
    "/addReader",
    "/createReader",
    "/listBooks",
})
public class LoginServlet extends HttpServlet {
    @EJB
    private UserFacade userFacade;
    @EJB
    private ReaderFacade readerFacade;
    @EJB
    private BookFacade bookFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8"); 
        String path = request.getServletPath(); //вернет запрос, который идет после названме контекста
        
        switch (path) {
            case "/showLoginForm":
                request.getRequestDispatcher("/WEB-INF/showLoginForm.jsp").forward(request, response); 
                break;
                
            case "/login":
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                User user = userFacade.findByLogin(login);
                if(user == null){
                    request.setAttribute("info", "Нет такого пользователя или неправильный пароль");
                    request.getRequestDispatcher("/showLoginForm").forward(request, response);
                    break;
                }
                if(!password.equals(user.getPassword())){
                     request.setAttribute("info", "Нет такого пользователя или неправильный пароль");
                    request.getRequestDispatcher("/showLoginForm").forward(request, response);
                    break;
                }
                HttpSession session = request.getSession(true);
                session.setAttribute("user", user);
                request.setAttribute("info", "Вы вошли! :)");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
                
            case "logout":
                session = request.getSession(false);
                if (session != null) {
                    session.invalidate();                 
                }
                request.setAttribute("info", "Вы вышли!");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
                
                        case "/addReader":
                request.getRequestDispatcher("/WEB-INF/addReaderForm.jsp").forward(request, response);
                break;
                
            case "/createReader":
                String name = request.getParameter("name");
                String lastname = request.getParameter("lastname");
                String phone = request.getParameter("phone");
                login = request.getParameter("login");
                password = request.getParameter("password");
                
                if ("".equals(name) || name ==  null 
                        || "".equals(lastname) || lastname == null
                        || "".equals(phone) || phone == null
                        || "".equals(login) || login == null
                        || "".equals(password) || password == null                        
                        ){ 
                    
                    request.setAttribute("info", "Пожалуйста заполните все поля формы!");
                    request.setAttribute("name",name);
                    request.setAttribute("lastname",lastname);
                    request.setAttribute("phone",phone);
                    request.setAttribute("login", login);
                    request.setAttribute("password", password);
                    
                    request.getRequestDispatcher("/addReaderForm").forward(request, response);
                }
                
                Reader reader= new Reader(name, lastname, phone);
                readerFacade.create(reader); //сохраняем читателя в базу данных
                user = new User(login, password, reader);
                userFacade.create(user);
                request.setAttribute("info","Добавлена читатель: " +reader.toString() );
                request.getRequestDispatcher("/index.jsp").forward(request, response);          
                break;
                
            case "/listBooks":
                List<Book> listBooks = bookFacade.findAll();
                request.setAttribute("listBooks", listBooks);
                request.getRequestDispatcher("/WEB-INF/listBooks.jsp").forward(request, response);
                break;
        
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
