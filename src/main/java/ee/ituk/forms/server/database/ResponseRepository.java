package ee.ituk.forms.server.database;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ResponseRepository extends CrudRepository<Response, Long> {
    Response findByName(String name);
    List<Response> findByPackageDay1(String value);
    List<Response> findByPackageDay2(String value);

}
