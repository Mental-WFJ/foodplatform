package com.frontEnd.service;

import com.frontEnd.dto.ApplicantDto;
import com.frontEnd.entity.Applicant;

import java.util.List;

/**
 * @Author: Mental
 * @Date:
 */
public interface ApplicantService {

    public boolean insertApp(ApplicantDto applicantDto);

    public List<Applicant> getApplicantList();
}
