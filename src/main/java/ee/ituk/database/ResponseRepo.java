package ee.ituk.database;

import org.springframework.data.repository.CrudRepository;

public interface ResponseRepo extends CrudRepository<ResponseModel, Long> {
    ResponseModel findByName(String name);
}
