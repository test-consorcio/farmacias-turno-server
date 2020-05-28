package consorcio.farmaciasturno;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;

import consorcio.farmaciasturno.service.IFarmaciasDeTurnoService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ServiceUnitTests {

	private static final String REGION_METROPOLITANA_ID = "7";

	private static final String COMUNA_ID = "87";

	@Autowired
	@Qualifier("breedsServiceImpl")
	private IFarmaciasDeTurnoService breedsService;

	@Test
	public void testListarComunasPorRegion() {

		String comunas = breedsService.listarComunasPorRegion(REGION_METROPOLITANA_ID);

		assertNotEquals(comunas, "");

		assertNotNull(comunas);

	}

	@Test
	public void testListarFarmaciasTurno() throws JsonProcessingException {

		List<LinkedHashMap<String, String>> farmaciasTurno = breedsService.obtenerFarmaciasTurno(COMUNA_ID);

		assertTrue(!farmaciasTurno.isEmpty());

	}

}
