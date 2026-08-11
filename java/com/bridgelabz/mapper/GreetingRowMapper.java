//package com.bridgelabz.mapper;
//
//import com.bridgelabz.model.Greeting;
//
//import javax.swing.tree.RowMapper;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class GreetingRowMapper implements RowMapper<Greeting> {
//
//    @Override
//    public Greeting mapRow(ResultSet rs, int rowNum) throws SQLException {
//
//        Greeting greeting = new Greeting();
//
//        greeting.setId(rs.getInt("id"));
//        greeting.setUserName(rs.getString("user_name"));
//        greeting.setMessage(rs.getString("message"));
//        greeting.setCreatedDate(
//                rs.getString("created_date")
//        );
//
//        return greeting;
//    }
//}