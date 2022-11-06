package com.github.agrimint.extended.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * A DTO for the {@link com.github.agrimint.domain.Member} entity.
 */

@Data
public class CreatMemberRequestDTO implements Serializable {

    @NotNull
    private Long federationId;

    @NotNull
    private String name;

    private String alias;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String countryCode;
}