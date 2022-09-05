package sf.backoffice.api.employees.persistence.repositories;

import org.springframework.stereotype.Repository;
import sf.backoffice.api.employees.domains.Employee;
import sf.backoffice.api.employees.domains.dtos.CreateOrUpdateEmployeeDto;
import sf.backoffice.api.employees.persistence.entities.EmployeeEntity;
import sf.backoffice.api.employees.persistence.interfaces.EmployeeRepositoryInterface;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository implements EmployeeRepositoryInterface {

    private static final String ENTITY_NAME = "Employee";
    @PersistenceContext
    protected EntityManager entityManager;

    public void update(CreateOrUpdateEmployeeDto createOrUpdateEmployeeDto) {
        EmployeeEntity entity = EmployeeEntity.createEntity(createOrUpdateEmployeeDto);

        try {
            entity = entityManager.merge(entity);
        } catch (Exception ex) {
        }
    }

    public Employee create(CreateOrUpdateEmployeeDto createOrUpdateEmployeeDto) {
        EmployeeEntity entity = EmployeeEntity.createEntity(createOrUpdateEmployeeDto);

        try {
            entityManager.persist(entity);
            entityManager.flush();
        } catch (Exception ex) {
        }

        return entity.toDomainModel();
    }


    public List<Employee> getAll() {
        String hql = "SELECT p FROM " + ENTITY_NAME + " p WHERE p.deleted = 'false'";

        Query query = entityManager.createQuery(hql);

        List<EmployeeEntity> list;

        try {
            list = (List<EmployeeEntity>) query.getResultList();
        } catch (NoResultException ex) {
            list = null;
        }

        return list.stream().map(elem -> elem.toDomainModel()).collect(Collectors.toList());
    }


    public Employee getById(long id) {
        String hql = "SELECT p FROM " + ENTITY_NAME + " p WHERE p.id = :entityId AND p.deleted = 'false'";

        Query query = entityManager.createQuery(hql)
                .setParameter("entityId", id);

        EmployeeEntity entity;

        try {
            entity = (EmployeeEntity) query.getSingleResult();
        } catch (NoResultException ex) {
            entity = null;
        }

        return entity.toDomainModel();
    }


}
