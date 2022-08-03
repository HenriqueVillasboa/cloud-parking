package one.digitalinnovation.parking.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import one.digitalinnovation.parking.dto.ParkingDto;
import one.digitalinnovation.parking.dto.ParkingRequest;
import one.digitalinnovation.parking.mapper.ParkingMapper;
import one.digitalinnovation.parking.service.ParkingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/parking")
@Api(tags = "Parking Controller")
public class ParkingController {

    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;

    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }

    @GetMapping
    @ApiOperation("Find all parkings")
    public ResponseEntity<List<ParkingDto>> findAll() {
        var parkingList = parkingService.findAll();
        List<ParkingDto> result = parkingMapper.toParkingDtoList(parkingList);
        return ResponseEntity.ok(result);
    }

    @GetMapping("{id}")
    public ResponseEntity<ParkingDto> findById(@PathVariable String id) {
        var parking = parkingService.findById(id);
        var result = parkingMapper.toParkingDto(parking);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable String id) {
        parkingService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<ParkingDto> create(@RequestBody ParkingRequest dto) {
        var parkingCreate = parkingMapper.toParkingCreate(dto);
        var parking = parkingService.create(parkingCreate);
        var result = parkingMapper.toParkingDto(parking);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("{id}")
    public ResponseEntity<ParkingDto> update(@PathVariable String id, @RequestBody ParkingRequest parkingRequest) {
        var parkingUpdate = parkingMapper.toParkingCreate(parkingRequest);
        var parking = parkingService.update(id, parkingUpdate);
        return ResponseEntity.ok(parkingMapper.toParkingDto(parking));
    }

    @PostMapping("{id}/exit")
    public ResponseEntity<ParkingDto> checkOut(@PathVariable String id) {
        var parking = parkingService.checkOut(id);
        return ResponseEntity.ok(parkingMapper.toParkingDto(parking));
    }
}
