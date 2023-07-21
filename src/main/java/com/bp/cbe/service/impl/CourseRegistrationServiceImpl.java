package com.bp.cbe.service.impl;

import com.bp.cbe.domain.CourseRegistration;
import com.bp.cbe.exception.RegisterException;
import com.bp.cbe.exception.ResourceNotFoundException;
import com.bp.cbe.external.dto.CourseDTO;
import com.bp.cbe.external.dto.UserDTO;
import com.bp.cbe.external.service.CourseService;
import com.bp.cbe.external.service.UserService;
import com.bp.cbe.repository.CourseRegistrarionRepository;
import com.bp.cbe.service.CourseRegistrationService;
import com.bp.cbe.service.RegisterService;
import com.bp.cbe.service.dto.*;
import com.bp.cbe.service.mapper.CourseRegistrationMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseRegistrationServiceImpl implements CourseRegistrationService {

    CourseRegistrarionRepository courseRegistrationRepository;

    RegisterService registerService;

    UserService userService;

    CourseService courseService;

    @Override
    public List<CourseRegistrationDTO> getAll() {
        return courseRegistrationRepository.findAll()
                .stream()
                .map( CourseRegistrationMapper.INSTANCE::toDto )
                .toList();
    }

    @Override
    public CourseRegistrationDTO getById(Integer id) {
        CourseRegistration entity = getCourseRegistration( id );
        return CourseRegistrationMapper.INSTANCE.toDto( entity );
    }

    private Boolean validateMaxCourseSize(CourseRegistration entity) {
        List<CourseRegistration> dbEntity = courseRegistrationRepository.findByRegister( entity.getRegister() );
        return dbEntity.size() < 5;
    }

    @Override
    public CourseRegistrationDTO save(CourseRegistrationDTO data) {
        RegisterDTO registerDTO = registerService.getByUser( data.getRegister().getUser() );

        if (registerDTO == null) {
            registerDTO = registerService.save( registerDTO );
            data.setRegister( registerDTO );
        }

        if (data.getCourse() == null) {
            data.setCourse( "CR-001" );
        }

        CourseRegistration entity = CourseRegistrationMapper.INSTANCE.toEntity( data );

        if (validateMaxCourseSize( entity )) {
            return CourseRegistrationMapper.INSTANCE.toDto( courseRegistrationRepository.save( entity ) );
        } else {
            throw new RegisterException( "The registration of a user is only allowed up to 5 courses." );
        }
    }

    @Override
    public CourseRegistrationDTO update(Integer id, CourseRegistrationDTO data) {
        CourseRegistration entity = getCourseRegistration( id );
        entity.setCourse( data.getCourse() );

        if (validateMaxCourseSize( entity )) {
            return CourseRegistrationMapper.INSTANCE.toDto( courseRegistrationRepository.save( entity ) );
        } else {
            throw new RegisterException( "The registration of a user is only allowed up to 5 courses." );
        }
    }

    @Override
    public void delete(Integer id) {
        courseRegistrationRepository.deleteById( id );
    }

    private CourseRegistration getCourseRegistration(Integer id) {
        return courseRegistrationRepository.findById( id )
                .orElseThrow( () -> new ResourceNotFoundException( "CourseRegistration", "id", id.toString() ) );
    }

    public RegisterReportDTO getReport(Integer userId) {
        RegisterDTO registerDTO = registerService.getByUser( userId );

        List<UserDTO> users = userService.getAll();
        List<CourseDTO> courses = courseService.getAll();

        List<CourseRegistrationReportDTO> courseRegistrationReportDTOS = new ArrayList<>();

        for (CourseRegistrationDTO courseRegistrationDTO : registerDTO.getRegistrations()) {
            courseRegistrationReportDTOS
                    .add( CourseRegistrationReportDTO.builder()
                            .id( courseRegistrationDTO.getId() )
                            .startDate( courseRegistrationDTO.getStartDate() )
                            .endDate( courseRegistrationDTO.getEndDate() )
                            .register( courseRegistrationDTO.getRegister() )
                            .course( courses.stream()
                                    .filter( course -> course.getCode().equals( courseRegistrationDTO.getCourse() ) )
                                    .findFirst()
                                    .orElse( null ) )
                            .status( courseRegistrationDTO.getStatus() )
                            .build() );
        }

        return RegisterReportDTO.builder()
                .user( users.stream()
                        .filter( user -> user.getId().equals( registerDTO.getUser() ) )
                        .findFirst()
                        .orElse( null ) )
                .registerDate( registerDTO.getRegisterDate() )
                .registrations( courseRegistrationReportDTOS )
                .build();
    }

    public PricesReportDTO getReportTotals(Integer userId) {
        RegisterReportDTO registerReportDTO = getReport( userId );

        BigDecimal totalAmount = registerReportDTO.getRegistrations().stream()
                .map( registration -> registration.getCourse().getPrice() )
                .reduce( BigDecimal.ZERO, BigDecimal::add );

        return PricesReportDTO.builder()
                .user( registerReportDTO.getUser() )
                .price( totalAmount )
                .build();
    }

    public List<UserDTO> getReportUsers() {
        List<UserDTO> users = userService.getAll();

        return users.stream()
                .sorted( Comparator.comparing(UserDTO::getName))
                .collect( Collectors.toList());
    }

    public DaysReportDTO getAvailableDays(Integer userId) {
        RegisterDTO registerDTO = registerService.getByUser( userId );

        List<Date> diasHabiles = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        List<String> diasHabilesFormateados = null;
        for (CourseRegistrationDTO course : registerDTO.getRegistrations()) {
            calendar.setTime( course.getStartDate() );

            while (!calendar.getTime().after( course.getEndDate() )) {
                if (esDiaHabil( calendar )) {
                    diasHabiles.add( calendar.getTime() );
                }
                calendar.add( Calendar.DAY_OF_MONTH, 1 );
            }

            diasHabilesFormateados = diasHabiles.stream()
                    .map( date -> formatDate( date, "yyyy/MM/dd" ) )
                    .collect( Collectors.toList() );
        }

        return DaysReportDTO.builder()
                .user( registerDTO.getUser() )
                .days( diasHabilesFormateados )
                .build();

    }

    public static boolean esDiaHabil(Calendar calendar) {
        int diaSemana = calendar.get(Calendar.DAY_OF_WEEK);
        return diaSemana != Calendar.SATURDAY && diaSemana != Calendar.SUNDAY;
    }

    public static Date getDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        return calendar.getTime();
    }

    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

}
