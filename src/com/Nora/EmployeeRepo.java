package com.Nora;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepo implements AutoCloseable{
    private Connection connection;
    private PreparedStatement preparedStatement;

    public EmployeeRepo() throws Exception{
        //constructor
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //activate
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe" , "sys sysdba", "");
        connection.setAutoCommit(false); //initialize

    }
    public void insert(EmployeeEnti employeeEnti) throws Exception{
        preparedStatement = connection.prepareStatement("INSERT INTO employee(idNumber, fullName, phoneNumber, address, place, salary) VALUES (?,?,?,?,?,?)");
        preparedStatement.setLong(1,employeeEnti.getIdNumber());
        preparedStatement.setString(2,employeeEnti.getFullName());
        preparedStatement.setString(3,employeeEnti.getPhoneNumber());
        preparedStatement.setString(4, employeeEnti.getAddress());
        preparedStatement.setString(5, employeeEnti.getPlace());
        preparedStatement.setInt(6, employeeEnti.getSalary());
        preparedStatement.executeUpdate();
    }

    public void update(EmployeeEnti employeeEnti) throws Exception{
        preparedStatement = connection.prepareStatement("UPDATE employee SET idNumber = ?, fullName= ?, phoneNumber = ?, address = ?, place = ?, salary = ?");
        preparedStatement.setLong(1,employeeEnti.getIdNumber());
        preparedStatement.setString(2,employeeEnti.getFullName());
        preparedStatement.setString(3,employeeEnti.getPhoneNumber());
        preparedStatement.setString(4, employeeEnti.getAddress());
        preparedStatement.setString(5, employeeEnti.getPlace());
        preparedStatement.setInt(6, employeeEnti.getSalary());
        preparedStatement.executeUpdate();
    }
    public void delete(long idNumber) throws Exception{
        preparedStatement=connection.prepareStatement("DELETE FROM employee where id=?");
        preparedStatement.setLong((1),idNumber);
        preparedStatement.executeUpdate();
    }
    public List<EmployeeEnti> select() throws Exception{
        preparedStatement=connection.prepareStatement("SELECT * FROM employee");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<EmployeeEnti> employeeEntisList = new ArrayList<>();
        while ((resultSet.next())){
            EmployeeEnti employeeEnti = new EmployeeEnti(); //call by reference
            employeeEnti.setIdNumber(resultSet.getLong("idNumber"));
            employeeEnti.setFullName(resultSet.getString("FullName"));
            employeeEnti.setPhoneNumber(resultSet.getString("PhoneNumber"));
            employeeEnti.setAddress(resultSet.getString("Address"));
            employeeEnti.setPlace(resultSet.getString("Place"));
            employeeEnti.setSalary(resultSet.getInt("Salary"));
        }
        return employeeEntisList;
    }

    public void commit() throws Exception{
        connection.commit();
    }
    public void rollback() throws Exception{
        connection.rollback();
    }

    //By using AutoCloseable this method should be implemented
    public void close() throws Exception{
        preparedStatement.close();
        connection.close();
    }


}
