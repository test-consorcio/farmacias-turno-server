package consorcio.farmaciasturno.service;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("farmaciasDeTurnoServiceImpl")
public class FarmaciasDeTurnoServiceImpl implements IFarmaciasDeTurnoService {

	private Logger logger = LoggerFactory.getLogger(FarmaciasDeTurnoServiceImpl.class);

	@Autowired
	private RestTemplate restTemplate;

	@Value("${comunas.url}")
	private String comunasUrl;

	@Value("${farmacias.turno.regionmetropolitana.url}")
	private String farmaciasTurnoUrl;

	@Value("${region.metropolitana.id}")
	private String regionMetropolitanaId;

	@Override
	public String listarComunasPorRegion(String regionId) {

		HttpHeaders headers = new HttpHeaders();

		headers.set("content-type", "multipart/form-data");

		MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();

		requestBody.add("reg_id", this.regionMetropolitanaId);

		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(requestBody, headers);

		ResponseEntity<String> result = restTemplate.exchange(this.comunasUrl, HttpMethod.POST, httpEntity,
				String.class);

		return result.getBody();

	}

	public List<LinkedHashMap<String, String>> obtenerFarmaciasTurno(String comunaId)
			throws JsonMappingException, JsonProcessingException {

		String responseEntity = restTemplate.getForObject(this.farmaciasTurnoUrl + this.regionMetropolitanaId,
				String.class, comunaId);

		@SuppressWarnings("unchecked")
		List<LinkedHashMap<String, String>> listaFarmacias = new ObjectMapper().readValue(responseEntity,
				LinkedList.class);

		List<LinkedHashMap<String, String>> listaRetornar = new LinkedList<>();

		for (LinkedHashMap<String, String> mapaFarmacia : listaFarmacias) {

			if (String.valueOf(mapaFarmacia.get("fk_comuna")).equals(String.valueOf((comunaId)))) {

				listaRetornar.add(mapaFarmacia);

			}

		}

		return listaRetornar;

	}

}
