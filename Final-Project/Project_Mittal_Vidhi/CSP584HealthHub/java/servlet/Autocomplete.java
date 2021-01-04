package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Product;
import util.SaxParserDataStore;

import java.util.Arrays;
import java.util.Iterator;

@WebServlet("/Autocomplete")
public class Autocomplete extends HttpServlet {


    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
	
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
    	SaxParserDataStore.addHashmap();
        String action = request.getParameter("action");
        String targetId = request.getParameter("id");
        StringBuffer sb = new StringBuffer();

        if (targetId != null) {
            targetId = targetId.trim().toLowerCase();
        } else {
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }

        boolean namesAdded = false;
        if (action.equals("complete")) {

            // check if user sent empty string
            if (!targetId.equals("")) {
            	int size = 0;
                Iterator it = SaxParserDataStore.products.keySet().iterator();
                while (it.hasNext()) {
                    String id = (String) it.next();
                    Product product = SaxParserDataStore.products.getOrDefault(id, new Product());
                    if (product.getName().toLowerCase().indexOf(targetId) >= 0 && size < 5) {
                        sb.append("<product>");
                        sb.append("<id>" + product.getId() + "</id>");
                        sb.append("<productName>" + product.getName() + "</productName>");
                        sb.append("</product>");
                        namesAdded = true;
                        size++;
                    }
                }
            }

            if (namesAdded) {
                response.setContentType("text/xml");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write("<products>" + sb.toString() + "</products>");
            } else {
                //nothing to show
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
        }

        if (action.equals("lookup")) {

            // put the target composer in the request scope to display 
            if ((targetId != null) && SaxParserDataStore.products.containsKey(targetId.trim())) {
                request.setAttribute("products", Arrays.asList(SaxParserDataStore.products.get(targetId)));
                request.getRequestDispatcher("/listProduct.jsp").forward(request, response);
            }
        }
    }
}
