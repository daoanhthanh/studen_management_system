package hanu.edu.ems.domains.Timetable;

import hanu.edu.ems.base.CRUDController;
import hanu.edu.ems.domains.Timetable.dto.CreateTimetableDTO;
import hanu.edu.ems.domains.Timetable.dto.UpdateTimetableDTO;
import hanu.edu.ems.domains.Timetable.entity.Timetable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/timetables")
@Tag(name = "Timetable Manager", description = "The Timetable API")
public class TimetableController implements CRUDController<Timetable, Long, CreateTimetableDTO, UpdateTimetableDTO> {

    private final TimetableService timetableService;

    @Autowired
    public TimetableController(TimetableService timetableService) {
        this.timetableService = timetableService;
    }

    @Override
    @Operation(summary = "Create a new timetable for a particular course")
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Missing Request Parameter"),
        @ApiResponse(responseCode = "422", description = "Input validation(s) failed"),
        @ApiResponse(responseCode = "409", description = "Field value(s) already exists")
    })
    public Timetable create(@Valid @RequestBody CreateTimetableDTO createTimeTableDTO) {
        return timetableService.create(createTimeTableDTO);
    }

    @Override
    @Operation(summary = "Get all timetables")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT')")
    @GetMapping(value = "/all")
    public List<Timetable> getAll() {
        return timetableService.getAll();
    }

    @Override
    @Operation(summary = "Get timetables with paginating and sorting options")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT')")
    @GetMapping
    public Page<Timetable> getMany(Pageable pageable) {
        return timetableService.getMany(pageable);
    }

    @Override
    @Operation(summary = "Get timetable by ID")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT')")
    @GetMapping(value = "/{id}")
    public Timetable getByID(@PathVariable Long id) {
        return timetableService.getById(id);
    }

    @Override
    @Operation(summary = "Update a timetable by ID")
    @PutMapping(value = "/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Missing Request Parameter"),
        @ApiResponse(responseCode = "422", description = "Input validation(s) failed"),
        @ApiResponse(responseCode = "409", description = "Field value(s) already exists")
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    public Timetable updateByID(@PathVariable Long id, @Valid @RequestBody UpdateTimetableDTO updateTimetableDTO) {
        return timetableService.updateById(id, updateTimetableDTO);
    }

    @Override
    @Operation(summary = "Delete a timetable by ID")
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public void deleteByID(@PathVariable Long id) {
        timetableService.deleteById(id);
    }
}
