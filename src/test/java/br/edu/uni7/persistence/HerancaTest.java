package br.edu.uni7.persistence;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.Test;

public class HerancaTest {

	static EntityManager manager = Persistence.createEntityManagerFactory("cursojpa").createEntityManager();
	
	@Test
	public void herancaTest(){
		TarefaUnica tu = new TarefaUnica();
		tu.setDataExecucao(new Date());
		
		manager.getTransaction().begin();
		manager.persist(tu);
		manager.getTransaction().commit();
		
		TarefaRecorrente tr = new TarefaRecorrente();
		tr.setPeriodicidade(100L);
		manager.getTransaction().begin();
		manager.persist(tr);
		manager.getTransaction().commit();
	}

}
