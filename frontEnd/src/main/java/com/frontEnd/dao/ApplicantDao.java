package com.frontEnd.dao;

import com.frontEnd.entity.Applicant;

import java.util.List;

/**
 * @Author: Mental
 * @Date:
 */
public interface ApplicantDao {

    int insertApp(Applicant applicant);

    List<Applicant> getApplicantList();
}
