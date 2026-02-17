package kakeibo.repository;

import java.util.List;

import kakeibo.entity.Entity;

public interface DatabaseOperation {
	
	List<Entity> findAll();
	
	Entity findById(int id);
	
	int insertEntity(Entity entity);
	
	int updateEntity(Entity entity);
	
	int deleteEntity(Entity entity);
	
	int getIdMaxValue();

}
