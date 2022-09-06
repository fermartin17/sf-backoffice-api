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
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository implements EmployeeRepositoryInterface {
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
        List<EmployeeEntity> employeeEntityList;
        try {
            employeeEntityList = entityManager.createQuery("SELECT b FROM EmployeeEntity b WHERE b.deleted = :deleted", EmployeeEntity.class)
                    .setParameter("deleted", false)
                    .getResultList();

        } catch (NoResultException ex) {
            employeeEntityList = null;
        } catch (Exception ex) {
            throw new EmployeeFailedToObtainException("Failed to obtain all employees.");
        }

        return Optional.ofNullable(employeeEntityList)
                .orElse(new ArrayList<EmployeeEntity>())
                .stream()
                .map(EmployeeEntity::toDomainModel)
                .collect(Collectors.toList());
    }


    public Employee getById(long id) throws EmployeeFailedToObtainException {
        EmployeeEntity entity;

        try {
            entity = entityManager.createQuery("SELECT b FROM EmployeeEntity b WHERE b.deleted = :deleted AND b.id = :employeeId", EmployeeEntity.class)
                    .setParameter("deleted", false)
                    .setParameter("employeeId", id)
                    .getSingleResult();

        } catch (NoResultException ex) {
            entity = null;
        } catch (Exception ex) {
            throw new EmployeeFailedToObtainException("Failed to obtain the employee.");
        }

        return entity.toDomainModel();
    }
}
