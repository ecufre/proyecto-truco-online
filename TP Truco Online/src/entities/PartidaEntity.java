package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="partidas")
public class PartidaEntity {
		@Id
		@GeneratedValue
		private Integer id;

		public PartidaEntity() {
		}

		public PartidaEntity(Integer id) {
			this.id = id;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}
		

}
