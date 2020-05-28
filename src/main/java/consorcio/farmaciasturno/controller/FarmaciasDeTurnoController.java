package consorcio.farmaciasturno.controller;

import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import consorcio.farmaciasturno.service.IFarmaciasDeTurnoService;

@RestController
public class FarmaciasDeTurnoController {

	private Logger logger = LoggerFactory.getLogger(FarmaciasDeTurnoController.class);

	@Autowired
	@Qualifier("breedsServiceImpl")
	private IFarmaciasDeTurnoService iFarmaciasDeTurnoService;

	@GetMapping(value = "/listarcomunasporregion/{regionId}", produces = MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	public ResponseEntity<String> listarComunasPorRegion(@PathVariable String regionId) {

		try {

			String comunas = iFarmaciasDeTurnoService.listarComunasPorRegion(regionId);

			return new ResponseEntity<>(comunas, HttpStatus.OK);

		} catch (Exception error) {

			logger.error(error.getMessage(), error);

			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@GetMapping(value = "/listarfarmaciasturno/{comunaId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<LinkedHashMap<String, String>>> listarFarmaciasTurno(@PathVariable String comunaId) {

		try {

			List<LinkedHashMap<String, String>> listaFarmacias = iFarmaciasDeTurnoService.obtenerFarmaciasTurno(comunaId);

			return new ResponseEntity<>(listaFarmacias, HttpStatus.OK);

		} catch (Exception error) {

			logger.error(error.getMessage(), error);

			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

}
