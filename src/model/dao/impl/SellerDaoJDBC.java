package model.dao.impl;

import db.DB;
import db.DBException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;


public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {

    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "SELECT "
                            + "s.*, d.Name AS DepName "
                            + "FROM seller s "
                            + "INNER JOIN department d ON s.DepartmentId = d.Id "
                            + "WHERE s.Id = ?"
            );

            st.setInt(1, id);

            rs = st.executeQuery();

            if(rs.next()){
                Department dep = instantiateDepartment(rs);
                return instantiateSeller(rs , dep);

            } else {
                return null;
            }

        } catch (SQLException e){
            throw new DBException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    public List<Seller> findByDepartment(Integer depId){
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement(
                    "SELECT "
                            + "s.*, d.Name AS DepName "
                            + "FROM seller s "
                            + "INNER JOIN department d ON s.DepartmentId = d.Id "
                            + "WHERE s.DepartmentId = ? "
                            + "ORDER BY s.Name"
            );

            st.setInt(1, depId);

            rs = st.executeQuery();

            List<Seller> list = new ArrayList<>();

            Map<Integer, Department> map = new HashMap<>();

            while (rs.next()){
                Department dep = map.get(rs.getInt("DepartmentId"));
                if(dep == null){
                    dep = instantiateDepartment(rs);
                    map.put(depId, dep);
                }
                list.add(instantiateSeller(rs, dep));
            }
            return list;

        } catch (SQLException e){
            throw new DBException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Seller> findAll() {
        Statement st = null;
        ResultSet rs = null;

        try{
            st = conn.createStatement();

            rs = st.executeQuery(
                    "SELECT s.*, d.Name AS DepName "
                    + "FROM Seller s "
                    + "INNER JOIN department d ON s.DepartmentId = d.Id "
                    + "ORDER BY s.name"
            );

            List<Seller> list = new ArrayList<>();

            Map<Integer, Department> map = new HashMap<>();

            while (rs.next()){
                Department dep = map.get(rs.getInt("DepartmentId"));
                if(dep == null){
                    dep = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }
                list.add(instantiateSeller(rs, dep));
            }
            return list;

        } catch (SQLException e){
            throw new DBException(e.getMessage());
        }
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));
        return dep;
    }

    private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException{
        Seller seller = new Seller();
        seller.setId(rs.getInt("Id"));
        seller.setName(rs.getString("Name"));
        seller.setEmail(rs.getString("Email"));
        seller.setBaseSalary(rs.getDouble("BaseSalary"));
        seller.setBirthDate(rs.getDate("BirthDate").toLocalDate().atStartOfDay());
        seller.setDepartment(dep);
        return seller;
    }


}
