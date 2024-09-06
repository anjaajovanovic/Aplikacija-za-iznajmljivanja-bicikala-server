/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.repository.db.impl;

import rs.ac.bg.fon.ai.zajednicki.domain.AbstractDomainObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ai.server.repository.db.DBConnectionFactory;
import rs.ac.bg.fon.ai.server.repository.db.DBRepository;

/**
 *
 * @author PC
 */
public class DBRepositoryGeneric implements DBRepository<AbstractDomainObject>{

    @Override
    public List<AbstractDomainObject> getAll(AbstractDomainObject param, String uslov) throws Exception {
        List<AbstractDomainObject> list = new ArrayList<>();
        String upit = "SELECT * FROM " + param.getTableName() + " " + param.alijas() + " " + param.join() + " ";
        if(uslov != null){
            upit += uslov;
        }
        System.out.println(upit);
        Statement st = DBConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(upit);
        list = param.getList(rs);
        rs.close();
        st.close();
        
        return list;
    }

    @Override
    public PreparedStatement add(AbstractDomainObject param) throws Exception {
        String upit = "INSERT INTO " +param.getTableName()+ " (" +param.getColumnsForInsert()+ ") VALUES ("+param.getValuesForInsert()+ ")";
        System.out.println(upit);
        PreparedStatement ps = DBConnectionFactory.getInstance().getConnection().prepareStatement(upit);
        ps.executeUpdate(upit, Statement.RETURN_GENERATED_KEYS);
//        st.close();
        return ps;
    }

    @Override
    public void edit(AbstractDomainObject param) throws Exception {
        String upit = "UPDATE "+param.getTableName()+ " SET " + param.getValuesForUpdate() + " WHERE "+param.getPrimaryKey();
        System.out.println(upit);
        Statement st = DBConnectionFactory.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();
    }

    @Override
    public void delete(AbstractDomainObject param) throws Exception {
        String upit = "DELETE FROM "+param.getTableName()+" WHERE "+param.getPrimaryKey();
        System.out.println(upit);
        Statement st = DBConnectionFactory.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();
    }

    @Override
    public List<AbstractDomainObject> getAll() {
        return new ArrayList<>();
    }

    
}
