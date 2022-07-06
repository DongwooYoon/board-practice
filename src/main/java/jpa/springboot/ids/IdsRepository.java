package jpa.springboot.ids;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IdsRepository extends JpaRepository<Ids, String>{
	public Ids findByTableName(String tableName);
}
