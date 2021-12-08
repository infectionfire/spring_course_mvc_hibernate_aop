package com.ivanovsergei.spring.mvc_hibernate_aop.dao;

import com.ivanovsergei.spring.mvc_hibernate_aop.entity.Employee;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
       public List<Employee> getAllEmployees() {

        Session session = sessionFactory.getCurrentSession();

        Query<Employee> query = session.createQuery("from Employee"//не работало, решилось пересборкой
                , Employee.class);
        List<Employee> allEmployees = query.getResultList();
        return allEmployees;
    }

    @Override
    public void saveEmployee(Employee employee) {//сохраняется здесь, т.к. дао имеет связь с бд
        //если новый работник, придет ид==0, если редактируем, то через емп. инфо получим текущий ид
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(employee);//метод хайбернейт сохранение или редактирование
    }

    @Override
    public Employee getEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();

        Employee employee = session.get(Employee.class, id);
        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();

        Query<Employee> query = session.createQuery("delete from Employee where id=:employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }
}
