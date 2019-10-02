package com.example.zarzadzaniezadaniami.repository;

import com.example.zarzadzaniezadaniami.entity.Task;
import com.example.zarzadzaniezadaniami.entity.status.TaskStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class TaskRepository {

    private EntityManagerFactory factory;
    private EntityManager manager;
    private EntityTransaction transaction;
    private List<Task> tasks;

    public TaskRepository(EntityManagerFactory factory) {
        this.factory = factory;
        this.manager = factory.createEntityManager();
        this.transaction = manager.getTransaction();
    }

    public void save(Task task) {
        transaction.begin();
        manager.persist(task);
        transaction.commit();
    }

    public List<Task> getAll() {
        transaction.begin();
        Query query = manager.createQuery("SELECT t FROM Task t");
        tasks = query.getResultList();
        transaction.commit();

        return tasks;
    }

    public List<Task> getByStatus(TaskStatus status) {
        transaction.begin();
        Query query = manager.createQuery("SELECT t FROM Task t WHERE t.status = " + status);
        tasks = query.getResultList();
        transaction.commit();

        return tasks;
    }

    public Task getById(long id) {
        Task task = null;
        transaction.begin();
        Query query = manager.createQuery("SELECT t FROM Task t WHERE t.id = " + id);
        for (Object o : query.getResultList()) {
            task = (Task) o;
        }
        transaction.commit();
        return task;
    }

    public void updateStatusToDone(long id) {
        transaction.begin();
        manager.createQuery("UPDATE Task t SET t.status = " + TaskStatus.DONE + " WHERE t.id = " + id);
        transaction.commit();
    }

    public void close() {
        factory.close();
        manager.close();
    }

}
