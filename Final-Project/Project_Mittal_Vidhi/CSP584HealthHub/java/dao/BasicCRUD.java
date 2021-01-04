package dao;

import java.util.List;

import bean.Appointment;


public interface BasicCRUD<T,ID> {
	public void add(T t);
	public void delete(ID id);
	public void update(T t);
	public T getById(ID id);
	public List<T> getAll();
}
