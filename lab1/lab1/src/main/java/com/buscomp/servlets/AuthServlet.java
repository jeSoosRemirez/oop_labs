package com.buscomp.servlets;
import com.buscomp.db.dao.DaoManager;
import com.buscomp.db.dao.UserDao;
import com.buscomp.db.entity.Password;
import com.buscomp.db.entity.User;
import com.buscomp.parsers.JsonParser;
import com.buscomp.servlets.token.Token;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.mindrot.jbcrypt.BCrypt;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.UUID;


@WebServlet("/auth")
public class AuthServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String login = req.getParameter("login");
        String pwd = req.getParameter("password");
        String email = req.getParameter("email");
        if(login == null || pwd == null || email == null){
            resp.getWriter().println("[]");
            return;
        }
        DaoManager mgr = new DaoManager();
        Connection conn = mgr.getConnection();
        UserDao dao = new UserDao(conn);
        User user = null;
        Password pass = null;
        try {
            user = (User) dao.readByEmail(email);
            if(user == null){
                throw new Exception();
            }
            pass = dao.getPassword(user.getId());
            String hashedPass = BCrypt.hashpw(pwd, pass.getSalt());
            if(!hashedPass.equals(pass.getPassword())){
                throw new Exception();
            }
        } catch (Exception e) {
            resp.getWriter().println("[]");
            return;
        }

        Algorithm algorithm = Algorithm.HMAC256("baeldung");
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("Baeldung")
                .build();
        String jwt = JWT.create()
                .withIssuer("Baeldung")
                .withClaim("id", user.getId())
                .withClaim("login", user.getLogin())
                .withClaim("email", user.getEmail())
                .withClaim("role", user.getRole())
                .sign(algorithm);

        Token token = new Token(jwt);
        try {
            String userJson = JsonParser.toJsonObject(token);
            resp.getWriter().println(userJson);
        } catch (Exception e) {
            resp.getWriter().println("[]");
        }
    }
}
