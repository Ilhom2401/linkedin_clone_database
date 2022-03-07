import com.google.gson.Gson;
import model.Post;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {

        Gson gson = new Gson();
        ArrayList<Post> posts = new ArrayList<>();
        File file = new File("C:\\Users\\User\\Desktop\\1.rasm.jfif");
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bytes = fileInputStream.readAllBytes();

        Post post = new Post();

        post.setSize(0L);
        post.setName("Ilhom");
        post.setContent_type(".png");
        post.setContent(bytes);

        posts.add(post);

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/LINKEDIN","postgres","2401");
        PreparedStatement preparedStatement = connection.prepareStatement("select add_post_b2(?,?,?)");

        System.out.println(gson.toJson(posts));
        preparedStatement.setString(1,"POST TITLE");
        preparedStatement.setString(3,"7197d725-626f-4271-a6cd-a56839bf27f8");
        preparedStatement.setString(2,gson.toJson(posts));

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next())
            System.out.println(resultSet.getString("add_post_b2"));

        connection.close();
        fileInputStream.close();

    }
}