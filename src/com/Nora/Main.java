package com.Nora;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        //insert
        try {
            EmployeeServ.getInstance().save(new EmployeeEnti().setIdNumber().setfullName().setphoneNumber()
                    .setaddress().setplace().setsalary());
            System.out.println("saved successfully");
        } catch (Exception e) {
            System.out.println("failed to save!" + e.getMessage());

        }
        //update
        try {
            EmployeeServ.getInstance().edit(new EmployeeEnti().setIdNumber().setfullName().setphoneNumber().setaddress().setplace().
                    setsalary());
            System.out.println("Edited successfully");
        } catch (Exception e) {
            System.out.println("Failed to edit" + e.getMessage());
        }
        //delete

        try  {
            System.out.println("Removed successfully");
        } catch (Exception e) {
            System.out.println("Failed to delete!" + e.getMessage());
        }
        //select
        try {
            List<EmployeeEnti> employeeEntiList =
                    EmployeeServ.getInstance().report();
            for (EmployeeEnti employeeEnti : employeeEntiList) {
                System.out.println(employeeEnti.getIdNumber());
                System.out.println(employeeEnti.getFullName());
                System.out.println(employeeEnti.getPhoneNumber());
                System.out.println(employeeEnti.getAddress());
                System.out.println(employeeEnti.getPlace());
                System.out.println(employeeEnti.getSalary());
            }
        } catch (Exception e) {
            System.out.println("Failed to report" + e.getMessage());

        }
    }
}
