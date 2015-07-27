import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.qeeka.test.service.PersonService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Neal on 2015/7/27.
 */
public class PersonTest extends SpringTestWithDB {


    @Autowired
    private PersonService personService;

    @Test
    @DatabaseSetup("PersonData.xml")
    @Transactional
    public void test1() {
        personService.remove(1);
    }


}
