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
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.BookFacade;
import session.HistoryFacade;
import session.ReaderFacade;
import session.UserFacade;

/**
 *
 * @author artur
 */
@WebServlet(name = "ManagerServlet", urlPatterns = {
    "/addBook",
    "/createBook",
    "/editBookForm",
    "/editBook",
    "/listReaders",
})
public class ManagerServlet extends HttpServlet {
    @EJB
    private UserFacade userFacade;
    @EJB
    private ReaderFacade readerFacade;
    @EJB
    private BookFacade bookFacade;
    @EJB    
    private HistoryFacade historyFacade;
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
        
        HttpSession session = request.getSession(false);
        if (session == null ) {
            request.setAttribute("info", "У вас нет прав для этого ресурса. Войдите в систему");
            request.getRequestDispatcher("/WEB-INF/showLoginForm.jsp").forward(request, response);
            return;
        }
        
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            request.setAttribute("info", "У вас нет прав для этого ресурса. Войдите в систему");
            request.getRequestDispatcher("/WEB-INF/showLoginForm.jsp").forward(request, response);            
            return;
        }
        
        
        String path = request.getServletPath(); //вернет запрос, который идет после названме контекста
        
        switch (path) {
            case "/addBook":
                request.getRequestDispatcher("/WEB-INF/addBookForm.jsp").forward(request, response); //forward означает что после выполнения
                //надо отдать requset и response дальше в контейнер
                break;
            case "/createBook":
                String name = request.getParameter("name"); //достаем данные из html формы addBookForm
                String author = request.getParameter("author");
                String publishedYear = request.getParameter("publishedYear");
                
                
                if("".equals(name) || name == null 
                        || "".equals(author) || author == null
                        || "".equals(publishedYear) || publishedYear == null){
                
                    request.setAttribute("info","Пожалуйста заполните все поля формы");
                    request.setAttribute("name",name); //возвращаем поля, которые были заполнены
                    request.setAttribute("author",author);
                    request.setAttribute("publishedYear",publishedYear);
                    
                    request.getRequestDispatcher("/WEB-INF/addBookForm.jsp").forward(request, response);
                    break;
                }
                
                Book book = new Book(name, author, Integer.parseInt(publishedYear));
                bookFacade.create(book); //сохраняет созданную книгу в базе данных
                //сообщаем индекс странице, что данные получены
                request.setAttribute("info","Добавлена книга: " +book.toString()  );
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
                
                
            case "/editBookForm":
                String bookId = request.getParameter("bookId");
                book = bookFacade.find(Long.parseLong(bookId));
                request.setAttribute("book", book);
                request.getRequestDispatcher("/WEB-INF/editBookForm.jsp").forward(request, response);
                break;
                
            case "/editBook": 
                bookId = request.getParameter("bookId");
                book = bookFacade.find(Long.parseLong(bookId));
                name = request.getParameter("name");
                author = request.getParameter("author");
                publishedYear = request.getParameter("publishedYear");
                if("".equals(name) || name == null 
                        || "".equals(author) || author == null
                        || "".equals(publishedYear) || publishedYear == null){
                    request.setAttribute("info","Поля не должны быть пустыми");
                    request.setAttribute("bookId", book.getId());
                    request.getRequestDispatcher("/editBookForm").forward(request, response);
                    break; 
                }
                book.setName(name);
                book.setAuthor(author);
                book.setPublishedYear(Integer.parseInt(publishedYear));
                bookFacade.edit(book);
                request.setAttribute("info","Книга отредактирована");
                request.setAttribute("bookId", book.getId());
                request.getRequestDispatcher("/editBookForm").forward(request, response);         
                break;                                   
                             
            case "/listReaders":
                List<Reader> listReaders = readerFacade.findAll();
                request.setAttribute("listReaders", listReaders);
                request.getRequestDispatcher("/WEB-INF/listReaders.jsp").forward(request, response);
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
