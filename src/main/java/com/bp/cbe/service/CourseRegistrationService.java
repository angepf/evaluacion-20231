package com.bp.cbe.service;

import com.bp.cbe.external.dto.UserDTO;
import com.bp.cbe.service.dto.*;

import java.util.List;

public interface CourseRegistrationService extends GenericService<Integer, CourseRegistrationDTO> {

    RegisterReportDTO getReport(Integer userId);

    PricesReportDTO getReportTotals(Integer userId);

    List<UserDTO> getReportUsers();

}