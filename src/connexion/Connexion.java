package connexion;

import entities.Blog;
import service.BlogService;
import util.DataSource;

public class Connexion {

    public static void main(String[] args) {
        Blog blog1 = new Blog("My First Blog", "This is the content of my first blog.", "C:\\Users\\Youssef El Amri\\Documents\\PI-DEV\\images");
        BlogService blogService = new BlogService();

        // Add the new blog
        blogService.addBlog(blog1);

        // Read all blogs from the database and print them
        blogService.readAllBlogs().forEach(System.out::println);
    }
}
