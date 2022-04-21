package guru.qa.jsonfile;

import org.junit.jupiter.api.Test;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonConvert {

    @Test
    void jsonTypeTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("src/test/resources/files/example_file.json");
        InfoHero ih = mapper.readValue(file, InfoHero.class);
        assertThat(ih.name).isEqualTo("Madame Uppercut");
        assertThat(ih.age).isEqualTo(39);
        assertThat(ih.secretIdentity).isEqualTo("Jane Wilson");
        assertThat(ih.powers).contains("Million tonne punch", "Damage resistance", "Superhuman reflexes");
        assertThat(ih.address.city).isEqualTo("New York");
        assertThat(ih.address.street).isEqualTo("NoName");

    }
}
