package br.edu.uni7.persistence;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.Test;

public class EmpregadoTest {

	static EntityManager manager = Persistence.createEntityManagerFactory("cursojpa").createEntityManager();

	@Test
	public void empregadoTest() {

		Endereco endEmp = new Endereco();
		endEmp.setCep(new Long("60732250"));
		endEmp.setCidade("Maracanau");
		endEmp.setEstado("CE");

		Endereco endDep = new Endereco();
		endDep.setCep(new Long("60310000"));
		endDep.setCidade("Fortaleza");
		endDep.setEstado("CE");

		Projeto prj = new Projeto();
		prj.setNome("Java");

		List<Projeto> prjs = new ArrayList<Projeto>();
		prjs.add(prj);

		Departamento dep = new Departamento();
		dep.setNome("TI");
		dep.setOrcamento(new BigDecimal("5000"));
		dep.setEndereco(endDep);

		manager.getTransaction().begin();
		manager.persist(dep);
		manager.getTransaction().commit();

		Documento doc = new Documento();
		doc.setNumero(new Long("4546886"));

		manager.getTransaction().begin();
		manager.persist(doc);
		manager.getTransaction().commit();

		Empregado emp = new Empregado();
		emp.setDepartamento(dep);
		emp.setEmail("teste@teste.com");
		emp.setNascimento(new Date());
		emp.setNome("Andre");
		emp.setSalario(new BigDecimal("4"));
		emp.setProjetos(prjs);
		emp.setDocumento(doc);
		emp.setEndereco(endEmp);
		List<Empregado> emps = new ArrayList<Empregado>();
		emps.add(emp);

		prj.setEmpregados(emps);

		manager.getTransaction().begin();
		manager.persist(emp);
		manager.getTransaction().commit();

		manager.getTransaction().begin();
		manager.persist(prj);
		manager.getTransaction().commit();

	}

}
