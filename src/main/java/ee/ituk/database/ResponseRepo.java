package ee.ituk.database;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ResponseRepo extends CrudRepository<ResponseModel, Long> {
    ResponseModel findByName(String name);
    List<ResponseModel> findByPackageDay1(String value);
    List<ResponseModel> findByPackageDay2(String value);

}
