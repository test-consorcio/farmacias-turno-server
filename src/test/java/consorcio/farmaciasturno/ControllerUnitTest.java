package consorcio.farmaciasturno;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ControllerUnitTest {

	private static final String REGION_METROPOLITANA_ID = "7";

	private static final String COMUNA_ID = "87";

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testListarComunasPorRegion() throws Exception {

		this.mockMvc.perform(get("/listarcomunasporregion/" + REGION_METROPOLITANA_ID)).andDo(print())
				.andExpect(status().isOk());

	}

	@Test
	public void testListarFarmaciasTurno() throws Exception {

		this.mockMvc.perform(get("/listarfarmaciasturno/" + COMUNA_ID)).andDo(print()).andExpect(status().isOk());

	}

}
