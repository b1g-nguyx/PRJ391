/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ProductDAO;
import dal.Product_CategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Product;
import model.Product_Category;

/**
 *
 * @author nguye
 */
public class ListProducts extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListProducts</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListProducts at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        Product_CategoryDAO pcdao = new Product_CategoryDAO();
        ProductDAO pdao = new ProductDAO();
        String pc = request.getParameter("pc");
        String indexP = request.getParameter("index");
        int index = 1;
        if (indexP != null) {
            index = Integer.parseInt(indexP);
        }
        if (pc != null) {
            
            int count = pdao.getCount(pc);
            int endPage = count / 12;
            if (count % 12 != 0) {
                endPage++;
            }
            request.setAttribute("tag", index);
            request.setAttribute("pcc", pcdao.getCategory(pc));
            request.setAttribute("listP", pdao.getPaging(pc, index));
            request.setAttribute("endP", endPage);
        }
        String search = request.getParameter("search");
        List<Product> listSearch = new ArrayList<>();
        
        if (search != null) {
            if (!search.trim().isBlank()) {
                for (Product product : pdao.getAll()) {
                    if (product.getName().toUpperCase().contains(search.trim().toUpperCase()) || product.getDescriotion().toLowerCase().contains(search.trim().toLowerCase())) {
                        listSearch.add(product);
                    }
                }
            } else {
                
            }
        }
        
        String max = request.getParameter("max");
        String min = request.getParameter("min");
        String checkprice = request.getParameter("checkprice");
        List<Product> listPrice = new ArrayList<>();
        
        if (checkprice != null) {
            if (max !=null) {
                if (min.isEmpty()) {
                    min = "0";
                }if (max.isEmpty()) {
                    max = "0";
                }
                float minp = Float.parseFloat(min);
                float maxp = Float.parseFloat(max);
                listPrice = pdao.findPrice(minp, maxp);
            } else {
                request.setAttribute("ranger", '0');
                request.getRequestDispatcher("product.jsp").forward(request, response);
            }
        }
        if (checkprice != null || search != null) {
            
            if (listPrice.size() > 0 && listSearch.size() > 0) {
                List<Product> list_temp = new ArrayList<>();
                
                for (Product product : listPrice) {
                    for (Product product1 : listSearch) {
                        if (product.getId().equals(product1.getId())) {
                            list_temp.add(product);
                            break;
                        }
                    }
                }
                List<Product> listAll = new ArrayList<>();
                index = (index - 1) * 12;
                int size = (list_temp.size() < index + 12) ? list_temp.size() : index + 12;
                for (int i = index; i < size; i++) {
                    listAll.add(list_temp.get(i));
                }
                int count = list_temp.size();
                int endPage = count / 12;
                if (count % 12 != 0) {
                    endPage++;
                }
                request.setAttribute("tag", index / 12 + 1);
                request.setAttribute("endP", endPage);
                request.setAttribute("all", listAll);
                request.setAttribute("sr", search);
            } else if (listPrice.size() > 0 && listSearch.size() == 0) {
                
                List<Product> listAll = new ArrayList<>();
                index = (index - 1) * 12;
                int size = (listPrice.size() < index + 12) ? listPrice.size() : index + 12;
                for (int i = index; i < size; i++) {
                    listAll.add(listPrice.get(i));
                }
                int count = listPrice.size();
                int endPage = count / 12;
                if (count % 12 != 0) {
                    endPage++;
                }
                request.setAttribute("tag", index / 12 + 1);
                request.setAttribute("endP", endPage);
                request.setAttribute("all", listAll);
                request.setAttribute("sr", search);
            } else if (listPrice.size() == 0 && listSearch.size() > 0) {
                List<Product> listAll = new ArrayList<>();
                index = (index - 1) * 12;
                int size = (listSearch.size() < index + 12) ? listSearch.size() : index + 12;
                for (int i = index; i < size; i++) {
                    listAll.add(listSearch.get(i));
                }
                int count = listSearch.size();
                int endPage = count / 12;
                if (count % 12 != 0) {
                    endPage++;
                }
                request.setAttribute("tag", index / 12 + 1);
                request.setAttribute("endP", endPage);
                request.setAttribute("all", listAll);
                request.setAttribute("sr", search);
            } else {
                List<Product> a = new ArrayList<>();
                request.setAttribute("all", a);
            }
        }
        
        request.getRequestDispatcher("product.jsp").forward(request, response);
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
