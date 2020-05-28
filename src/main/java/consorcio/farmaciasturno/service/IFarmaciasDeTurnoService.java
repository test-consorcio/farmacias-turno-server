package consorcio.farmaciasturno.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IFarmaciasDeTurnoService {

	List<LinkedHashMap<String, String>> obtenerFarmaciasTurno(String comunaId) throws JsonProcessingException;

	String listarComunasPorRegion(String regionId);

}
