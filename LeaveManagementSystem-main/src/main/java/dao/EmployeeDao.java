package dao;

import com.Spring.model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.data.jpa.provider.HibernateUtils;
import util.HibernateUtil;
import org.hibernate.query.Query;
import java.util.List;

public class EmployeeDao {


        public Integer saveEmployee ( Employee E) {
            Session ses = new HibernateUtil().getSf().openSession();
            Transaction tx = null;
            Integer id = null;
            try{
                tx = ses.beginTransaction();
                id = (Integer) ses.save(ses);
                tx.commit();
            } catch (Exception e){
                if (tx!= null && tx.getStatus().canRollback()){
                    tx.rollback();
                }
                e.printStackTrace();
            }
            return id;
        }

        public List<Employee> getAllEmployees() {
            String HQL = " From " + Employee.class.getName();
            Session ses =  new HibernateUtil().getSf().openSession();
            List<Employee> list = null;
            try (ses) {
                Query q = ses.createQuery(HQL);
                q.list();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }

        public String deleteEmployee( Integer id) {
            String message = null;
            Session ses =  new HibernateUtil().getSf().openSession();

            Transaction tx = null;
            try {
                tx = ses.beginTransaction();
                Employee e = new Employee() ;
                e.setId(id);
                ses.delete(e);
                tx.commit();
                message= "Employee" + id + " deleted";
            } catch (Exception e ) {
                if (tx!= null & tx.getStatus().canRollback()){
                    tx.rollback();
                }
                message= "Employee" + id + "not exist";
                e.printStackTrace();
            }
            return message;
        }

        public Employee getoneEmployee( Integer id){
            Session ses =  new HibernateUtil().getSf().openSession();
            Employee e = null;
            try {
                e = ses.get(Employee.class, id);
            }
            catch (Exception em) {
                em.printStackTrace();
            }
            return e;
        }

        public String updateEmployee(Employee employee) {
            String msg = null;
            Session ses =  new HibernateUtil().getSf().openSession();
            Transaction tx = null;
            try {
                tx = ses.beginTransaction();
                ses.update(employee);
                tx.commit();
                msg = " Employee" + employee.getId() + " Updated";

            } catch (Exception e) {
                if (tx !=null && tx.getStatus().canRollback())
                    tx.rollback();
                msg = " Employee " + e.getMessage() + " not exit ";
                e.printStackTrace();
            }
            return msg;
        }
    }


