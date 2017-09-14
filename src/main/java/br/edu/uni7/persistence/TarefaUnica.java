package br.edu.uni7.persistence;

import java.util.Date;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity 
@Table(name="TBL_TAR_UNI")
@DiscriminatorValue("TU")
@PrimaryKeyJoinColumn(name="ID_FK")
public class TarefaUnica extends Tarefa {
	Date dataExecucao;

	public Date getDataExecucao() {
		return dataExecucao;
	}

	public void setDataExecucao(Date dataExecucao) {
		this.dataExecucao = dataExecucao;
	}
	
}
