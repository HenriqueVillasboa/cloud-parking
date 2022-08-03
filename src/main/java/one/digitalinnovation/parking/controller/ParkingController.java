package one.digitalinnovation.parking.controller;

import one.digitalinnovation.parking.dto.ParkingDto;
import one.digitalinnovation.parking.mapper.ParkingMapper;
import one.digitalinnovation.parking.service.ParkingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/parking")
public class ParkingController {

    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;

    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }

    @GetMapping
    public List<ParkingDto> findAll() {
        var parkingList = parkingService.findAll();
        return parkingMapper.toParkingDtoList(parkingList);
    }
}
