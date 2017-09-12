package br.edu.uni7.persistence;

import java.io.Console;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.Test;

import junit.framework.Assert;

public class QueriesTest {

	static EntityManager manager = Persistence.createEntityManagerFactory("cursojpa").createEntityManager();

	@Test
	public void buscaEmpregadoCidadeTest() {
		manager.getTransaction().begin();
		Utils.gerarDepartamentosComEmpregados(manager);
		manager.getTransaction().commit();

		Query query = manager.createQuery("SELECT e FROM Empregado e WHERE e.endereco.cidade = :cidade",
				Empregado.class);
		query.setParameter("cidade", "Fortaleza");

		List<Empregado> empregados = query.getResultList();

	}

	@Test
	public void buscaEmpregadoPorNomeTest() {

		Query query = manager.createQuery("SELECT e FROM Empregado e WHERE e.nome LIKE :name", Empregado.class);
		query.setParameter("name", "Andre");

		List<Empregado> empregados = query.getResultList();
	}

	@Test
	public void empregadosMaiorOrcamentoTest() {

		Query query = manager.createQuery("select ed from Departamento ed order by ed.orcamento", Departamento.class);

		query.setFirstResult(0);
		query.setMaxResults(1);
		List<Departamento> departamentos = query.getResultList();

		Query query2 = manager.createQuery("SELECT e FROM Empregado e WHERE e.departamento IN (:departamentos)",
				Empregado.class);
		query2.setParameter("departamentos", departamentos);

		List<Empregado> empregados = query.getResultList();

	}
}
