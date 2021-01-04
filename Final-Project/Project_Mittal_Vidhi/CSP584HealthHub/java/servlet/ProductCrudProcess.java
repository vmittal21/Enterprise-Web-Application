package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import service.ProductCrudService;
import bean.Product;

@WebServlet("/ProductCrudProcess")
public class ProductCrudProcess extends HttpServlet {
    static ProductCrudService productCrudService = new ProductCrudService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("showNewForm")) {
            showNewForm(request, response);
        } else if (action.equals("addProduct")) {
            addProduct(request, response);
        } else if (action.equals("deleteProduct")) {
            deleteProduct(request, response);
        } else if (action.equals("showEditForm")) {
            showEditForm(request, response);
        } else if (action.equals("updateProduct")) {
            updateProduct(request, response);
        }

    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("productForm.jsp").forward(request, response);
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		//parse request
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String productId = "";
        String productName = "";
        String productPrice = "";
        String productImage = "";
        String productBrand = "";
        String productCondition = "";
        String productDiscount = "";
        String productCatagory = "";
        String productInventory = "";
    
		for(FileItem item: items) {
			if(item.isFormField()) {
				String name = item.getFieldName();
				String val = item.getString();
				switch (name) {
				case "productId":
					productId = val;
					break;
				case "productName":
					productName = val;
					break;
				case "productPrice":
					productPrice = val;
					break;
				case "productBrand":
					productBrand = val;
					break;
				case "productCondition":
					productCondition = val;
					break;
				case "productDiscount":
					productDiscount = val;
					break;
				case "productCatagory":
					productCatagory = val;
					break;
				case "productInventory":
					productInventory = val;
					break;
				default:
					break;
				}
			}else { //upload image file
				InputStream in = item.getInputStream();
				String fileName = item.getName();//   c:\dsf\a.jpg
				fileName = "new_" + fileName.substring(fileName.lastIndexOf("\\")+1);//a.jpg 
				//write image into directory
				productImage = fileName;
				String TOMCAT_HOME = System.getProperty("catalina.base");
				OutputStream out = new FileOutputStream(TOMCAT_HOME + ""
						+ "\\webapps\\CSP584HealthHub\\image\\products"+"\\"+fileName);
				byte b[] = new byte[1024];
				int len = -1;
				while((len=in.read(b))!=-1){
					out.write(b, 0, len);
					System.out.println(len);
				}
				out.close();
				in.close();
				item.delete();
			}
		}
        
        
        Product product = new Product(productId, productName, Double.parseDouble(productPrice), productImage,
                productBrand, productCondition, Double.parseDouble(productDiscount), productCatagory,
                Integer.parseInt(productInventory));
        productCrudService.add(product);
        response.sendRedirect("admin.jsp");
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productId = request.getParameter("id");
        productCrudService.delete(productId);
        response.sendRedirect("admin.jsp");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productId = request.getParameter("id");
        Product existingProduct = productCrudService.getProductObjById(productId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("productForm.jsp");
        request.setAttribute("product", existingProduct);
        dispatcher.forward(request, response);
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        //parse request
        List<FileItem> items = null;
        try {
            items = upload.parseRequest(request);
        } catch (FileUploadException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String productId = "";
        String productName = "";
        String productPrice = "";
        String productImage = "";
        String productBrand = "";
        String productCondition = "";
        String productDiscount = "";
        String productCatagory = "";
        String productInventory = "";
    
        for(FileItem item: items) {
            if(item.isFormField()) {
                String name = item.getFieldName();
                String val = item.getString();
                switch (name) {
                case "productId":
                    productId = val;
                    break;
                case "productName":
                    productName = val;
                    break;
                case "productPrice":
                    productPrice = val;
                    break;
                case "productBrand":
                    productBrand = val;
                    break;
                case "productCondition":
                    productCondition = val;
                    break;
                case "productDiscount":
                    productDiscount = val;
                    break;
                case "productCatagory":
                    productCatagory = val;
                    break;
                case "productInventory":
                    productInventory = val;
                    break;
                default:
                    break;
                }
            }else { //upload image file
                InputStream in = item.getInputStream();
                String fileName = item.getName();//   c:\dsf\a.jpg
                fileName = "new_" + fileName.substring(fileName.lastIndexOf("\\")+1);//a.jpg 
                //write image into directory
                productImage = fileName;
                String TOMCAT_HOME = System.getProperty("catalina.base");
                OutputStream out = new FileOutputStream(TOMCAT_HOME + ""
                        + "\\webapps\\CSP584HealthHub\\image\\products"+"\\"+fileName);
                byte b[] = new byte[1024];
                int len = -1;
                while((len=in.read(b))!=-1){
                    out.write(b, 0, len);
                    System.out.println(len);
                }
                out.close();
                in.close();
                item.delete();
            }
        }
        Product product = new Product(productId, productName, Double.parseDouble(productPrice), productImage,
                productBrand, productCondition, Double.parseDouble(productDiscount), productCatagory,
                Integer.parseInt(productInventory));
        productCrudService.update(product);
        response.sendRedirect("admin.jsp");
    }

}