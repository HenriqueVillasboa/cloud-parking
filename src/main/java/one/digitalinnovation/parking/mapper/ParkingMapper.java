package one.digitalinnovation.parking.mapper;

import one.digitalinnovation.parking.dto.ParkingDto;
import one.digitalinnovation.parking.dto.ParkingRequest;
import one.digitalinnovation.parking.model.Parking;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParkingMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public ParkingDto toParkingDto(Parking parking) {
        return MODEL_MAPPER.map(parking, ParkingDto.class);
    }

    public List<ParkingDto> toParkingDtoList(List<Parking> parkingList) {
        return parkingList.stream()
                .map(this::toParkingDto)
                .collect(Collectors.toList());
    }

    public Parking toParking(ParkingDto dto) {
        return MODEL_MAPPER.map(dto, Parking.class);
    }

    public Parking toParkingCreate(ParkingRequest dto) {
        return MODEL_MAPPER.map(dto, Parking.class);
    }
}
