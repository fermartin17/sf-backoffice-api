package sf.backoffice.api.employees.persistence.repositories;

import org.springframework.stereotype.Repository;
import sf.backoffice.api.employees.domains.Employee;
import sf.backoffice.api.employees.domains.dtos.CreateOrUpdateEmployeeDto;
import sf.backoffice.api.employees.errors.EmployeeFailedToCreateException;
import sf.backoffice.api.employees.errors.EmployeeFailedToObtainException;
import sf.backoffice.api.employees.errors.EmployeeFailedToUpdateException;
import sf.backoffice.api.employees.persistence.entities.EmployeeEntity;
import sf.backoffice.api.employees.persistence.interfaces.EmployeeRepositoryInterface;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository implements EmployeeRepositoryInterface {

    private static final String ENTITY_NAME = "Employee";
    @PersistenceContext
    protected EntityManager entityManager;

    public void update(CreateOrUpdateEmployeeDto createOrUpdateEmployeeDto) throws EmployeeFailedToUpdateException {
        EmployeeEntity entity = EmployeeEntity.createEntity(createOrUpdateEmployeeDto);

        try {
            
            entityManager.merge(entity);
        } catch (Exception ex) {
            throw new EmployeeFailedToUpdateException("Failed to update employee in database.");
        }
    }

    public Employee create(CreateOrUpdateEmployeeDto createOrUpdateEmployeeDto) throws EmployeeFailedToCreateException {
        EmployeeEntity entity = EmployeeEntity.createEntity(createOrUpdateEmployeeDto);

        try {
            entity.setCreationDate(Date.from(Instant.now()));

            entityManager.persist(entity);
            entityManager.flush();
        } catch (Exception ex) {
            throw new EmployeeFailedToCreateException("Failed to create employee in database.");
        }

        return entity.toDomainModel();
    }


    public List<Employee> getAll() throws EmployeeFailedToObtainException {
        String hql = "SELECT p FROM " + ENTITY_NAME + " p WHERE p.deleted = 'false'";

        Query query = entityManager.createQuery(hql);

        List<EmployeeEntity> list;

        try {
            list = (List<EmployeeEntity>) query.getResultList();
        } catch (NoResultException ex) {
            list = null;
        } catch (Exception ex) {
            throw new EmployeeFailedToObtainException("Failed to obtain all employees.");
        }

        return list.stream().map(elem -> elem.toDomainModel()).collect(Collectors.toList());
    }


    public Employee getById(long id) throws EmployeeFailedToObtainException {
        String hql = "SELECT p FROM " + ENTITY_NAME + " p WHERE p.id = :entityId AND p.deleted = 'false'";

        Query query = entityManager.createQuery(hql)
                .setParameter("entityId", id);

        EmployeeEntity entity;

        try {
            entity = (EmployeeEntity) query.getSingleResult();
        } catch (NoResultException ex) {
            entity = null;
        } catch (Exception ex) {
            throw new EmployeeFailedToObtainException("Failed to obtain the employee.");
        }

        return entity.toDomainModel();
    }


}
