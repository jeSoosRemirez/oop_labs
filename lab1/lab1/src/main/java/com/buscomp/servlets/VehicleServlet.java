package com.buscomp.servlets;

import com.buscomp.db.dao.DaoManager;
import com.buscomp.db.dao.PlaneDao;
import com.buscomp.db.entity.Entity;
import com.buscomp.db.entity.Plane;
import com.buscomp.parsers.JsonParser;
import com.buscomp.servlets.util.RequestPack;
import com.buscomp.servlets.util.RoleUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@WebServlet("/plane")
public class PlaneServlet extends HttpServlet {

    public Entity getEntity(String toParse){
        try {
            return JsonParser.parsePlane(toParse);
        } catch (Exception e) {
            return null;
        }
    }

    public void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        BufferedReader reader = req.getReader();
        String requestBodyString = RequestPack.processRequest(reader);
        if(!RoleUtil.validateAccess(RoleUtil.getRole(req), RoleUtil.getAllowedRoles(new String[]{RoleUtil.ADMIN, RoleUtil.DISPATCH}))){
            resp.getWriter().println("[]");
            return;
        }
        Entity entity = getEntity(requestBodyString);
        DaoManager DBM = new DaoManager();
        Connection conn = DBM.getConnection();
        PlaneDao dao = new PlaneDao(conn);
        if(entity != null) {
            try {
                dao.update((Plane) entity);
                resp.getWriter().println(JsonParser.toJsonObject(dao.readAll()));
            } catch (Exception e) {
                resp.getWriter().println("[]");
            }
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        BufferedReader reader = req.getReader();
        String requestBodyString = RequestPack.processRequest(reader);
        if(!RoleUtil.validateAccess(RoleUtil.getRole(req), RoleUtil.getAllowedRoles(new String[]{RoleUtil.ADMIN, RoleUtil.DISPATCH}))){
            resp.getWriter().println("[]");
            return;
        }
        Entity entity = getEntity(requestBodyString);
        if(entity == null){
            resp.getWriter().println("[]");
            return;
        }
        entity.setId(UUID.randomUUID().toString());
        DaoManager DBM = new DaoManager();
        Connection conn = DBM.getConnection();
        PlaneDao dao = new PlaneDao(conn);
        try {
            dao.create((Plane) entity);
        } catch (Exception e) {
            resp.getWriter().println("[]");
            return;
        }
        try {
            resp.getWriter().println(JsonParser.toJsonObject(dao.readAll()));
        } catch (Exception e) {
            resp.getWriter().println("[]");
        }
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String field = req.getParameter("field");
        String value = req.getParameter("value");
        if(field == null || value == null){
            resp.getWriter().println("[]");
            return;
        }
        String role = RoleUtil.getRole(req);
        DaoManager mgr = new DaoManager();
        Connection conn = mgr.getConnection();
        if(conn == null){
            resp.getWriter().println("[]");
            return;
        }
        PlaneDao dao = new PlaneDao(conn);
        List<Entity> entityList = new ArrayList<>();
        try {
            if(RoleUtil.validateAccess(role, RoleUtil.getAllowedRoles(new String[]{RoleUtil.USER, RoleUtil.DISPATCH, RoleUtil.ADMIN}))) {
                switch (field) {
                    case "model":
                        entityList = dao.readByModel(value);
                        break;
                    case "id":
                        entityList.add(dao.read(value));
                        break;
                    case "all":
                        entityList = dao.readAll();
                        break;
                    default:
                        if (RoleUtil.validateAccess(role, RoleUtil.getAllowedRoles(new String[]{RoleUtil.USER, RoleUtil.DISPATCH, RoleUtil.ADMIN}))) {
                            switch (field) {
                                case "delete":
                                    dao.delete(value);
                                    entityList = dao.readAll();
                                    break;
                                default:
                                    resp.getWriter().println("[]");
                                    return;
                            }

                        }
                        else {
                            resp.getWriter().println("[]");
                            return;
                        }
                }
            }
        }catch (Exception e){
            resp.getWriter().println("[]");
            return;
        }
        try {
            String res = JsonParser.toJsonObject(entityList);
            resp.getWriter().println(res);
        } catch (Exception e) {
            resp.getWriter().println("[]");
            return;
        }
    }
}
