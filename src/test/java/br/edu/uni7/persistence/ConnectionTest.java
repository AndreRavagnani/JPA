package br.edu.uni7.persistence;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import junit.framework.Assert;

public class ConnectionTest {

	static EntityManager manager = Persistence.createEntityManagerFactory("cursojpa").createEntityManager();

	@Test
	public void testConnectionWithDb() {
		EntityManagerFactory etmf = Persistence.createEntityManagerFactory("cursojpa");
		EntityManager etm = etmf.createEntityManager();
		Assert.assertNotNull(etm);

	}

	public Empregado createEmpregado() {
		Empregado emp = new Empregado();
		emp.setNome("Andre");
		emp.setEmail("andreskiter@gmail.com");
		emp.setNascimento(new Date(1987, 06, 02));

		manager.getTransaction().begin();
		manager.persist(emp);
		manager.getTransaction().commit();

		return emp;

	}

	public Empregado updateEmpregado(Empregado empregado) {

		empregado.setEmail("andre@softium.com.br");
		manager.getTransaction().begin();
		Empregado empregadoUpdated = manager.merge(empregado);
		manager.getTransaction().commit();
		return empregadoUpdated;
	}

	public void removeEmpregado(Empregado empregado) {

		Empregado userManaged = manager.find(Empregado.class, empregado.getId());
		manager.getTransaction().begin();
		manager.remove(userManaged);
		manager.getTransaction().commit();
	}

	public void detachEmpregado(Empregado user) {

		Empregado userManaged = manager.merge(user);
		manager.getTransaction().begin();
		manager.detach(userManaged);
		manager.getTransaction().commit();
	}

	@Test
	public void empregadoTest() {
		Empregado emp = createEmpregado();
		Assert.assertNotNull(emp);

		emp = updateEmpregado(emp);
		Assert.assertEquals("andre@softium.com.br", emp.getEmail());

		removeEmpregado(emp);

		Empregado userManaged = manager.find(Empregado.class, emp.getId());

		Assert.assertNull(userManaged);

	}	

}
