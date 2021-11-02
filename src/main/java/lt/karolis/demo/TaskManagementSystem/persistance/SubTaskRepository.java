package lt.karolis.demo.TaskManagementSystem.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubTaskRepository extends JpaRepository<SubTask, Long>, JpaSpecificationExecutor<SubTask> {

    SubTask getById(Long id);

    List<SubTask> findAll();
}
