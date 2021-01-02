/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Book;
import entity.History;
import entity.Reader;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.BookFacade;
import session.HistoryFacade;
import session.ReaderFacade;

/**
 *
 * @author artur
 */
@WebServlet(name = "MyServlet", urlPatterns = {
    "/addBook",
    "/createBook",
    "/listBooks",
    "/addReader",
    "/createReader",
    "/listReaders",
    "/takeOnBookForm",
    "/takeOnBook",
    "/returnBookForm",
    "/returnBook",
    
        
})
public class MyServlet extends HttpServlet {
    @EJB //enterprise JavaBeans
    private BookFacade bookFacade; //поле bookFacade
    @EJB
    private ReaderFacade readerFacade;
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
                
            case "/addReader":
                request.getRequestDispatcher("/WEB-INF/addReaderForm.jsp").forward(request, response);
                break;
                
            case "/createReader":
                name = request.getParameter("name");
                String lastname = request.getParameter("lastname");
                String phone = request.getParameter("phone");
                
                if ("".equals(name) || name ==  null 
                        || "".equals(lastname) || lastname == null
                        || "".equals(phone) || phone == null){ 
                    
                    request.setAttribute("info", "Пожалуйста заполните все поля формы!");
                    request.setAttribute("name",name);
                    request.setAttribute("author",lastname);
                    request.setAttribute("publishedYear",phone);
                    
                    request.getRequestDispatcher("/WEB-INF/addReaderForm.jsp").forward(request, response);
                }
                
                Reader reader= new Reader(name, lastname, phone);
                readerFacade.create(reader); //сохраняем читателя в базу данных
                request.setAttribute("info","Добавлена читатель: " +reader.toString() );
                request.getRequestDispatcher("/index.jsp").forward(request, response);          
                break;
                
            case "/listReaders":
                List<Reader> listReaders = readerFacade.findAll();
                request.setAttribute("listReaders", listReaders);
                request.getRequestDispatcher("/WEB-INF/listReaders.jsp").forward(request, response);
                break;
          
            case "/listBooks":
                List<Book> listBooks = bookFacade.findAll();
                request.setAttribute("listBooks", listBooks);
                request.getRequestDispatcher("/WEB-INF/listBooks.jsp").forward(request, response);
                break;
                
            case "/takeOnBookForm":
                listReaders = readerFacade.findAll();
                request.setAttribute("listReaders", listReaders);
                listBooks = bookFacade.findAll();
                request.setAttribute("listBooks", listBooks);
                request.getRequestDispatcher("/WEB-INF/takeOnBookForm.jsp").forward(request, response);
                break;
                
            case "/takeOnBook":
                String bookId = request.getParameter("bookId");
                book = bookFacade.find(Long.parseLong(bookId));
                String readerId = request.getParameter("readerId");
                reader = readerFacade.find(Long.parseLong(readerId));
                History history = new History(book, reader, new GregorianCalendar().getTime(), null);
                historyFacade.create(history);
                request.setAttribute("info","Добавлена выдана");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
                
            case "/returnBookForm":
                List<History> listHistoriesWithReadBook = historyFacade.findHistoriesWithReadBook();
                request.setAttribute("listHistoriesWithReadBook", listHistoriesWithReadBook);
                request.getRequestDispatcher("/WEB-INF/returnBookForm.jsp").forward(request, response);
                break;
            case "/returnBook":
                String historyId = request.getParameter("historyId");
                if("".equals(historyId) || historyId == null || historyId == "-1"){
                    request.setAttribute("info", "Выберите книгу");
                    request.getRequestDispatcher("/returnBookForm").forward(request, response);
                    break;
                }
                history = historyFacade.find(Long.parseLong(historyId));
                history.setReturnDate(new GregorianCalendar().getTime());
                historyFacade.edit(history);
                request.setAttribute("info","Добавлена возвращена");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                
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
