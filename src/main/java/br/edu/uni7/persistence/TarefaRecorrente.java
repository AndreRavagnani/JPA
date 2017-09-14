package br.edu.uni7.persistence;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity 
@Table(name="TBL_TAR_REC")
@DiscriminatorValue("TR")
@PrimaryKeyJoinColumn(name="ID_FK")
public class TarefaRecorrente extends Tarefa {
	Long periodicidade;

	public Long getPeriodicidade() {
		return periodicidade;
	}

	public void setPeriodicidade(Long periodicidade) {
		this.periodicidade = periodicidade;
	}
	

}
