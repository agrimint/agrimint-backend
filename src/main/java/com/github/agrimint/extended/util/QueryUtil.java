package com.github.agrimint.extended.util;

import com.github.agrimint.extended.exeception.MemberAlreadyExistExecption;
import com.github.agrimint.service.AppUserQueryService;
import com.github.agrimint.service.FederationMemberQueryService;
import com.github.agrimint.service.FederationMemberService;
import com.github.agrimint.service.FederationQueryService;
import com.github.agrimint.service.criteria.AppUserCriteria;
import com.github.agrimint.service.criteria.FederationCriteria;
import com.github.agrimint.service.criteria.FederationMemberCriteria;
import com.github.agrimint.service.dto.AppUserDTO;
import com.github.agrimint.service.dto.FederationMemberDTO;
import java.time.Instant;
import java.util.Optional;
import org.springframework.stereotype.Component;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * @author OMONIYI ILESANMI
 */

@Component
public class QueryUtil {

    private final FederationMemberService federationMemberService;
    private final FederationMemberQueryService federationMemberQueryService;
    private final FederationQueryService federationQueryService;
    private final AppUserQueryService appUserQueryService;

    public QueryUtil(
        FederationMemberService federationMemberService,
        FederationMemberQueryService federationMemberQueryService,
        FederationQueryService federationQueryService,
        AppUserQueryService appUserQueryService
    ) {
        this.federationMemberService = federationMemberService;
        this.federationMemberQueryService = federationMemberQueryService;
        this.federationQueryService = federationQueryService;
        this.appUserQueryService = appUserQueryService;
    }

    public FederationMemberDTO persistFederationMember(Long federationId, Long memberId) throws MemberAlreadyExistExecption {
        if (getFederationMemberCount(federationId, memberId) > 0) {
            throw new MemberAlreadyExistExecption("Member Already Exist In Federation");
        }
        FederationMemberDTO federationMemberDTO = new FederationMemberDTO();
        federationMemberDTO.setMemberId(memberId);
        federationMemberDTO.setFederationId(federationId);
        federationMemberDTO.setActive(true);
        federationMemberDTO.setDateCreated(Instant.now());
        return federationMemberService.save(federationMemberDTO);
    }

    public Optional<AppUserDTO> getUserByPhoneNumberAndCountryCode(String phoneNumber, String countryCode) {
        AppUserCriteria appUserCriteria = new AppUserCriteria();
        StringFilter phoneNumberFilter = new StringFilter();
        phoneNumberFilter.setEquals(phoneNumber);
        appUserCriteria.setPhoneNumber(phoneNumberFilter);

        StringFilter countryCodeFilter = new StringFilter();
        countryCodeFilter.setEquals(countryCode);
        appUserCriteria.setCountryCode(countryCodeFilter);

        return Optional.ofNullable(appUserQueryService.findByCriteria(appUserCriteria).stream().findFirst().orElse(null));
    }

    public long getFederationCount(String federationName) {
        FederationCriteria federationCriteria = new FederationCriteria();
        StringFilter federationNameFilter = new StringFilter();
        federationNameFilter.setEquals(federationName);
        federationCriteria.setName(federationNameFilter);

        return federationQueryService.countByCriteria(federationCriteria);
    }

    public long getFederationMemberCount(Long federationId, Long memberId) {
        FederationMemberCriteria federationMemberCriteria = new FederationMemberCriteria();
        LongFilter federationIdFilter = new LongFilter();
        federationIdFilter.setEquals(federationId);
        federationMemberCriteria.setFederationId(federationIdFilter);

        LongFilter memberIdFilter = new LongFilter();
        memberIdFilter.setEquals(memberId);
        federationMemberCriteria.setFederationId(memberIdFilter);

        return federationMemberQueryService.countByCriteria(federationMemberCriteria);
    }

    public Optional<FederationMemberDTO> getFederationMember(Long federationId, Long memberId) {
        FederationMemberCriteria federationMemberCriteria = new FederationMemberCriteria();
        LongFilter federationIdFilter = new LongFilter();
        federationIdFilter.setEquals(federationId);
        federationMemberCriteria.setFederationId(federationIdFilter);

        LongFilter memberIdFilter = new LongFilter();
        memberIdFilter.setEquals(memberId);
        federationMemberCriteria.setFederationId(memberIdFilter);

        return Optional.ofNullable(federationMemberQueryService.findByCriteria(federationMemberCriteria).stream().findFirst().orElse(null));
    }

    public Optional<AppUserDTO> getUserByLogin(String login) {
        AppUserCriteria appUserCriteria = new AppUserCriteria();
        StringFilter loginFilter = new StringFilter();
        loginFilter.setEquals(login);
        appUserCriteria.setLogin(loginFilter);

        return Optional.ofNullable(appUserQueryService.findByCriteria(appUserCriteria).stream().findFirst().orElse(null));
    }
}
