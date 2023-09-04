/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.ws;

import com.mycompany.bookstore.dao.Book;
import com.mycompany.bookstore.dao.BookDAO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 *
 * @author DELL
 */

//class phục vụ data thô cho các app khác
//những class khác thì cứ java web bth
//riêng package ws chỉ để trả về data thô
//có quyền gọi DAO/DTO như bth
//làm sao gọi được qua tomcat , do Tomcat phục vụ chung cho Java Web App
//ta phải url mapping riêng
//thường quy ước như sau: http://ten-mien.com -> đi vào web app bth (localhost..)
//http://ten-mien.com/api/ -> gọi api của mình
//gọi các class nằm trong package ws để phục vụ data thô JSON
//phần cấu hình mapping url/api ánh xạ vào class trong ws thì được khai báo
//ở file mapping web.xml

//api là đến được package ws. Vấn đề là đến class nào để tính tiếp thì ta phải
//chỉ ra đường dẫn phụ qua từ Annotation @Path - thư viện Jersey

//api/books

@Path("books")
public class BookWS {
    
    BookDAO dao = BookDAO.getInstance();
    
    @Context //đây là cách Jersey giao tiếp với Tomcat
    UriInfo ui; //đây là biến sẽ giao tiếp với Tomcat, do Tomcat vẫn 
                //quản lý Web App của mình
    
    
    
// @GET: 100% giống duyệt web, gọi hàm qua url, nhưng trả về JSON
//mặc định có 1 hàm GET được dùng làm mặc định, cứ truy cập vào class này qua
//...api/books thì sẽ gọi hàm GET này còn lại thì đặt cho nó 1 đường dẫn phụ
//như .../api/books/đường-dẫn-phụ
    @GET
    @Path("hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHelloFromServer() {
        return "Hey, this is 1st message comes from my own first API";
    }
    
    @GET
    @Path("abook")
    @Produces(MediaType.APPLICATION_JSON)
    public Book getBook() {
        return new Book("8788427845641", "Đừng Chạy Theo Số Đông", "Kiên Trần", 1, 2020);
    }

    //default
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<Book> GetBookList() {
//        List<Book> list = new ArrayList<>();
//        list.add(new Book("8788427845641", "Đừng Chạy Theo Số Đông", "Kiên Trần", 1, 2020));
//        list.add(new Book("6911225907262", "Tuổi Trẻ Đáng Giá Bao Nhiêu", "Rosie Nguyễn", 2, 2018));
//        return list;
//    }
    
    //default
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getBooks() {
        return dao.getAll();
    }
    
    //tìm 1 cuốn sách theo mã số
    //truyền tham số qua lời gọi API, giống tham số hàm, đưa mã số
    //cuốn sách vào
    @GET
    @Path("{isbn}") //tham số truyền vào api
    // url: ...api/books/ms-cần-tìm
    @Produces(MediaType.APPLICATION_JSON)
    public Book getABookByIsbn(@PathParam("isbn") String isbn) {
        return dao.getOne(isbn);
    }
    
    //POST
    @POST //hàm này nhận vào 1 chuỗi JSON
    //insert vào database
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addABook(Book book) throws URISyntaxException {
        String isbn = dao.addOne(book); //nếu insert thành công thì return isbn
                                        //vừa insert
        //mặc định khi insert thành công, status 201
        URI uri = new URI(ui.getBaseUri() + "books/" + isbn); 
        //tạo URL API GET /books/ms-sách-vừa-tạo
        
        if(isbn == null) return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        return Response.created(uri).build(); //trả về kèm theo url của cuốn sách mới
    }
}
